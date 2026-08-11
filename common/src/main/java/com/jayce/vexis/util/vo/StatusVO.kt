package com.jayce.vexis.util.vo

import com.jayce.vexis.util.Config.NIL

data class StatusVO(
    val statusCode: Int,
    val data: String = NIL
)
