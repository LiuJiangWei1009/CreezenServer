package com.jayce.vexis.util.dto

import com.jayce.vexis.util.Config.NIL
import com.jayce.vexis.util.DataConverter
import com.jayce.vexis.util.vo.AuthVO
import com.jayce.vexis.util.vo.UserVO

data class UserDTO(
    val userId: String = "-1",
    val createTime: String = NIL,
    val auth: AuthDTO = AuthDTO(),
    val profile: ProfileDTO = ProfileDTO(),
    val privilege: PrivilegeDTO = PrivilegeDTO()
): DataConverter<UserVO> {

    override fun vo(): UserVO {
        val authVO = AuthVO("", "")
        return UserVO(userId, createTime, authVO, profile.vo(), privilege.vo())
    }
}