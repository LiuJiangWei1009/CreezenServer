package com.jayce.vexis.util.dto

import com.jayce.vexis.util.DataConverter
import com.jayce.vexis.util.vo.SectionRemarkVO

data class SectionRemarkDTO(
    val articleId: Long,
    val sectionId: Long,
    val type : Int,
    val content: String,
    val list: List<RemarkDTO>,
): DataConverter<SectionRemarkVO> {

    override fun vo(): SectionRemarkVO {
        return SectionRemarkVO(articleId, sectionId, type, content, list.map { it.vo() })
    }
}
