package com.jayce.vexis.business.dao

import com.jayce.vexis.util.dto.FileDTO

interface FileDao {

    fun insertFile(file: FileDTO)

    fun getFile(): List<FileDTO>

    fun findFileByHash(fileHash: String): FileDTO?
}