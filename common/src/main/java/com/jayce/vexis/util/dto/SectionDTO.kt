package com.jayce.vexis.util.dto

import com.jayce.vexis.util.DataConverter
import com.jayce.vexis.util.vo.SectionVO

data class SectionDTO (
    val articleId: Long,
    val sectionId: Long,
    val orderId: Int,
    val type: Int,
    val content: String
): DataConverter<SectionVO> {

    override fun vo(): SectionVO {
        return SectionVO(articleId, sectionId, orderId, type, content)
    }
}