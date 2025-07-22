package com.example.myapplication.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.util.TokenManager

@Composable
fun HeaderBar(
    navController: NavController,
    username: String? = null, // ← 새로 추가된 파라미터
    onMenuClick: (() -> Unit)? = null,
    showProfileIcon: Boolean = true
) {
    val context = LocalContext.current
    val username = TokenManager.getUsername(context)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // 메뉴 아이콘
        Image(
            painter = painterResource(id = R.drawable.menu),
            contentDescription = "Menu",
            modifier = Modifier
                .size(24.dp)
                .clickable { onMenuClick?.invoke() }
        )

        // 로고 텍스트
        Text(
            text = "DGlife",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier.clickable {
                navController.navigate("main_menu") {
                    popUpTo("main_menu") { inclusive = false }
                    launchSingleTop = true
                }
            }
        )

        // 프로필 + 사용자 이름 또는 비로그인
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (showProfileIcon) {
                Image(
                    painter = painterResource(id = R.drawable.profile_image),
                    contentDescription = "Profile",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = if (!username.isNullOrBlank()) "${username}님" else "비로그인",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            } else {
                Spacer(modifier = Modifier.width(24.dp))
            }
        }
    }
}
