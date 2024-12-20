package com.example.myapplication

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    Scaffold(
        bottomBar = { BottomNavigationBar1() } // Alt Menü
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Logo
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logobeyaz), // Logonuzun ID'si
                    contentDescription = "Logo",
                    modifier = Modifier.size(120.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = ("Konular"),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black
            )

            // Kategori Listesi
            val categories = listOf(
                "Tarih",
                "Coğrafya",
                "Vatandaşlık",
                "Türkçe",
                "Güncel Bilgiler",
                "Eğitim Bilimleri",
                "Tarih Soru Bankası",
                "Coğrafya Soru Bankası",
                "Vatandaşlık Soru Bankası",
                "Soru Cevap Denemeleri",
                "Yanlış Yaptığın Sorular"
            )
            LazyColumn(
                modifier = Modifier.padding(bottom = 40.dp)
            ) {
                items(categories) { category ->
                    CategoryRow4(category)
                }
            }
        }
    }
}

@Composable
fun CategoryRow4(category: String) {
    //Kategori barı
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(Color(0xFF605EB5), shape = RoundedCornerShape(4.dp))
            .clickable { /* Kategoriye tıklanınca yapılacak işlem */ }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = category,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White
        )

        //Kategorilerin yanındaki başla buttonu
        Button(
            onClick = { /* Başla butonuna tıklanınca yapılacak işlem */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF9800))
        ) {
            Text(text = "Başla", color = Color.White)
        }
    }
}

@Composable
fun BottomNavigationBar1() {
    val items = listOf(
        NavigationItem1("Profil", Icons.Default.Person), // Material Icon
        NavigationItem1("Kısa Notlar", Icons.Default.Create), // Material Icon
        NavigationItem1("Puan Tablosu", Icons.Default.Check), // Material Icon
        NavigationItem1("Ayarlar", Icons.Default.Settings) // Material Icon
    )

    NavigationBar(
        containerColor = Color(0xFF605EB5), // Arka plan rengi
        modifier = Modifier.height(56.dp) // Yüksekliği sabitleyin
    ) {
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon, // Material Icon kullanımı
                        contentDescription = item.title,
                        modifier = Modifier.size(24.dp), // İkon boyutu
                        tint = Color.White // İkon rengi
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        color = Color.White, // Metin rengi
                        fontSize = 12.sp // Metin boyutu
                    )
                },
                selected = false, // Seçim durumu
                onClick = {
                    // Tıklama işlevi
                }
            )
        }
    }
}

data class NavigationItem1(val title: String, val icon: ImageVector)
