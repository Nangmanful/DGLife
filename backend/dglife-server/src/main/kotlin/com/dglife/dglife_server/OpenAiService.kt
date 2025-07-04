package com.dglife.dglife_server

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class OpenAiService(
    @Value("\${openai.api-key}") private val apiKey: String
) {

    private val webClient = WebClient.builder()
        .baseUrl("https://api.openai.com/v1/chat/completions")
        .defaultHeader("Authorization", "Bearer $apiKey")
        .defaultHeader("Content-Type", "application/json")
        .build()

    fun getChatResponse(userMessage: String): String {
        println("현재 사용 중인 OpenAI API Key: $apiKey")  // ← 여기에 추가

        val requestBody = mapOf(
            "model" to "gpt-3.5-turbo",
            "messages" to listOf(mapOf("role" to "user", "content" to userMessage))
        )

        val response = webClient.post()
            .bodyValue(requestBody)
            .retrieve()
            .bodyToMono(Map::class.java)
            .block()

        val choices = response?.get("choices") as? List<*> ?: return "GPT 응답 실패"
        val choice = choices.firstOrNull() as? Map<*, *> ?: return "응답 없음"
        val message = choice["message"] as? Map<*, *> ?: return "메시지 없음"

        return message["content"] as? String ?: "내용 없음"
    }
}
