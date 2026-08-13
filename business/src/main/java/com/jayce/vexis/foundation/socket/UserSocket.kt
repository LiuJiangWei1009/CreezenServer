package com.jayce.vexis.foundation.socket

import com.jayce.vexis.foundation.Log
import com.jayce.vexis.foundation.utils.RedisUtil.STREAM_CONTENT_KEY
import com.jayce.vexis.foundation.utils.RedisUtil.STREAM_MESSAGE_ID
import com.jayce.vexis.foundation.utils.RedisUtil.readStream
import com.jayce.vexis.foundation.utils.RedisUtil.sendFinishMsg
import com.jayce.vexis.foundation.utils.RedisUtil.setOfflineStatus
import com.jayce.vexis.foundation.utils.RedisUtil.verifyOnlineStatus
import com.jayce.vexis.foundation.utils.RedisUtil.writeStream
import com.jayce.vexis.foundation.utils.ThreadUtil.workLooper
import com.jayce.vexis.util.Config.EVENT_TYPE_EXIT
import com.jayce.vexis.util.dto.EventDTO
import com.jayce.vexis.util.toBean
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import org.json.JSONObject
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.Socket

class UserSocket(private val socket: Socket, private val callback: (UserSocket, String) -> Unit) {

    private val log by lazy { Log(this::class.java) }

    private lateinit var reader: BufferedReader
    private lateinit var writer: BufferedWriter

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    private lateinit var _shakeMessage: EventDTO
    private var eventTag: String = "0"
    private var hasFinish: Boolean = false
    private var isDied: Boolean = false

    fun init() {
        reader = BufferedReader(InputStreamReader(socket.getInputStream(), "UTF-8"))
        writer = BufferedWriter(OutputStreamWriter(socket.getOutputStream(), "UTF-8"))
        val shakeMessage = reader.readLine().toBean<EventDTO>()
        if (shakeMessage == null || !shakeMessage.isShake()) {
            destroy()
            return
        }
        identify(shakeMessage)
        startRead()
        startWrite()
    }

    private fun identify(shakeMessage: EventDTO) {
        _shakeMessage = shakeMessage
        eventTag = shakeMessage.content
        if (verifyOnlineStatus(shakeMessage)) {
            callback.invoke(this@UserSocket, shakeMessage.userId)
        } else {
            sendFinishMsg(shakeMessage)
        }
    }

    private fun startRead() {
        workLooper(scope) {
            if (!it) {
                if (!hasFinish) markDeath()
                return@workLooper false
            }
            val line = reader.readLine()
            log.d("接收消息： $line")
            if (line.isNullOrEmpty()) {
                markDeath()
                return@workLooper false
            }
            writeStream(line)
            return@workLooper true
        }
    }

    private fun startWrite() {
        workLooper(scope) {
            val list = readStream<String, String>(eventTag)
            eventTag = list.last().id.value
            list.forEach {
                log.d("发送消息： ${it.value}")
                val json = JSONObject(it.value[STREAM_CONTENT_KEY])
                val type = json.optInt("type", -1)
                val eventSession = json.optString("session") ?: ""
                if (type == EVENT_TYPE_EXIT) {
                    if (eventSession != _shakeMessage.session) return@forEach
                    setOfflineStatus(_shakeMessage.userId)
                    write(json.toString())
                    destroy()
                    return@workLooper false
                }
                json.put(STREAM_MESSAGE_ID, it.id)
                write(json.toString())
            }
            return@workLooper true
        }
    }

    private fun write(content: String) {
        kotlin.runCatching {
            writer.write("$content\n")
            writer.flush()
        }.onFailure {
            log.i("Socket write error: ${it.message}")
        }
    }

    private fun markDeath() {
        sendFinishMsg(_shakeMessage)
        setOfflineStatus(_shakeMessage.userId)
        hasFinish = true
    }

    fun destroy() {
        if (isDied) return
        isDied = true
        kotlin.runCatching {
            socket.shutdownInput()
            socket.shutdownOutput()
            reader.close()
            writer.close()
            socket.close()
            scope.cancel()
        }.onFailure {
            log.e("Socket destroy error: ${it.message}")
        }
    }
}