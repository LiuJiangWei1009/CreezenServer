package com.jayce.vexis.util.dto

import com.jayce.vexis.util.DataConverter
import com.jayce.vexis.util.vo.ApkSimpleVO

data class ApkSimpleDTO(
    val versionName: String,
    val versionCode: Long,
    val modifyTime: Long
): DataConverter<ApkSimpleVO> {

    override fun vo(): ApkSimpleVO {
        return ApkSimpleVO(versionName, versionCode, modifyTime)
    }
}
