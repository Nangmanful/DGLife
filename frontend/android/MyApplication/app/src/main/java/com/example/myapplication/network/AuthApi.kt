package com.example.myapplication.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

// 🔸 로그인 요청 DTO
data class LoginRequest(
    val username: String,
    val password: String
)

// 🔸 로그인 응답 DTO
data class LoginResponse(
    val token: String
)

// 🔸 회원가입 요청 DTO
data class SignupRequest(
    val username: String,
    val password: String
)

// 🔸 Auth API 인터페이스
interface AuthApi {
    @POST("auth/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @POST("auth/signup")
    fun signup(@Body request: SignupRequest): Call<Void>
}
