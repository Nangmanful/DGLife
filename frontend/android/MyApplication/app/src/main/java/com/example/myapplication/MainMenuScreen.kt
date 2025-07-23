package com.example.myapplication

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.ui.components.HeaderBar

@Composable
fun MainMenuScreen(navController: NavController) {
    Scaffold(
        topBar = {
            HeaderBar(navController = navController)
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("메인 메뉴", fontSize = 24.sp, modifier = Modifier.padding(bottom = 32.dp))

                Button(
                    onClick = { navController.navigate("chat") },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
                ) {
                    Text("채팅 화면으로 이동")
                }

                Button(
                    onClick = { navController.navigate("login") },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
                ) {
                    Text("로그인 화면으로 이동")
                }

                Button(
                    onClick = { navController.navigate("signup") },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("회원가입 화면으로 이동")
                }
            }
        }
    )
}
