package com.jayce.vexis.util.vo

import com.jayce.vexis.util.Config.NIL

data class EventVO(
    val type: Int,
    val userId: String,
    val nickName: String,
    val session: String,
    val time: Long,
    val isRead: Boolean = false,
    val msgId: String = "-1",
    val content: String = NIL
)
