package com.dglife.dglife_server.controller

import com.dglife.dglife_server.dto.*
import com.dglife.dglife_server.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
    private val userService: UserService
) {
    @PostMapping("/signup")
    fun signup(@RequestBody request: SignupRequest): ResponseEntity<String> {
        return try {
            val result = userService.registerUser(request)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().body("회원가입 실패: ${e.message}")
        }
    }
    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): ResponseEntity<AuthResponse> {
        val token = userService.login(request.username, request.password)
        return ResponseEntity.ok(AuthResponse(token))
    }

}
