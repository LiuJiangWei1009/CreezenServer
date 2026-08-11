package com.jayce.vexis.util.vo

import com.jayce.vexis.util.Config.NIL

data class ProfileVO(
    val nickname: String = "NickUser",
    val age: Int = -1,
    val sex: String = "UnKnow",
    val email: String = NIL,
    val selfIntroduction: String = NIL,
    val phone: String = NIL,
    val address: String = NIL,
    val birthday: String = NIL,
    val headType: String = NIL,
    val isEdit: Int = -1
) {
    fun isEdit() = isEdit != 1
}
