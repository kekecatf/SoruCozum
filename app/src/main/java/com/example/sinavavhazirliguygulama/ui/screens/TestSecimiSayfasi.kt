package com.example.sinavavhazirliguygulama.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sinavavhazirliguygulama.data.model.Ders
import com.example.sinavavhazirliguygulama.data.model.Konu


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestSecimiSayfasi(onNavigateToTest: (Ders, Konu) -> Unit, onBackClick: () -> Unit) {
    val dersler = remember {
        listOf(
            Ders(
                id = 1,
                ad = "Sözel Yetenek",
                icon = Icons.Default.MenuBook,
                color = Color(0xFF4285F4),
                konular = listOf(
                    Konu(1, "Paragraf"),
                    Konu(2, "Dil Bilgisi"),
                    Konu(3, "Sözcük Bilgisi"),
                    Konu(4, "Cümle Bilgisi"),
                    Konu(5, "Anlatım Bozuklukları")
                )
            ),
            Ders(
                id = 2,
                ad = "Sayısal Yetenek",
                icon = Icons.Default.Calculate,
                color = Color(0xFF0F9D58),
                konular = listOf(
                    Konu(1, "Temel Matematik"),
                    Konu(2, "Sayılar"),
                    Konu(3, "Problemler"),
                    Konu(4, "Geometri"),
                    Konu(5, "Olasılık")
                )
            ),
            Ders(
                id = 3,
                ad = "Tarih",
                icon = Icons.Default.History,
                color = Color(0xFFDB4437),
                konular = listOf(
                    Konu(1, "İlk Çağ Tarihi"),
                    Konu(2, "Orta Çağ Tarihi"),
                    Konu(3, "Yeni Çağ Tarihi"),
                    Konu(4, "Yakın Çağ Tarihi"),
                    Konu(5, "Türk İnkılap Tarihi")
                )
            ),
            Ders(
                id = 4,
                ad = "Türkiye Coğrafyası",
                icon = Icons.Default.Public,
                color = Color(0xFFF4B400),
                konular = listOf(
                    Konu(1, "Türkiye'nin Fiziki Coğrafyası"),
                    Konu(2, "İklim ve Bitki Örtüsü"),
                    Konu(3, "Nüfus ve Yerleşme"),
                    Konu(4, "Ekonomik Coğrafya"),
                    Konu(5, "Bölgeler")
                )
            ),
            Ders(
                id = 5,
                ad = "Eğitimin Temelleri",
                icon = Icons.Default.School,
                color = Color(0xFF673AB7),
                konular = listOf(
                    Konu(1, "Eğitim Felsefesi"),
                    Konu(2, "Eğitim Sosyolojisi"),
                    Konu(3, "Eğitim Psikolojisi"),
                    Konu(4, "Eğitim Programları"),
                    Konu(5, "Öğretim İlke ve Yöntemleri")
                )
            ),
            Ders(
                id = 6,
                ad = "Mevzuat",
                icon = Icons.Default.Gavel,
                color = Color(0xFF9E9E9E),
                konular = listOf(
                    Konu(1, "Anayasa"),
                    Konu(2, "Eğitim Hukuku"),
                    Konu(3, "Personel Kanunu"),
                    Konu(4, "Memur Disiplin Mevzuatı"),
                    Konu(5, "Etik ve İnsan Hakları")
                )
            )
        )
    }

    var seciliDers by remember { mutableStateOf<Ders?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        seciliDers?.ad ?: "Test Seçimi",
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        if (seciliDers != null) {
                            seciliDers = null
                        } else {
                            onBackClick()
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Geri"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (seciliDers == null) {
                // Dersler listesi
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(dersler) { ders ->
                        DersCard(
                            ders = ders,
                            onClick = { seciliDers = ders }
                        )
                    }
                }
            } else {
                // Seçilen dersin konuları
                seciliDers?.let { ders ->
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(ders.konular) { konu ->
                            KonuCard(
                                konu = konu,
                                dersColor = ders.color,
                                onClick = { onNavigateToTest(ders, konu) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DersCard(ders: Ders, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = ders.color.copy(alpha = 0.8f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = ders.icon,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = ders.ad,
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}

@Composable
fun KonuCard(konu: Konu, dersColor: Color, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .padding(4.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = konu.id.toString(),
                    color = dersColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = konu.ad,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = null,
                tint = dersColor
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TestSecimiSayfasiPreview() {
    MaterialTheme {
        TestSecimiSayfasi(
            onNavigateToTest = { _, _ -> },
            onBackClick = {}
        )
    }
}