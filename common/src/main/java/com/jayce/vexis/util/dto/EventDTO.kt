package com.jayce.vexis.util.dto

import com.jayce.vexis.util.Config.EVENT_TYPE_DEFAULT
import com.jayce.vexis.util.Config.NIL
import com.jayce.vexis.util.DataConverter
import com.jayce.vexis.util.vo.EventVO

data class EventDTO (
    val type: Int,
    val userId: String,
    val nickName: String,
    val session: String,
    val time: Long,
    val msgId: String = "-1",
    val content: String = NIL
): DataConverter<EventVO> {

    fun isShake() = type == EVENT_TYPE_DEFAULT

    override fun vo(): EventVO {
        return EventVO(type, userId, nickName, session, time, false, msgId, content)
    }
}