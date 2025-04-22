package com.example.sinavavhazirliguygulama.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AyarlarSayfasi(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Ayarlar",
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
                .verticalScroll(scrollState)
        ) {
            // Kullanıcı bilgileri
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Profil avatarı
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .padding(4.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            modifier = Modifier.size(40.dp),
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    // Kullanıcı adı ve e-posta
                    Column {
                        Text(
                            text = "Kullanıcı Adı",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "kullanici@email.com",
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    // Düzenle butonu
                    IconButton(onClick = { /* Profil düzenleme */ }) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Profili Düzenle",
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            }

            // Ayarlar başlıkları
            Text(
                text = "Uygulama Ayarları",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            // Bildirimler
            var bildirimlerAktif by remember { mutableStateOf(true) }
            AyarSecenegi(
                icon = Icons.Default.Notifications,
                baslik = "Bildirimler",
                aciklama = "Bildirim ayarlarını düzenle",
                rightContent = {
                    Switch(
                        checked = bildirimlerAktif,
                        onCheckedChange = { bildirimlerAktif = it }
                    )
                },
                onClick = {}
            )

            // Tema ayarları
            AyarSecenegi(
                icon = Icons.Default.DarkMode,
                baslik = "Tema",
                aciklama = "Koyu veya açık tema seçin",
                rightContent = {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = null
                    )
                },
                onClick = { /* Tema seçim diyaloğu */ }
            )

            // Ses ayarları
            var sesAktif by remember { mutableStateOf(true) }
            AyarSecenegi(
                icon = Icons.Default.VolumeUp,
                baslik = "Ses",
                aciklama = "Uygulama seslerini aç/kapat",
                rightContent = {
                    Switch(
                        checked = sesAktif,
                        onCheckedChange = { sesAktif = it }
                    )
                },
                onClick = {}
            )

            // Yazı boyutu
            AyarSecenegi(
                icon = Icons.Default.FormatSize,
                baslik = "Yazı Boyutu",
                aciklama = "Metin boyutunu ayarla",
                rightContent = {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = null
                    )
                },
                onClick = { /* Yazı boyutu seçenekleri */ }
            )

            Divider(modifier = Modifier.padding(vertical = 8.dp))

            // Genel başlığı
            Text(
                text = "Genel",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            // Veri yedekleme
            AyarSecenegi(
                icon = Icons.Default.Backup,
                baslik = "Veri Yedekleme",
                aciklama = "İlerlemeni yedekle veya geri yükle",
                rightContent = {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = null
                    )
                },
                onClick = { /* Yedekleme seçenekleri */ }
            )

            // Çevrimdışı çalışma
            var cevrimdisiAktif by remember { mutableStateOf(false) }
            AyarSecenegi(
                icon = Icons.Default.CloudOff,
                baslik = "Çevrimdışı Çalışma",
                aciklama = "İnternet olmadan soruları kullanabilme",
                rightContent = {
                    Switch(
                        checked = cevrimdisiAktif,
                        onCheckedChange = { cevrimdisiAktif = it }
                    )
                },
                onClick = {}
            )

            // Veri temizleme
            AyarSecenegi(
                icon = Icons.Default.DeleteSweep,
                baslik = "Veri Temizleme",
                aciklama = "Önbelleği ve uygulama verilerini temizle",
                rightContent = {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = null
                    )
                },
                onClick = { /* Veri temizleme diyaloğu */ }
            )

            Divider(modifier = Modifier.padding(vertical = 8.dp))

            // Hakkında ve Yardım başlığı
            Text(
                text = "Hakkında ve Yardım",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            // Yardım
            AyarSecenegi(
                icon = Icons.Default.Help,
                baslik = "Yardım",
                aciklama = "Sık sorulan sorular ve destek",
                rightContent = {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = null
                    )
                },
                onClick = { /* Yardım sayfası */ }
            )

            // Uygulama hakkında
            AyarSecenegi(
                icon = Icons.Default.Info,
                baslik = "Uygulama Hakkında",
                aciklama = "Versiyon bilgisi ve geliştiriciler",
                rightContent = {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = null
                    )
                },
                onClick = { /* Hakkında diyaloğu */ }
            )

            // Geri bildirim
            AyarSecenegi(
                icon = Icons.Default.Feedback,
                baslik = "Geri Bildirim",
                aciklama = "Önerilerinizi paylaşın",
                rightContent = {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = null
                    )
                },
                onClick = { /* Geri bildirim formu */ }
            )

            // Uygulama puanla
            AyarSecenegi(
                icon = Icons.Default.Star,
                baslik = "Uygulamayı Puanla",
                aciklama = "Play Store'da puanlayın",
                rightContent = {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = null
                    )
                },
                onClick = { /* Play Store yönlendirmesi */ }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Çıkış yap
            Button(
                onClick = { /* Çıkış işlemi */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Logout,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Çıkış Yap",
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Uygulama versiyonu
            Text(
                text = "Versiyon 1.0.0",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun AyarSecenegi(
    icon: ImageVector,
    baslik: String,
    aciklama: String,
    rightContent: @Composable () -> Unit,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = baslik,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = aciklama,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        rightContent()
    }
}

@Preview(showBackground = true)
@Composable
fun AyarlarSayfasiPreview() {
    MaterialTheme {
        AyarlarSayfasi(
            onBackClick = {}
        )
    }
}