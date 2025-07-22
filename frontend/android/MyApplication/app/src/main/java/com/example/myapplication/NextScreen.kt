package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun NextScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // 헤더
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
                    modifier = Modifier.size(24.dp)
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

            Spacer(modifier = Modifier.height(32.dp))

            // 게시물 리스트
            PostItem(title = "디지스트 최고의 교수님", time = "1d")
            Spacer(modifier = Modifier.height(24.dp))
            PostItem(title = "디지스트 최악의 교수님", time = "2d")
            Spacer(modifier = Modifier.height(24.dp))
            PostItem(title = "디지스트 전공 추천", time = "1w")
        }

        // 하단 입력창
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(Color.White)
                .padding(bottom = 16.dp)
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
                Text(
                    text = "새로운 채팅을 입력하세요.",
                    fontSize = 14.sp,
                    color = Color(0xFF828282),
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Image(
                    painter = painterResource(id = R.drawable.mic),
                    contentDescription = "Mic",
                    modifier = Modifier.size(24.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.image),
                    contentDescription = "Image",
                    modifier = Modifier.size(24.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.group_1),
                    contentDescription = "Group",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            navController.navigate("chat") {
                                popUpTo("chat") { inclusive = true }
                                launchSingleTop = true
                            }
                        }
                )
            }
        }
    }
}

@Composable
fun PostItem(title: String, time: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 4.dp, bottom = 2.dp)
        )
        Text(
            text = time,
            fontSize = 16.sp,
            color = Color(0x80000000),
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 4.dp),
            maxLines = 1
        )
    }
}