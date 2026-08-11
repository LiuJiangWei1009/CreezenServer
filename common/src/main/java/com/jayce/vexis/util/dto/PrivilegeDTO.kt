package com.jayce.vexis.util.dto

import com.jayce.vexis.util.DataConverter
import com.jayce.vexis.util.vo.PrivilegeVO

data class PrivilegeDTO(
    val count: Long = -1,
    val level: Int = 0,
    val adminLevel: Int = 0,
): DataConverter<PrivilegeVO> {

    override fun vo(): PrivilegeVO {
        return PrivilegeVO(count, level, adminLevel)
    }
}
