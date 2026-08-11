package com.jayce.vexis.util.dto

import com.jayce.vexis.util.Config.NIL
import com.jayce.vexis.util.DataConverter
import com.jayce.vexis.util.vo.ProfileVO

data class ProfileDTO(
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
): DataConverter<ProfileVO> {

    fun isEdit() = isEdit != 1

    override fun vo(): ProfileVO {
        return ProfileVO(nickname, age, sex, email, selfIntroduction, phone, address, birthday, headType, isEdit)
    }
}
