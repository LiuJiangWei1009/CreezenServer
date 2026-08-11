package com.jayce.vexis.business.controllers

import com.jayce.vexis.business.dao.SeniorDao
import com.jayce.vexis.core.MyDispatchServlet
import com.jayce.vexis.foundation.Log
import com.jayce.vexis.util.dto.PeerDTO
import com.jayce.vexis.util.vo
import com.jayce.vexis.util.vo.PeerVO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class SeniorManager: MyDispatchServlet() {

    private val log by lazy { Log(this::class.java) }

    @Autowired
    lateinit var seniorDao: SeniorDao

    @RequestMapping("/postAdvice")
    @ResponseBody
    fun postAdvice(peerDTO: PeerDTO): Boolean {
        log.d("receive:  $peerDTO")
        seniorDao.addAdvice(peerDTO)
        return true
    }

    @RequestMapping("/getAdvice")
    @ResponseBody
    fun getAdvice(
        primary: String,
        second: String,
        tertiary: String,
    ): List<PeerVO> {
        val query = PeerDTO(primary, second, tertiary, "")
        val list = seniorDao.getAdvice(query)
        return list.vo()
    }
}