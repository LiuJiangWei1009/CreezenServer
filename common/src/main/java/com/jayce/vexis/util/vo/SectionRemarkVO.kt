package com.jayce.vexis.util.vo

data class SectionRemarkVO(
    val articleId: Long,
    val sectionId: Long,
    val type : Int,
    val content: String,
    val list: List<RemarkVO>,
)
