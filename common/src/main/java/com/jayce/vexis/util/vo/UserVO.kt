package com.jayce.vexis.util.vo

import com.jayce.vexis.util.Config.NIL

data class UserVO(
    val userId: String = "-1",
    val createTime: String = NIL,
    val auth: AuthVO = AuthVO(),
    val profile: ProfileVO = ProfileVO(),
    val privilege: PrivilegeVO = PrivilegeVO()
){
    fun isAdministrator() = privilege.isAdmin()
}
