package com.jayce.vexis.business.dao

import com.jayce.vexis.util.dto.HistoryDTO

interface HistoryDao {

    fun insertEvent(historyDTO: HistoryDTO)

    fun queryAllEvent(): List<HistoryDTO>
}