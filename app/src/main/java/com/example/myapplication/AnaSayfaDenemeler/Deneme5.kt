package com.example.myapplication.AnaSayfaDenemeler

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainScreenMotivation() {
    Scaffold(
        topBar = { AppTopBarMotivation() },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                MotivationalQuoteCard()
                Spacer(modifier = Modifier.height(16.dp))
                PersonalGoalsSection()
                Spacer(modifier = Modifier.height(16.dp))
                QuickTestOptionsMotivation()
            }
        },
        bottomBar = { BottomNavigationBarMotivation() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBarMotivation() {
    TopAppBar(
        title = {
            Text(
                text = "KPSS Motivasyon",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        },
        actions = {
            IconButton(onClick = { /* Bildirimler */ }) {
                Icon(Icons.Default.Notifications, contentDescription = "Bildirimler")
            }
        }
    )
}

@Composable
fun MotivationalQuoteCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.elevatedCardElevation(6.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFBBDEFB))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "\"Başarı, düzenli bir çalışmanın sonucudur.\"",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1976D2)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Bugün bir adım daha atmaya ne dersin?",
                fontSize = 14.sp,
                color = Color.DarkGray
            )
        }
    }
}

@Composable
fun PersonalGoalsSection() {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.elevatedCardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8BBD0))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Hedeflerim",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFC2185B)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "- Bugün 30 soru çöz", fontSize = 14.sp, color = Color.DarkGray)
            Text(text = "- Tarih testinde 20 doğru yap", fontSize = 14.sp, color = Color.DarkGray)
            Text(text = "- Matematik tekrarını bitir", fontSize = 14.sp, color = Color.DarkGray)
        }
    }
}

@Composable
fun QuickTestOptionsMotivation() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Hızlı Test Seçenekleri",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        TestButton("Genel Kültür", Icons.Default.Create, Color(0xFF81D4FA))
        TestButton("Genel Yetenek", Icons.Default.Create, Color(0xFF4FC3F7))
        TestButton("Eğitim Bilimleri", Icons.Default.Create, Color(0xFF29B6F6))
    }
}

@Composable
fun BottomNavigationBarMotivation() {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Ana Sayfa") },
            label = { Text("Ana Sayfa") },
            selected = true,
            onClick = { /* Ana Sayfa */ }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Star, contentDescription = "Hedeflerim") },
            label = { Text("Hedeflerim") },
            selected = false,
            onClick = { /* Hedefler */ }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Settings, contentDescription = "Ayarlar") },
            label = { Text("Ayarlar") },
            selected = false,
            onClick = { /* Ayarlar */ }
        )
    }
}
