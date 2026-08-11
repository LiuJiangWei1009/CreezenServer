package com.jayce.vexis.util.vo

import com.jayce.vexis.util.Config.NIL

data class FileVO(
    val userId: String,
    val fileName: String,
    val fileID: String,
    val fileSuffix: String,
    val description: String,
    val illustrate: String,
    val fileSize: Long,
    val uploadTime: String,
    val fileHash: String = NIL
)
