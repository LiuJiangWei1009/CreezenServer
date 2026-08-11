package com.jayce.vexis.util.dto

import com.jayce.vexis.util.DataConverter
import com.jayce.vexis.util.vo.HistoryVO
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

data class HistoryDTO (val time: String, val event: String): DataConverter<HistoryVO> {

    fun isValid() = time.length == 17

    fun millisTime(): Long {
        val formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")
        val localDateTime = LocalDateTime.parse(time, formatter)
        val zoneDateTime = localDateTime.atZone(ZoneId.systemDefault())
        return zoneDateTime.toInstant().toEpochMilli()
    }

    override fun vo(): HistoryVO {
        return HistoryVO(time, event)
    }
}
