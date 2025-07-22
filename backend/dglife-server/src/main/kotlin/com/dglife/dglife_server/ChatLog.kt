package com.dglife.dglife_server

import jakarta.persistence.*

@Entity
data class ChatLog(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val userMessage: String,
    val gptResponse: String
)
