package com.jayce.vexis.util.dto

import com.jayce.vexis.util.DataConverter
import com.jayce.vexis.util.vo.PeerVO

data class PeerDTO (
    val primary: String = "",
    val second: String = "",
    val tertiary: String = "",
    val content: String = ""
): DataConverter<PeerVO> {

    override fun vo(): PeerVO {
        return PeerVO(primary, second, tertiary, content)
    }
}
