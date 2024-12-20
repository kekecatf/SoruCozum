package com.example.myapplication.AnaSayfaDenemeler

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    Scaffold(
        topBar = { AppTopBar() },
        bottomBar = { BottomNavigationBar2() },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Hızlı Başla",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                MainButtons()
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar() {
    TopAppBar(
        title = { Text("KPSS Soru Çözüm Uygulaması") },
        actions = {
            IconButton(onClick = { /* Profil ya da ayarlar ekranına yönlendirme */ }) {
                Icon(Icons.Default.Person, contentDescription = "Profil")
            }
        }
    )
}

@Composable
fun MainButtons() {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { /* Derslere Göre Test */ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE))
        ) {
            Icon(Icons.Default.ArrowDropDown, contentDescription = "Derslere Göre Test")
            Spacer(modifier = Modifier.width(8.dp))
            Text("Derslere Göre Test")
        }
        Button(
            onClick = { /* Deneme Sınavı */ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF018786))
        ) {
            Icon(Icons.Default.Create, contentDescription = "Deneme Sınavı")
            Spacer(modifier = Modifier.width(8.dp))
            Text("Deneme Sınavı")
        }
        Button(
            onClick = { /* Çözüm Analizi */ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF5722))
        ) {
            Icon(Icons.Default.Info, contentDescription = "Çözüm Analizi")
            Spacer(modifier = Modifier.width(8.dp))
            Text("Çözüm Analizi")
        }
        Button(
            onClick = { /* Favori Sorularım */ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFC107))
        ) {
            Icon(Icons.Default.Star, contentDescription = "Favori Sorularım")
            Spacer(modifier = Modifier.width(8.dp))
            Text("Favori Sorularım")
        }
    }
}

@Composable
fun BottomNavigationBar2() {
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
