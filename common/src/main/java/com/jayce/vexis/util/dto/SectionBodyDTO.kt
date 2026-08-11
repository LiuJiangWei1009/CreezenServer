package com.jayce.vexis.util.dto

import com.jayce.vexis.util.DataConverter
import com.jayce.vexis.util.vo.SectionBodyVO

data class SectionBodyDTO(
    val type: Int,
    val content: String
): DataConverter<SectionBodyVO> {

    override fun vo(): SectionBodyVO {
        return SectionBodyVO(type, content)
    }
}
