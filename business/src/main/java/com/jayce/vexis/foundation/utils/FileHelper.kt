package com.jayce.vexis.foundation.utils

import com.jayce.vexis.core.MyDispatchServlet.Companion.BASE_FILE_PATH
import com.jayce.vexis.util.bo.FileTypeBO
import com.jayce.vexis.util.util.FileUtil.getFileHashAndHead
import com.jayce.vexis.util.util.FileUtil.isApk
import org.yaml.snakeyaml.LoaderOptions
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor
import java.io.File

object FileHelper {

    private var _fileMap: Map<String, List<String>>? = null

    private val fileTypeMap: Map<String, List<String>>
        get() {
            return if (_fileMap != null) {
                _fileMap ?: mapOf()
            } else {
                _fileMap = loadDataFromYAML()
                _fileMap ?: mapOf()
            }
        }

    private lateinit var fileTypeList: List<String>

    private val compare = Comparator<String> { a, b ->
        val compResult = b.length - a.length
        if (compResult != 0) {
            return@Comparator compResult
        }
        a.zip(b).forEach {
            if (it.first == it.second) {
                return@forEach
            }
            return@Comparator charToCompareInt(it.first) - charToCompareInt(it.second)
        }
        return@Comparator 0
    }

    fun init() {
        if (!FileHelper::fileTypeList.isInitialized) {
            fileTypeList = fileTypeMap.keys.toList().sortedWith(compare)
        }
    }

    fun getFileHashAndType(file: File, algorithm: String, length: Int = 1024): Pair<String, String> {
        val pair = getFileHashAndHead(file, algorithm, length)
        val fileType = getFileTypeByFileHead(file, pair.second)
        return pair.first to fileType
    }

    fun getFileTypeByFileHead(file: File, fileHead: String): String {
        for (i in fileTypeList.indices) {
            val typeIndicator = fileTypeList[i]
            if (fileHead.length < typeIndicator.length) {
                continue
            }
            if (fileHead.startsWith(typeIndicator)) {
                var type = fileTypeMap[typeIndicator]?.first()
                if (type == "zip") {
                    if (isApk(file)) {
                        type = "apk"
                    }
                }
                return type ?: ""
            }
        }
        return ""
    }

    fun tempFile(): File {
        val tempDirectory = File("$BASE_FILE_PATH/temp/")
        if (tempDirectory.exists().not()) tempDirectory.mkdirs()
        val tempFile = File(tempDirectory, "${System.currentTimeMillis()}")
        tempFile.createNewFile()
        return tempFile
    }

    private fun loadDataFromYAML(): Map<String, List<String>> {
        kotlin.runCatching {
            val yaml = Yaml(Constructor(LoaderOptions()))
            val source = "fileType.yaml"
            javaClass.classLoader.getResourceAsStream(source)?.use {
                val values = yaml.loadAs(it, FileTypeBO::class.java)
                return values.typeMap
            } ?: run {
                println("stream is empty")
            }
        }.onFailure {
            println("fail ${it.message}")
        }
        return mapOf()
    }

    private fun charToCompareInt(c: Char): Int {
        return when(c) {
            in 'a'..'z' -> 200 + c.code
            in 'A'..'Z' -> 400 + c.code
            in '0'..'9' -> c.code
            else -> -1
        }
    }
}