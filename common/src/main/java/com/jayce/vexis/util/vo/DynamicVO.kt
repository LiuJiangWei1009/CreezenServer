package com.jayce.vexis.util.vo

data class DynamicVO(
    val type: Int  = 0,
    val path: String = "",
    val title: String = "",
    val size: Long = 0L,
    val hash: String,
    val entries: List<DynamicItemVO>
)
