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
        println("현재 사용 중인 OpenAI API Key: $apiKey")

        val messages = listOf(
            mapOf(
                "role" to "system",
                "content" to "넌 디지스트(DGIST, 대구경북과학기술원)의 마스코트인 '달구'. 친근하게 사용자에게 말하고, 장난스럽지만 예의도 갖춰."
            ),
            mapOf(
                "role" to "user",
                "content" to userMessage
            )
        )

        val requestBody = mapOf(
            "model" to "gpt-3.5-turbo",
            "messages" to messages
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
