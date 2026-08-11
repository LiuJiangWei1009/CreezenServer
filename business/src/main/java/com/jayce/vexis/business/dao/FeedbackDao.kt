package com.jayce.vexis.business.dao

import com.jayce.vexis.util.dto.FeedbackDTO

interface FeedbackDao {

    fun insertFeedback(feedbackDTO: FeedbackDTO)

    fun getFeedback(): List<FeedbackDTO>

    fun supportFeedback(feedbackId: String, count: Int)
}