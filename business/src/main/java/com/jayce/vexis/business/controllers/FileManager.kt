package com.jayce.vexis.business.controllers

import com.jayce.vexis.business.dao.FileDao
import com.jayce.vexis.core.MyDispatchServlet
import com.jayce.vexis.foundation.Log
import com.jayce.vexis.foundation.utils.FileHelper
import com.jayce.vexis.util.dto.DynamicDTO
import com.jayce.vexis.util.dto.DynamicItemDTO
import com.jayce.vexis.util.dto.FileDTO
import com.jayce.vexis.util.util.FileUtil.getHash
import com.jayce.vexis.util.vo
import com.jayce.vexis.util.vo.DynamicVO
import com.jayce.vexis.util.vo.FileVO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.nio.file.Files
import java.nio.file.StandardCopyOption

@Controller
class FileManager: MyDispatchServlet() {

    private val log by lazy { Log(this::class.java) }

    @Autowired
    lateinit var fileDao: FileDao

    @RequestMapping(value = ["/fileUpload"])
    @ResponseBody
    fun upload(
        @RequestPart("fileEntry") fileDTO: FileDTO,
        @RequestPart("file") file: MultipartFile,
    ): Int {
        log.d("${file.originalFilename}   $fileDTO")
        val tempFile = FileHelper.tempFile()
        file.transferTo(tempFile)
        val filePair = FileHelper.getFileHashAndType(tempFile, "SHA256")
        val fileHash = filePair.first
        val fileType = filePair.second
        val bean = if (fileType.isNotEmpty()) {
            fileDTO.copy(fileSuffix = ".$fileType")
        } else {
            fileDTO
        }
        val existFile = fileDao.findFileByHash(fileHash)
        if (existFile != null) {
            log.d("file exist")
            return -1
        }
        bean.fileHash = fileHash
        val destFile = File("$BASE_FILE_PATH/${bean.fileID}${bean.fileSuffix}")
        destFile.parentFile?.mkdirs()
        Files.move(tempFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING)
        fileDao.insertFile(bean)
        return 1
    }

    @RequestMapping(value = ["/fileFetch"])
    @ResponseBody
    fun fetch(): List<FileVO> {
        return fileDao.getFile().vo()
    }

    @RequestMapping(value = ["/loadSlider"])
    @ResponseBody
    fun loadSlider(): List<String> {
        val directory = File("$BASE_FILE_PATH/slider")
        val files = directory.listFiles() ?: return listOf()
        val nameList = files.map { it.name }
        return nameList
    }

    @RequestMapping(value = ["/getDynamic"])
    @ResponseBody
    fun loadDynamicModule(): List<DynamicVO> {
        val apkList = loadApkResource()
        val resourceList = loadRawResource()
        return apkList + resourceList
    }

    @RequestMapping(value = ["/loadDynamicSubModule"])
    @ResponseBody
    fun loadDynamicSubModule(module: String, sunModule: String): List<DynamicVO> {
        val moduleList = when (module) {
            "apk" -> loadApkResource()
            "resource" -> loadRawResource(sunModule)
            else -> listOf()
        }
        return moduleList
    }

    private fun loadApkResource() = scanResource("apk") { name, files ->
        if (files.size != 1) return@scanResource null
        val destFile = files[0]
        val hash = destFile.getHash()
        val moduleSubPath = "$name/${destFile.name}"
        val dynamicDTO = DynamicDTO(moduleSubPath, destFile.name, destFile.length(), hash)
        return@scanResource dynamicDTO
    }

    private fun loadRawResource(filterName: String = "") = scanResource("resource") { name, files ->
        val subList = arrayListOf<DynamicItemDTO>()
        if (filterName.isEmpty() || name == filterName) {
            files.forEach {
                val itemDTO = DynamicItemDTO(it.name, it.length(), it.getHash())
                subList.add(itemDTO)
                log.d("itemDTO: $itemDTO")
            }
        }
        val dynamicDTO = DynamicDTO(name, name, 0, "", subList)
        return@scanResource dynamicDTO
    }

    private fun scanResource(
        path: String,
        onScanFile: (String, Array<File>) -> DynamicDTO?
    ): List<DynamicVO> {
        val directory = File("$BASE_FILE_PATH/$path")
        val resources = directory.listFiles() ?: return listOf()
        val moduleResource = arrayListOf<DynamicVO>()
        resources.forEach {
            val files = it.listFiles() ?: return@forEach
            if (files.isEmpty()) return@forEach
            val dto = onScanFile.invoke(it.name, files) ?: return@forEach
            moduleResource.add(dto.vo())
        }
        return moduleResource
    }
}