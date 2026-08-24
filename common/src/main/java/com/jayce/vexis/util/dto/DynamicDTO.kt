package com.jayce.vexis.util.dto

import com.jayce.vexis.util.DataConverter
import com.jayce.vexis.util.vo
import com.jayce.vexis.util.vo.DynamicVO

data class DynamicDTO(
    val path: String = "",
    val title: String = "",
    val size: Long = 0L,
    val hash: String = "",
    val entries: List<DynamicItemDTO> = listOf()
): DataConverter<DynamicVO> {

    override fun vo(): DynamicVO {
        val type = if (entries.isEmpty()) 0 else 1
        return DynamicVO(type, path, title, size, hash, entries.vo())
    }
}
