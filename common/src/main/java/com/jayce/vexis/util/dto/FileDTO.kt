package com.jayce.vexis.util.dto

import com.jayce.vexis.util.Config.NIL
import com.jayce.vexis.util.DataConverter
import com.jayce.vexis.util.vo.FileVO

data class FileDTO(
    val userId: String = NIL,
    val fileName: String = NIL,
    val fileID: String = NIL,
    val fileSuffix: String = NIL,
    val description: String = NIL,
    val illustrate: String = NIL,
    val fileSize: Long = 0,
    val uploadTime: String = NIL,
    var fileHash: String = NIL
): DataConverter<FileVO> {

    override fun vo(): FileVO {
        return FileVO(userId, fileName, fileID, fileSuffix, description, illustrate, fileSize, uploadTime, fileHash)
    }
}