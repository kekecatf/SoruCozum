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

/*
@Composable
fun MainScreenWithStats() {
    Scaffold(
        topBar = { AppTopBarWithWelcome(userName = "Ahmet") },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                UserStatsSection()
                Spacer(modifier = Modifier.height(16.dp))
                QuickTestOptions()
            }
        },
        bottomBar = { BottomNavigationBar4() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBarWithWelcome(userName: String) {
    TopAppBar(
        title = {
            Text(
                text = "KPSS Çalışma",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        },
        actions = {
            Text(
                text = "Hoş geldin, $userName!",
                fontSize = 14.sp,
                color = Color.White,
                modifier = Modifier.padding(end = 16.dp)
            )
        }
    )
}

@Composable
fun UserStatsSection() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.elevatedCardElevation(6.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF3E5F5))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Bugünkü İstatistikler",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF7E57C2)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                StatBox("Çözülen Sorular", "25")
                StatBox("Doğru Cevaplar", "22")
                StatBox("Yanlış Cevaplar", "3")
            }
        }
    }
}

@Composable
fun StatBox(label: String, value: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        Text(text = value, fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color(0xFF512DA8))
        Text(text = label, fontSize = 14.sp, color = Color.Gray)
    }
}

@Composable
fun QuickTestOptions() {
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
        TestButton2("Türkçe Testi", Icons.Default.Create, Color(0xFF81C784))
        TestButton2("Matematik Testi", Icons.Default.Create, Color(0xFF64B5F6))
        TestButton2("Tarih Testi", Icons.Default.Create, Color(0xFFFFB74D))
    }
}

@Composable
fun TestButton2(title: String, icon: androidx.compose.ui.graphics.vector.ImageVector, color: Color) {
    Button(
        onClick = { /* Test seçimine yönlendirme */ },
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        colors = ButtonDefaults.buttonColors(containerColor = color),
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = title, tint = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = title, color = Color.White, fontSize = 16.sp)
        }
    }
}

@Composable
fun BottomNavigationBar4() {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Ana Sayfa") },
            label = { Text("Ana Sayfa") },
            selected = true,
            onClick = { /* Ana Sayfa */ }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Info, contentDescription = "İstatistik") },
            label = { Text("İstatistik") },
            selected = false,
            onClick = { /* İstatistik */ }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Settings, contentDescription = "Ayarlar") },
            label = { Text("Ayarlar") },
            selected = false,
            onClick = { /* Ayarlar */ }
        )
    }
}
*/