package com.jayce.vexis.util.util

import java.io.File
import java.io.InputStream
import java.security.MessageDigest
import java.util.zip.ZipFile
import java.util.zip.ZipInputStream
import kotlin.math.min

object FileUtil {

    fun File.getHash(algorithm: String = "SHA256") = getFileHashAndHead(this, algorithm).first

    fun getFileHashAndHead(file: File, algorithm: String, length: Int = 1024): Pair<String, String> {
        val digest = MessageDigest.getInstance(algorithm)
        val headArray = ByteArray(length)
        var isHeadRead = false
        var readLength = 0
        file.inputStream().use { stream ->
            val buffer = ByteArray(1024 * 1024)
            var contentSize: Int
            while (stream.read(buffer).also { contentSize = it } != -1) {
                if (!isHeadRead) {
                    readLength = min(contentSize, length)
                    buffer.copyInto(headArray, 0, 0, readLength)
                    isHeadRead = true
                }
                digest.update(buffer, 0, contentSize)
            }
        }
        val digestValue = digest.digest().joinToString("") { "%02x".format(it) }
        val headValue = byteToString(headArray.copyOfRange(0, readLength))
        return digestValue to headValue
    }

    fun isApk(file: File): Boolean {
        var isApkFile: Boolean
        ZipFile(file).use {
            val hasManifest = it.getEntry("AndroidManifest.xml") != null
            val hasResources = it.getEntry("resources.arsc") != null
            val hasClasses = it.getEntry("classes.dex") != null
            isApkFile = hasManifest && (hasResources || hasClasses)
        }
        return isApkFile
    }

    fun isApk(stream: InputStream): Boolean {
        ZipInputStream(stream).use { zip ->
            var entry = zip.nextEntry
            var hasManifest = false
            var hasResource = false
            var hasClasses = false
            while (entry != null) {
                when (entry.name) {
                    "AndroidManifest.xml" -> hasManifest = true
                    "resources.arsc" -> hasResource = true
                    "classes.dex" -> hasClasses = true
                }
                if (hasManifest && (hasResource || hasClasses)) {
                    return true
                }
                entry = zip.nextEntry
            }
        }
        return false
    }

    private fun byteToString(byteArray: ByteArray): String {
        val stringBuilder = StringBuilder()
        byteArray.forEach {
            val byteValue = it.toInt() and 0xFF
            val hex = Integer.toHexString(byteValue)
            val hexValue = hex.padStart(2, '0').uppercase()
            stringBuilder.append(hexValue)
        }
        return stringBuilder.toString()
    }
}