package com.example.myapplication.AnaSayfaDenemeler

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
fun MainScreenWithAnnouncements() {
    Scaffold(
        topBar = { AppTopBarWithLogo() },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                AnnouncementCard(
                    title = "Günün Testi",
                    description = "Bugünün Türkçe testine katıl ve puanını hemen öğren!"
                )
                Spacer(modifier = Modifier.height(16.dp))
                TestOptions()
            }
        },
        bottomBar = { QuickAccessBar() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBarWithLogo() {
    TopAppBar(
        title = {
            Text("KPSS Hazırlık", fontWeight = FontWeight.Bold)
        },
        actions = {
            IconButton(onClick = { /* Profil ya da ayarlar */ }) {
                Icon(Icons.Default.Person, contentDescription = "Profil")
            }
        }
    )
}

@Composable
fun AnnouncementCard(title: String, description: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.elevatedCardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(text = description, fontSize = 14.sp, color = Color.DarkGray)
        }
    }
}

@Composable
fun TestOptions() {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TestButton(
            title = "Derslere Göre Test",
            icon = Icons.Default.CheckCircle,
            color = Color(0xFF81C784)
        )
        TestButton(
            title = "Deneme Sınavı",
            icon = Icons.Default.Create,
            color = Color(0xFF64B5F6)
        )
        TestButton(
            title = "Soru Analizleri",
            icon = Icons.Default.Info,
            color = Color(0xFFFFB74D)
        )
    }
}

@Composable
fun TestButton(title: String, icon: androidx.compose.ui.graphics.vector.ImageVector, color: Color) {
    Button(
        onClick = { /* Aksiyon */ },
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        colors = ButtonDefaults.buttonColors(containerColor = color),
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(icon, contentDescription = title, tint = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = title, color = Color.White, fontSize = 16.sp)
        }
    }
}

@Composable
fun QuickAccessBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF1E88E5))
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        QuickAccessIcon(Icons.Default.Home, "Ana Sayfa")
        QuickAccessIcon(Icons.Default.Notifications, "Bildirimler")
        QuickAccessIcon(Icons.Default.Info, "İstatistik")
        QuickAccessIcon(Icons.Default.Settings, "Ayarlar")
    }
}

@Composable
fun QuickAccessIcon(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            icon,
            contentDescription = label,
            tint = Color.White,
            modifier = Modifier
                .size(24.dp)
                .background(color = Color.Transparent, shape = CircleShape)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = label, fontSize = 10.sp, color = Color.White)
    }
}
