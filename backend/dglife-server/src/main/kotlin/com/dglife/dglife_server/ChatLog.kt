package com.dglife.dglife_server

import jakarta.persistence.*

@Entity
data class ChatLog(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val userId: String,           // ← 반드시 있어야 함!
    val userMessage: String,
    val gptResponse: String
)
