package com.example.myapplication.network

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Call

data class ChatRequest(
    val userId: String,   // ✅ 사용자 정보 추가
    val message: String
)

data class ChatResponse(val reply: String)

interface ChatApi {
    @POST("chat")
    fun sendMessage(@Body request: ChatRequest): Call<ChatResponse>
}
