// ChatScreen.kt
package com.example.myapplication

import com.example.myapplication.network.ChatRequest
import com.example.myapplication.network.ChatResponse
import com.example.myapplication.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ChatScreen(navController: NavController) {
    data class ChatMessage(val content: String, val isUser: Boolean)
    val messages = remember { mutableStateListOf<ChatMessage>() }
    var inputText by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(8.dp))
            HeaderBar(onMenuClick = { navController.navigate("next") },
                navController = navController
                )
            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                messages.forEach { msg ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                        horizontalArrangement = if (msg.isUser) Arrangement.End else Arrangement.Start
                    ) {
                        if (!msg.isUser) {
                            Image(
                                painter = painterResource(id = R.drawable.image_2),
                                contentDescription = "GPT Icon",
                                modifier = Modifier
                                    .size(24.dp)
                                    .padding(end = 4.dp)
                            )
                        }

                        Box(
                            modifier = Modifier
                                .background(
                                    if (msg.isUser) Color.Black else Color(0xFFF5F5F5),
                                    shape = RoundedCornerShape(18.dp)
                                )
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                        ) {
                            Text(
                                text = msg.content,
                                color = if (msg.isUser) Color.White else Color.Black,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        ) {
            ChatInputBar(
                inputText = inputText,
                onTextChange = { inputText = it },
                onSendClick = {
                    if (inputText.isNotBlank()) {
                        val userMessage = inputText
                        messages.add(ChatMessage(userMessage, isUser = true)) // 사용자 메시지
                        inputText = ""

                        val request = ChatRequest(message = userMessage)
                        RetrofitInstance.api.sendMessage(request).enqueue(object : Callback<ChatResponse> {
                            override fun onResponse(call: Call<ChatResponse>, response: Response<ChatResponse>) {
                                val reply = response.body()?.reply ?: "응답 오류"
                                messages.add(ChatMessage(reply, isUser = false)) // ✅ GPT 응답도 같은 구조로 추가
                            }

                            override fun onFailure(call: Call<ChatResponse>, t: Throwable) {
                                messages.add(ChatMessage("❗ 서버 연결 실패: ${t.message}", isUser = false)) // ✅ 실패도 같은 구조로
                            }
                        })
                    }
                }


            )
        }
    }
}

@Composable
fun HeaderBar(onMenuClick: () -> Unit, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(top = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = R.drawable.menu),
            contentDescription = "Menu",
            modifier = Modifier
                .size(24.dp)
                .clickable { onMenuClick() }
        )
        Text(
            text = "DGlife",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.clickable {
                navController.navigate("main_menu") {
                    popUpTo("main_menu") { inclusive = false }
                    launchSingleTop = true
                }
            }
        )
        Image(
            painter = painterResource(id = R.drawable.profile_image),
            contentDescription = "Profile",
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun ChatInputBar(
    inputText: String,
    onTextChange: (String) -> Unit,
    onSendClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(Color.White, RoundedCornerShape(8.dp))
            .border(1.dp, Color(0xFFDFDFDF), RoundedCornerShape(8.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = inputText,
            onValueChange = onTextChange,
            placeholder = { Text("채팅을 입력하세요.") },
            modifier = Modifier.weight(1f),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            maxLines = 1
        )
        Spacer(modifier = Modifier.width(4.dp))
        Image(
            painter = painterResource(id = R.drawable.group_1),
            contentDescription = "Send",
            modifier = Modifier
                .size(24.dp)
                .clickable { onSendClick() }
        )
    }
}
