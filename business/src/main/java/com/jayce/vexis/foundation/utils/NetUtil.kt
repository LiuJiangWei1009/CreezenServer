package com.jayce.vexis.foundation.utils

import com.jayce.vexis.foundation.Log
import com.jayce.vexis.util.toJson
import org.springframework.web.reactive.function.client.WebClient

object NetUtil {

    private val log by lazy { Log(this::class.java) }

    private const val EMAIL_PATH = "https://apiv2.aoksend.com/index/api/send_email"
    private const val APP_KEY = "33f42a87dbd47e491b50eb77d3d82ba9"
    private const val TEMPLATE_KEY = "E_152062690826"

    private val emailClient = WebClient.builder().baseUrl(EMAIL_PATH).build()

    fun sendEmail(id: String, email: String, code: String): String {
        val params = mapOf(
            "code" to code,
            "account" to id
        )
        val values = mapOf(
            "app_key" to APP_KEY,
            "template_id" to TEMPLATE_KEY,
            "to" to email,
            "data" to params.toJson()
        )
        val result = emailClient.post()
            .bodyValue(values)
            .retrieve()
            .bodyToMono(String::class.java)
            .block()
        return result ?: ""
    }
}