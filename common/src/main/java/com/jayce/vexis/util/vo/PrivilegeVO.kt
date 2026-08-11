package com.jayce.vexis.util.vo

data class PrivilegeVO(
    val count: Long = -1,
    val level: Int = 0,
    val adminLevel: Int = 0,
) {
    fun isAdmin() = adminLevel > 0
}
