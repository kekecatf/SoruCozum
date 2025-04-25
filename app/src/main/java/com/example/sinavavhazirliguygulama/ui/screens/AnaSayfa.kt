package com.example.sinavavhazirliguygulama.ui.screens

import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sinavavhazirliguygulama.R

@Composable
fun AnaSayfa(onNavigate: (String) -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Transparent // Arka planı Image ile veriyoruz
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Arka plan görseli
            Image(
                painter = painterResource(id = R.drawable.background), // background.png drawable'a eklenmeli
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Uygulama içeriği
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
                    modifier = Modifier.padding(vertical = 24.dp),
                    color = Color.White // Görsel üstünde okunaklılık için
                )

                // 2x2 Grid
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        MenuCardLarge(
                            title = "Test Seçimi",
                            icon = Icons.Default.MenuBook,
                            color = Color(0xFF4285F4),
                            modifier = Modifier.weight(1f),
                            onClick = { onNavigate("testSecimi") }
                        )
                        MenuCardLarge(
                            title = "Rastgele Sorular",
                            icon = Icons.Default.Shuffle,
                            color = Color(0xFF0F9D58),
                            modifier = Modifier.weight(1f),
                            onClick = { onNavigate("rastgeleSorular") }
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        MenuCardLarge(
                            title = "Yanlış Sorular",
                            icon = Icons.Default.Close,
                            color = Color(0xFFDB4437),
                            modifier = Modifier.weight(1f),
                            onClick = { onNavigate("yanlisSorular") }
                        )
                        MenuCardLarge(
                            title = "İstatistikler",
                            icon = Icons.Default.BarChart,
                            color = Color(0xFFF4B400),
                            modifier = Modifier.weight(1f),
                            onClick = { onNavigate("istatistikler") }
                        )
                    }
                }

                // Alt Bar
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    shape = RoundedCornerShape(28.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFFFF8E1) // Açık mor/lila tonu (Material Purple 100)
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        MenuCardSmall(
                            title = "Ayarlar",
                            icon = Icons.Default.Settings,
                            color = Color(0xFF673AB7),
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 8.dp),
                            onClick = { onNavigate("ayarlar") }
                        )
                        MenuCardSmall(
                            title = "Yardım",
                            icon = Icons.Default.Help,
                            color = Color(0xFF757575),
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
            .clickable { onClick() },
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(
            containerColor = color // Burayı ekledik
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = Color.Black,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = title,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold
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
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFEDE7F6) // Açık mor/lila tonu (Material Purple 100)
        ),
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