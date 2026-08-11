package com.jayce.vexis.util.dto

import com.jayce.vexis.util.DataConverter
import com.jayce.vexis.util.vo.FeedbackVO

data class FeedbackDTO(
    val feedbackID: String,
    val userName: String,
    val userID: String? = null,
    val type: String,
    val title: String,
    val content: String,
    val createTime: Long,
    val support: Long,
    val against: Long
): DataConverter<FeedbackVO> {

    override fun vo(): FeedbackVO {
        return FeedbackVO(feedbackID, userName, userID ?: "", type, title, content, createTime, support, against)
    }
}
