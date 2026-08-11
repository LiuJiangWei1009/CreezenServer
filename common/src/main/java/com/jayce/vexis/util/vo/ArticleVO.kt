package com.jayce.vexis.util.vo

import com.jayce.vexis.util.Config.NIL

data class ArticleVO(
    var articleId: Long = 0,
    var userId: String = NIL,
    var title: String = NIL,
    var createTime: Long = 0,
    var updateTime: Long = 0,
    var favor: Long = 0
)
