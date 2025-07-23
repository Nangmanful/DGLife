// MainActivity.kt
package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.*
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                var isReady by remember { mutableStateOf(false) }

                LaunchedEffect(Unit) {
                    delay(1000)
                    isReady = true
                }

                if (isReady) {
                    val navController = rememberNavController()

                    NavHost(navController, startDestination = "main_menu") {
                        composable("main_menu") { MainMenuScreen(navController) }
                        composable("chat") { ChatScreen(navController) }
                        composable("next") { NextScreen(navController) }
                        composable("login") { LoginScreen(navController) }
                        composable("signup") { SignupScreen(navController) }
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("로딩 중...", fontSize = 16.sp, color = Color.Gray)
                    }
                }
            }
        }
    }
}
