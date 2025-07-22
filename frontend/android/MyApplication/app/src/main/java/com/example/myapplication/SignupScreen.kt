package com.example.myapplication

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.network.SignupRequest
import com.example.myapplication.network.RetrofitInstance
import com.example.myapplication.ui.components.HeaderBar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun SignupScreen(navController: NavController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            HeaderBar(navController = navController)  // ✅ 상단 고정 헤더
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "회원가입",
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(24.dp))

                TextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("아이디") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("비밀번호") },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                errorMessage?.let {
                    Text(
                        text = it,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }

                Button(
                    onClick = {
                        if (username.isBlank() || password.isBlank()) {
                            errorMessage = "아이디와 비밀번호를 모두 입력하세요."
                            return@Button
                        }

                        val request = SignupRequest(username, password)
                        RetrofitInstance.authApi.signup(request).enqueue(object : Callback<Void> {
                            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                                if (response.isSuccessful) {
                                    navController.navigate("login") {
                                        popUpTo("signup") { inclusive = true }
                                    }
                                } else {
                                    errorMessage = "이미 존재하는 아이디입니다."
                                }
                            }

                            override fun onFailure(call: Call<Void>, t: Throwable) {
                                errorMessage = "서버 오류: ${t.localizedMessage}"
                            }
                        })
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("회원가입")
                }
            }
        }
    )
}
