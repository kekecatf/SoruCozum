package com.example.sinavavhazirliguygulama.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AnaSayfa(onNavigate: (String) -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Başlık
            Text(
                text = "Sınav Hazırlık Uygulaması",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 24.dp)
            )

            // Ana içerik alanı (2x2 Grid)
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Üst satır - Test Seçimi ve Rastgele Sorular
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Test Seçimi
                    MenuCardLarge(
                        title = "Test Seçimi",
                        icon = Icons.Default.MenuBook,
                        color = Color(0xFF4285F4),
                        modifier = Modifier.weight(1f),
                        onClick = { onNavigate("testSecimi") }
                    )

                    // Rastgele Sorular
                    MenuCardLarge(
                        title = "Rastgele Sorular",
                        icon = Icons.Default.Shuffle,
                        color = Color(0xFF0F9D58),
                        modifier = Modifier.weight(1f),
                        onClick = { onNavigate("rastgeleSorular") }
                    )
                }

                // Alt satır - Yanlış Sorular ve İstatistikler
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Yanlış Sorular
                    MenuCardLarge(
                        title = "Yanlış Sorular",
                        icon = Icons.Default.Close,
                        color = Color(0xFFDB4437),
                        modifier = Modifier.weight(1f),
                        onClick = { onNavigate("yanlisSorular") }
                    )

                    // İstatistikler
                    MenuCardLarge(
                        title = "İstatistikler",
                        icon = Icons.Default.BarChart,
                        color = Color(0xFFF4B400),
                        modifier = Modifier.weight(1f),
                        onClick = { onNavigate("istatistikler") }
                    )
                }
            }

            // Alt bar (Ayarlar ve Yardım)
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                shape = RoundedCornerShape(28.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    // Ayarlar butonu
                    MenuCardSmall(
                        title = "Ayarlar",
                        icon = Icons.Default.Settings,
                        color = Color(0xFF673AB7),
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 8.dp),
                        onClick = { onNavigate("ayarlar") }
                    )

                    // Yardım butonu
                    MenuCardSmall(
                        title = "Yardım",
                        icon = Icons.Default.Help,
                        color = Color(0xFF9E9E9E),
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 8.dp),
                        onClick = { onNavigate("yardim") }
                    )
                }
            }
        }
    }
}

@Composable
fun MenuCardLarge(
    title: String,
    icon: ImageVector,
    color: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .height(220.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(containerColor = color.copy(alpha = 0.1f)),
        border = ButtonDefaults.outlinedButtonBorder
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = color,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun MenuCardSmall(
    title: String,
    icon: ImageVector,
    color: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .height(60.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = ButtonDefaults.outlinedButtonBorder
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = color,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnaSayfaPreview() {
    MaterialTheme {
        AnaSayfa(onNavigate = {})
    }
}