package com.dglife.dglife_server.service

import com.dglife.dglife_server.dto.SignupRequest
import com.dglife.dglife_server.model.User
import com.dglife.dglife_server.util.JwtUtil
import com.dglife.dglife_server.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtUtil: JwtUtil // ← 이게 꼭 있어야 함
) {
    fun registerUser(request: SignupRequest): String {
        if (userRepository.findByUsername(request.username) != null) {
            throw IllegalArgumentException("이미 존재하는 사용자입니다.")
        }

        val encodedPassword = passwordEncoder.encode(request.password)
        val user = User(username = request.username, password = encodedPassword)
        userRepository.save(user)

        return "회원가입 성공"
    }
    fun login(username: String, password: String): String {
        val user = userRepository.findByUsername(username)
            ?: throw IllegalArgumentException("존재하지 않는 사용자입니다.")

        if (!passwordEncoder.matches(password, user.password)) {
            throw IllegalArgumentException("비밀번호가 일치하지 않습니다.")
        }

        return jwtUtil.generateToken(username)
    }
}
