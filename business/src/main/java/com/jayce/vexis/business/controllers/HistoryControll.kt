package com.jayce.vexis.business.controllers

import com.jayce.vexis.util.dto.HistoryDTO
import com.jayce.vexis.core.MyDispatchServlet
import com.jayce.vexis.business.dao.HistoryDao
import com.jayce.vexis.util.vo
import com.jayce.vexis.util.vo.HistoryVO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class HistoryControll: MyDispatchServlet() {

    @Autowired
    lateinit var historyDao: HistoryDao

    @RequestMapping("/sendEvent")
    @ResponseBody
    fun sendEvent(
        time: String,
        event: String,
    ): Boolean {
        historyDao.insertEvent(HistoryDTO(time, event))
        return true
    }

    @RequestMapping("/queryAllEvent")
    @ResponseBody
    fun queryAllEvent(): List<HistoryVO> {
        val list = historyDao.queryAllEvent()
        return list.vo()
    }
}