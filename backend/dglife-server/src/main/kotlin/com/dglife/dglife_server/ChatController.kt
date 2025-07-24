package com.dglife.dglife_server

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/chat")
class ChatController(
    private val chatLogRepository: ChatLogRepository,
    private val openAiService: OpenAiService
) {

    @PostMapping
    fun chat(@RequestBody request: ChatRequest): ResponseEntity<Map<String, String>> {
        val userId = request.userId                     // ✅ 추가
        val userMessage = request.message
        val gptResponse = openAiService.getChatResponse(userMessage)

        chatLogRepository.save(
            ChatLog(
                userId = userId,
                userMessage = userMessage,
                gptResponse = gptResponse
            )
        )
        // 응답을 JSON으로 감싸서 전송
        return ResponseEntity.ok(mapOf("reply" to gptResponse))
    }
}
