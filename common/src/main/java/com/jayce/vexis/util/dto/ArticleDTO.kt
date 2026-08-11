package com.jayce.vexis.util.dto

import com.jayce.vexis.util.Config.NIL
import com.jayce.vexis.util.DataConverter
import com.jayce.vexis.util.vo.ArticleVO

data class ArticleDTO(
    var articleId: Long = 0,
    var userId: String = NIL,
    var title: String = NIL,
    var createTime: Long = 0,
    var updateTime: Long = 0,
    var favor: Long = 0
): DataConverter<ArticleVO> {

    override fun vo(): ArticleVO {
        return ArticleVO(articleId, userId, title, createTime, updateTime, favor)
    }
}
