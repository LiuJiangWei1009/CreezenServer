package com.jayce.vexis.business.dao

import com.jayce.vexis.util.dto.ArticleDTO
import com.jayce.vexis.util.dto.RemarkDTO
import com.jayce.vexis.util.dto.SectionDTO

interface ArticleDao {

    fun saveArticle(articleDTO: ArticleDTO)

    fun saveSection(sectionDTO: SectionDTO)

    fun getArticle(): List<ArticleDTO>

    fun getSections(articleId: Long): List<SectionDTO>

    fun getRemark(sectionId: Long): List<RemarkDTO>

    fun insertRemark(remarkDTO: RemarkDTO)

    fun deleteArticle(articleId: Long)
}