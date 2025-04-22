package com.example.sinavavhazirliguygulama.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sinavavhazirliguygulama.data.model.DersIstatistik
import java.util.*

enum class IstatistikTipi {
    GÜNLÜK, HAFTALIK, AYLIK
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IstatistikSayfasi(onBackClick: () -> Unit) {
    // Varsayılan olarak günlük istatistikleri seçili olarak başlatıyoruz
    var seciliIstatistikTipi by remember { mutableStateOf(IstatistikTipi.GÜNLÜK) }

    // Gerçek bir uygulamada, bu veriler bir veritabanından gelecektir
    // Şimdilik örnek verileri kullanıyoruz
    val dersIstatistikleri = when (seciliIstatistikTipi) {
        IstatistikTipi.GÜNLÜK -> listOf(
            DersIstatistik(
                dersAdi = "Sözel Yetenek",
                icon = Icons.Default.MenuBook,
                renk = Color(0xFF4285F4),
                dogruSayisi = 15,
                yanlisSayisi = 5,
                toplamSure = 35,
                tamamlananTest = 2
            ),
            DersIstatistik(
                dersAdi = "Tarih",
                icon = Icons.Default.History,
                renk = Color(0xFFDB4437),
                dogruSayisi = 20,
                yanlisSayisi = 10,
                toplamSure = 45,
                tamamlananTest = 3
            )
        )
        IstatistikTipi.HAFTALIK -> listOf(
            DersIstatistik(
                dersAdi = "Sözel Yetenek",
                icon = Icons.Default.MenuBook,
                renk = Color(0xFF4285F4),
                dogruSayisi = 65,
                yanlisSayisi = 25,
                toplamSure = 180,
                tamamlananTest = 9
            ),
            DersIstatistik(
                dersAdi = "Tarih",
                icon = Icons.Default.History,
                renk = Color(0xFFDB4437),
                dogruSayisi = 85,
                yanlisSayisi = 35,
                toplamSure = 210,
                tamamlananTest = 12
            ),
            DersIstatistik(
                dersAdi = "Türkiye Coğrafyası",
                icon = Icons.Default.Public,
                renk = Color(0xFFF4B400),
                dogruSayisi = 40,
                yanlisSayisi = 20,
                toplamSure = 120,
                tamamlananTest = 6
            )
        )
        IstatistikTipi.AYLIK -> listOf(
            DersIstatistik(
                dersAdi = "Sözel Yetenek",
                icon = Icons.Default.MenuBook,
                renk = Color(0xFF4285F4),
                dogruSayisi = 200,
                yanlisSayisi = 95,
                toplamSure = 750,
                tamamlananTest = 30
            ),
            DersIstatistik(
                dersAdi = "Sayısal Yetenek",
                icon = Icons.Default.Calculate,
                renk = Color(0xFF0F9D58),
                dogruSayisi = 150,
                yanlisSayisi = 75,
                toplamSure = 600,
                tamamlananTest = 25
            ),
            DersIstatistik(
                dersAdi = "Tarih",
                icon = Icons.Default.History,
                renk = Color(0xFFDB4437),
                dogruSayisi = 180,
                yanlisSayisi = 60,
                toplamSure = 680,
                tamamlananTest = 24
            ),
            DersIstatistik(
                dersAdi = "Türkiye Coğrafyası",
                icon = Icons.Default.Public,
                renk = Color(0xFFF4B400),
                dogruSayisi = 160,
                yanlisSayisi = 70,
                toplamSure = 620,
                tamamlananTest = 23
            ),
            DersIstatistik(
                dersAdi = "Eğitimin Temelleri",
                icon = Icons.Default.School,
                renk = Color(0xFF673AB7),
                dogruSayisi = 130,
                yanlisSayisi = 90,
                toplamSure = 580,
                tamamlananTest = 22
            ),
            DersIstatistik(
                dersAdi = "Mevzuat",
                icon = Icons.Default.Gavel,
                renk = Color(0xFF9E9E9E),
                dogruSayisi = 110,
                yanlisSayisi = 60,
                toplamSure = 480,
                tamamlananTest = 17
            )
        )
    }

    // Toplam istatistikler
    val toplamDogruSayisi = dersIstatistikleri.sumOf { it.dogruSayisi }
    val toplamYanlisSayisi = dersIstatistikleri.sumOf { it.yanlisSayisi }
    val toplamSorularSayisi = toplamDogruSayisi + toplamYanlisSayisi
    val toplamBasariYuzdesi = if (toplamSorularSayisi > 0)
        (toplamDogruSayisi.toFloat() / toplamSorularSayisi.toFloat()) * 100
    else 0f
    val toplamSure = dersIstatistikleri.sumOf { it.toplamSure }
    val toplamTamamlananTest = dersIstatistikleri.sumOf { it.tamamlananTest }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "İstatistikler",
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Geri"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Zaman filtresi (Günlük, Haftalık, Aylık)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TabButton(
                    text = "Günlük",
                    selected = seciliIstatistikTipi == IstatistikTipi.GÜNLÜK,
                    onClick = { seciliIstatistikTipi = IstatistikTipi.GÜNLÜK },
                    modifier = Modifier.weight(1f)
                )
                TabButton(
                    text = "Haftalık",
                    selected = seciliIstatistikTipi == IstatistikTipi.HAFTALIK,
                    onClick = { seciliIstatistikTipi = IstatistikTipi.HAFTALIK },
                    modifier = Modifier.weight(1f)
                )
                TabButton(
                    text = "Aylık",
                    selected = seciliIstatistikTipi == IstatistikTipi.AYLIK,
                    onClick = { seciliIstatistikTipi = IstatistikTipi.AYLIK },
                    modifier = Modifier.weight(1f)
                )
            }

            // Tarih başlık
            Text(
                text = getTarihBasligi(seciliIstatistikTipi),
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Ana istatistikler
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Genel Performans",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        // Başarı yüzdesi
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = String.format("%.1f%%", toplamBasariYuzdesi),
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                text = "Başarı",
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
                            )
                        }

                        // Çizgi ayırıcı
                        Box(
                            modifier = Modifier
                                .width(1.dp)
                                .height(40.dp)
                                .background(MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.2f))
                        )

                        // Toplam çözülen soru
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "$toplamSorularSayisi",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                text = "Soru",
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
                            )
                        }

                        // Çizgi ayırıcı
                        Box(
                            modifier = Modifier
                                .width(1.dp)
                                .height(40.dp)
                                .background(MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.2f))
                        )

                        // Tamamlanan test
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "$toplamTamamlananTest",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                text = "Test",
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // İlerleme çubuğu
                    LinearProgressIndicator(
                        progress = toplamBasariYuzdesi / 100f,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(RoundedCornerShape(4.dp))
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Doğru ve yanlış sayıları
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(12.dp)
                                    .background(
                                        color = Color(0xFF4CAF50),
                                        shape = RoundedCornerShape(6.dp)
                                    )
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "$toplamDogruSayisi Doğru",
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(12.dp)
                                    .background(
                                        color = Color(0xFFE57373),
                                        shape = RoundedCornerShape(6.dp)
                                    )
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "$toplamYanlisSayisi Yanlış",
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Toplam çalışma süresi
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Timer,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = formatSure(toplamSure),
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            }

            // Her ders için ayrı istatistikler
            Text(
                text = "Ders Bazında İstatistikler",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            dersIstatistikleri.forEach { dersIstatistik ->
                // Her ders için kart
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        // Ders adı ve icon
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .background(
                                        color = dersIstatistik.renk.copy(alpha = 0.2f),
                                        shape = RoundedCornerShape(18.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = dersIstatistik.icon,
                                    contentDescription = null,
                                    tint = dersIstatistik.renk
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = dersIstatistik.dersAdi,
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // İstatistikler
                        val dersBasariYuzdesi = if ((dersIstatistik.dogruSayisi + dersIstatistik.yanlisSayisi) > 0)
                            (dersIstatistik.dogruSayisi.toFloat() / (dersIstatistik.dogruSayisi + dersIstatistik.yanlisSayisi).toFloat()) * 100
                        else 0f

                        // İlerleme çubuğu
                        LinearProgressIndicator(
                            progress = dersBasariYuzdesi / 100f,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(6.dp)
                                .clip(RoundedCornerShape(3.dp)),
                            color = dersIstatistik.renk
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // İstatistik verileri
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            // Başarı yüzdesi
                            Column {
                                Text(
                                    text = String.format("%.1f%%", dersBasariYuzdesi),
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 14.sp
                                )
                                Text(
                                    text = "Başarı",
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }

                            // Doğru/Yanlış
                            Column(
                                horizontalAlignment = Alignment.End
                            ) {
                                Text(
                                    text = "${dersIstatistik.dogruSayisi} Doğru / ${dersIstatistik.yanlisSayisi} Yanlış",
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 14.sp
                                )
                                Text(
                                    text = "Toplam ${dersIstatistik.dogruSayisi + dersIstatistik.yanlisSayisi} Soru",
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        // Süre ve test sayısı
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            // Toplam süre
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Timer,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp),
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = formatSure(dersIstatistik.toplamSure),
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }

                            // Test sayısı
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Assignment,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp),
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "${dersIstatistik.tamamlananTest} Test",
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TabButton(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
    val textColor = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface

    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = text,
            color = textColor,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
        )
    }
}

// Tarih başlığını formatla
fun getTarihBasligi(tip: IstatistikTipi): String {
    val takvim = Calendar.getInstance()
    return when (tip) {
        IstatistikTipi.GÜNLÜK -> "Bugün, ${takvim.get(Calendar.DAY_OF_MONTH)} ${getAyAdi(takvim.get(Calendar.MONTH))} ${takvim.get(Calendar.YEAR)}"
        IstatistikTipi.HAFTALIK -> {
            val hafta = takvim.get(Calendar.WEEK_OF_YEAR)
            "Hafta $hafta, ${takvim.get(Calendar.YEAR)}"
        }
        IstatistikTipi.AYLIK -> "${getAyAdi(takvim.get(Calendar.MONTH))} ${takvim.get(Calendar.YEAR)}"
    }
}

// Ay adlarını döndür
fun getAyAdi(ay: Int): String {
    return when(ay) {
        0 -> "Ocak"
        1 -> "Şubat"
        2 -> "Mart"
        3 -> "Nisan"
        4 -> "Mayıs"
        5 -> "Haziran"
        6 -> "Temmuz"
        7 -> "Ağustos"
        8 -> "Eylül"
        9 -> "Ekim"
        10 -> "Kasım"
        11 -> "Aralık"
        else -> ""
    }
}

// Dakikayı saat ve dakika formatına dönüştür
fun formatSure(dakika: Int): String {
    val saat = dakika / 60
    val kalanDakika = dakika % 60

    return if (saat > 0) {
        "$saat saat $kalanDakika dakika"
    } else {
        "$kalanDakika dakika"
    }
}

@Preview(showBackground = true)
@Composable
fun IstatistikSayfasiPreview() {
    MaterialTheme {
        IstatistikSayfasi(
            onBackClick = {}
        )
    }
}