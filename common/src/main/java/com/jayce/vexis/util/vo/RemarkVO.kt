package com.jayce.vexis.util.vo

data class RemarkVO(
    val sectionId: Long,
    val userId: String,
    val remarkId: Long,
    val content: String,
    val type: Int,
    val favor: Long,
    val createTime: Long
)
