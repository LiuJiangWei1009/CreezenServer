package com.jayce.vexis.foundation.utils

import com.jayce.vexis.foundation.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

object ThreadUtil {

    private val log by lazy { Log(this::class.java) }

    fun workInThread(func: () -> Unit) {
        Thread {
            kotlin.runCatching {
                func.invoke()
            }.onFailure {
                log.e("error: ${it.message}")
            }
        }.start()
    }

    fun workInThreadBlocked(func: () -> Boolean) {
        workInThread {
            while (func.invoke()){}
        }
    }

    fun workLooper(scope: CoroutineScope, func: suspend (Boolean) -> Boolean) = scope.launch {
        while (true) {
            kotlin.runCatching {
                val status = func.invoke(true)
                if (!status) return@launch
            }.onFailure {
                log.w("workLooper error: ${it.message}")
                func.invoke(false)
                return@launch
            }
        }
    }
}