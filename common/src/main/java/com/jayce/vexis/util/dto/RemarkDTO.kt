package com.jayce.vexis.util.dto

import com.jayce.vexis.util.DataConverter
import com.jayce.vexis.util.vo.RemarkVO

data class RemarkDTO(
    val sectionId: Long,
    val userId: String,
    val remarkId: Long,
    val content: String,
    val type: Int,
    val favor: Long,
    val createTime: Long
): DataConverter<RemarkVO> {

    override fun vo(): RemarkVO {
        return RemarkVO(sectionId, userId, remarkId, content, type, favor, createTime)
    }
}
