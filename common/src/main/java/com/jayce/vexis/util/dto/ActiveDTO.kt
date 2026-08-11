package com.jayce.vexis.util.dto

import com.jayce.vexis.util.DataConverter
import com.jayce.vexis.util.vo.ActiveVO

data class ActiveDTO(
    val userID: String,
    val nickname: String? = null,
    val createTime: String,
    val level: Int = 0,
    val adminLevel: Int = 0,
    val support: Long = 0,
    val against: Long = 0,
    val inform: Int = 0,
    val reported: Int = 0,
    val follow: Long = 0,
    val fans: Long = 0,
    val post: Int = 0,
): DataConverter<ActiveVO> {

    override fun vo(): ActiveVO {
        return ActiveVO(userID, nickname, createTime, level, adminLevel, support, against, inform, reported, follow, fans, post)
    }
}
