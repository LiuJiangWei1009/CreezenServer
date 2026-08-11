package com.jayce.vexis.util.dto

import com.jayce.vexis.util.Config.NIL

data class AuthDTO(
    val password: String = NIL,
    val session: String = NIL,
)
