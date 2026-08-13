package com.jayce.vexis.foundation.utils

import com.jayce.vexis.foundation.Log
import com.jayce.vexis.util.Config.EVENT_TYPE_EXIT
import com.jayce.vexis.util.dto.EventDTO
import com.jayce.vexis.util.toJson
import org.springframework.context.ApplicationContext
import org.springframework.data.redis.connection.stream.MapRecord
import org.springframework.data.redis.connection.stream.ReadOffset
import org.springframework.data.redis.connection.stream.StreamOffset
import org.springframework.data.redis.connection.stream.StreamReadOptions
import org.springframework.data.redis.core.*
import java.time.Duration
import java.util.concurrent.TimeUnit

object RedisUtil {

    const val STREAM_MESSAGE_ID = "msgId"
    const val STREAM_CONTENT_KEY = "messageKey"
    private const val STREAM_NAME = "telecom"
    private const val ONLINE_PREFIX = "ONLINE_"
    private const val STREAM_READ_COUNT = 256L

    private val log by lazy { Log(this::class.java) }

    private lateinit var template: StringRedisTemplate
    private lateinit var stringOpt: ValueOperations<String, String>
    private lateinit var hashOpt: HashOperations<String, Any, Any>
    private lateinit var listOpt: ListOperations<String, String>
    private lateinit var setOpt: SetOperations<String, String>
    private lateinit var zsetOpt: ZSetOperations<String, String>
    private lateinit var streamOpt: StreamOperations<String, Any, Any>
    private lateinit var geoOpt: GeoOperations<String, Any>
    private lateinit var hyperLogOpt: HyperLogLogOperations<String, Any>
    private lateinit var clusterOpt: ClusterOperations<String, Any>

    private val streamOption =  StreamReadOptions.empty().count(STREAM_READ_COUNT).block(Duration.ZERO)

    /**
     * 所有需要通过aop代理的数据，都需要通过spring管理
     * 所以除了定义切点之外，还需要定义bean，以及逐一将切点都加进去
     */
    fun init(redisTemplate: StringRedisTemplate, context: ApplicationContext) {
        initOpt(redisTemplate, context)
        initStatus()
    }

    @Suppress("UNCHECKED_CAST")
    private fun initOpt(redisTemplate: StringRedisTemplate, context: ApplicationContext) {
        template = redisTemplate
        stringOpt = context.getBean("stringOpt") as ValueOperations<String, String>
        hashOpt = context.getBean("hashOpt") as HashOperations<String, Any, Any>
        listOpt = context.getBean("listOpt") as ListOperations<String, String>
        setOpt = context.getBean("setOpt") as SetOperations<String, String>
        zsetOpt = context.getBean("zsetOpt") as ZSetOperations<String, String>
        streamOpt = context.getBean("streamOpt") as StreamOperations<String, Any, Any>
        geoOpt = context.getBean("geoOpt") as GeoOperations<String, Any>
        hyperLogOpt = context.getBean("hyperLogOpt") as HyperLogLogOperations<String, Any>
        clusterOpt =  context.getBean("clusterOpt") as ClusterOperations<String, Any>
    }

    private fun initStatus() {
        clearOnlineStatus()
    }

    @Suppress("UNCHECKED_CAST")
    fun <K, V> readStream(eventTag: String): List<MapRecord<String, K, V>> {
        val eventOffset =  StreamOffset.create(STREAM_NAME, ReadOffset.from(eventTag))
        val eventList = streamOpt.read(streamOption, eventOffset)
        return eventList as List<MapRecord<String, K, V>>
    }

    fun <V> writeStream(content: V) {
        val map = mapOf(STREAM_CONTENT_KEY to content)
        streamOpt.add(STREAM_NAME, map)
    }

    fun sendFinishMsg(msg: EventDTO) {
        val finishJson = msg.copy(
            type = EVENT_TYPE_EXIT,
            time = System.currentTimeMillis()
        ).toJson()
        writeStream(finishJson)
    }

    fun setOnlineStatus(userId: String, session: String) {
        stringOpt.set(getOnlineKey(userId), session)
    }

    fun isUserAlreadyOnline(userId: String): Boolean {
        val session = stringOpt.get(getOnlineKey(userId))
        return session != null
    }

    fun verifyOnlineStatus(msg: EventDTO): Boolean {
        val cacheSession = stringOpt.get(getOnlineKey(msg.userId))
        log.d("校验session 缓存：$cacheSession 新：${msg.session}")
        if (cacheSession == null || cacheSession == msg.session) {
            return true
        }
        return false
    }

    fun verifyOnlineStatus(userId: String?, session: String?): Boolean {
        if (userId == null) return false
        if (session == null) return false
        val cacheSession = stringOpt.get(getOnlineKey(userId))
//        return cacheSession == session
        return true
    }

    fun setOfflineStatus(userId: String) {
        template.delete(getOnlineKey(userId))
    }

    fun saveUser(name: String, status: String) {
        stringOpt.set(name, status)
    }

    fun saveEmailCode(id: String, code: String) {
        stringOpt.set(id, code, 60, TimeUnit.SECONDS)
    }

    fun checkEmailCode(id: String, code: String): Boolean {
        return stringOpt.get(id) == code
    }

    fun queryUser(name: String): String? {
        return stringOpt.get(name)
    }

    fun feedbackSupport(userId: String, feedbackId: String) {
        val feedbackKey = getFeedbackKey(userId, feedbackId)
        stringOpt.set(feedbackKey, "1")
    }

    fun removeSupportFeedback(userId: String, feedbackId: String): Boolean {
        val feedbackKey = getFeedbackKey(userId, feedbackId)
        return template.delete(feedbackKey)
    }

    private fun clearOnlineStatus() {
        template.keys("*").forEach {
            if (it.startsWith(ONLINE_PREFIX)) {
                template.delete(it)
            }
        }
    }

    private fun getOnlineKey(userId: String) = "$ONLINE_PREFIX$userId"

    private fun getFeedbackKey(userId: String, feedbackId: String) = "$userId$feedbackId"
}