package com.example.myapplication

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.example.myapplication.network.LoginRequest
import com.example.myapplication.network.LoginResponse
import com.example.myapplication.network.RetrofitInstance
import com.example.myapplication.ui.components.HeaderBar
import com.example.myapplication.util.TokenManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current

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
                Text(text = "로그인", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(24.dp))

                TextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("이메일 또는 아이디") },
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
                        val loginRequest = LoginRequest(username, password)
                        RetrofitInstance.authApi.login(loginRequest).enqueue(object : Callback<LoginResponse> {
                            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                                if (response.isSuccessful) {
                                    val token = response.body()?.token
                                    if (!token.isNullOrEmpty()) {
                                        TokenManager.saveToken(context, token, username)

                                        navController.navigate("main_menu") {
                                            popUpTo("login") { inclusive = true }
                                        }
                                    } else {
                                        errorMessage = "서버 응답 오류: 토큰 없음"
                                    }
                                } else {
                                    errorMessage = "아이디 또는 비밀번호가 잘못되었습니다."
                                }
                            }

                            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                                errorMessage = "서버 연결 실패: ${t.localizedMessage}"
                                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                            }
                        })
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("로그인")
                }
            }
        }
    )
}
