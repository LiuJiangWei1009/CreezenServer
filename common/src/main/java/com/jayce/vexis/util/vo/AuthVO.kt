package com.jayce.vexis.util.vo

import com.jayce.vexis.util.Config.NIL

data class AuthVO(
    val password: String = NIL,
    val session: String = NIL,
)
