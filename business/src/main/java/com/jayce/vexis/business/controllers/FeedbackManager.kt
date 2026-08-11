package com.jayce.vexis.business.controllers

import com.jayce.vexis.util.dto.FeedbackDTO
import com.jayce.vexis.business.dao.FeedbackDao
import com.jayce.vexis.business.dao.UserDao
import com.jayce.vexis.core.MyDispatchServlet
import com.jayce.vexis.foundation.Log
import com.jayce.vexis.foundation.utils.RedisUtil
import com.jayce.vexis.util.vo
import com.jayce.vexis.util.vo.FeedbackVO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class FeedbackManager: MyDispatchServlet() {

    private val log by lazy { Log(this::class.java) }

    @Autowired
    lateinit var feedbackDao: FeedbackDao

    @Autowired
    lateinit var userDao: UserDao

    @RequestMapping("/sendFeedback")
    @ResponseBody
    fun sendFeedback(
        feedbackID: String,
        userID: String,
        title: String,
        content: String,
        type: String
    ): Boolean {
        val userName = userDao.findByID(userID)?.profile?.nickname ?: "匿名用户"
        val feedbackDTO = FeedbackDTO(
            feedbackID,
            userName,
            userID,
            type,
            title,
            content,
            System.currentTimeMillis(),
            0,
            0
        )
        feedbackDao.insertFeedback(feedbackDTO)
        return true
    }

    @RequestMapping("/getFeedback")
    @ResponseBody
    fun getFeedback(): List<FeedbackVO> {
        return feedbackDao.getFeedback().vo()
    }

    @RequestMapping("/supportFeedback")
    @ResponseBody
    fun supportFeedback(userId: String, feedbackId: String): Boolean {
        val status = RedisUtil.removeSupportFeedback(userId, feedbackId)
        if (status) {
            feedbackDao.supportFeedback(feedbackId, -1)
            return false
        } else {
            RedisUtil.feedbackSupport(userId, feedbackId)
            feedbackDao.supportFeedback(feedbackId, 1)
            return true
        }
    }
}