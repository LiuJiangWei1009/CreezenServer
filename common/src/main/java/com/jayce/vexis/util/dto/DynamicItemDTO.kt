package com.jayce.vexis.util.dto

import com.jayce.vexis.util.DataConverter
import com.jayce.vexis.util.vo.DynamicItemVO

data class DynamicItemDTO(
    val name: String = "",
    val size: Long = 0L,
    val hash: String = ""
): DataConverter<DynamicItemVO> {

    override fun vo(): DynamicItemVO {
        return DynamicItemVO(name, size, hash)
    }
}
