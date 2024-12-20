package com.example.myapplication.AnaSayfaDenemeler

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainScreenWithCategories() {
    Scaffold(
        topBar = { AppTopBarWithGreeting(userName = "Ahmet") },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Hangi dersi çalışmak istersin?",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                CategoryCards()
            }
        },
        bottomBar = { BottomNavigationBar3() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBarWithGreeting(userName: String) {
    TopAppBar(
        title = { Text("KPSS Soru Çözüm Uygulaması") },
        actions = {
            Text(
                text = "Merhaba, $userName!",
                fontSize = 14.sp,
                color = Color.White,
                modifier = Modifier.padding(end = 16.dp)
            )
        }
    )
}

@Composable
fun CategoryCards() {
    val categories = listOf(
        "Türkçe" to Icons.Default.Edit,
        "Matematik" to Icons.Default.Edit,
        "Tarih" to Icons.Default.Edit,
        "Coğrafya" to Icons.Default.Edit,
        "Vatandaşlık" to Icons.Default.Edit
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        categories.forEach { (category, icon) ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .clickable { /* Ders seçimine yönlendirme */ },
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.elevatedCardElevation(4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(icon, contentDescription = category, modifier = Modifier.size(40.dp))
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = category,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar3() {
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
            icon = { Icon(Icons.Default.Notifications, contentDescription = "Bildirimler") },
            label = { Text("Bildirimler") },
            selected = false,
            onClick = { /* Bildirimler */ }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Settings, contentDescription = "Ayarlar") },
            label = { Text("Ayarlar") },
            selected = false,
            onClick = { /* Ayarlar */ }
        )
    }
}
