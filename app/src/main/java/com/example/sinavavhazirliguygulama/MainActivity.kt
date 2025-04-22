package com.example.sinavavhazirliguygulama

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Gavel
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sinavavhazirliguygulama.ui.screens.*
import com.example.sinavavhazirliguygulama.ui.theme.SinavHazirlikUygulamasiTheme
import com.example.sinavavhazirliguygulama.data.model.Ders
import com.example.sinavavhazirliguygulama.data.model.Konu
import com.example.sinavavhazirliguygulama.data.model.Soru
import com.example.sinavavhazirliguygulama.data.model.SoruRepository

class MainActivity : ComponentActivity() {
    private lateinit var soruRepository: SoruRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Repository'yi başlat
        soruRepository = SoruRepository(this)

        setContent {
            SinavHazirlikUygulamasiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Navigation Controller
                    val navController = rememberNavController()

                    // Navigation Host
                    NavHost(
                        navController = navController,
                        startDestination = "anasayfa"
                    ) {
                        // Ana Sayfa
                        composable("anasayfa") {
                            AnaSayfa(onNavigate = { route ->
                                navController.navigate(route)
                            })
                        }

                        // Test Seçimi Sayfası
                        composable("testSecimi") {
                            TestSecimiSayfasi(
                                onNavigateToTest = { ders, konu ->
                                    // Test ekranına giderken ders ve konu bilgisini parametre olarak geçirebiliriz
                                    // Gerçek bir uygulamada bu iletişim ViewModel veya bundle üzerinden yapılırdı
                                    // Burada basitlik için doğrudan yönlendirme yapıyoruz
                                    navController.navigate("soruEkrani/${ders.id}/${konu.id}")
                                },
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        // Rastgele Sorular Sayfası
                        composable("rastgeleSorular") {
                            val rastgeleSorular = soruRepository.getRastgeleSorular()
                            SoruEkrani(
                                ders = "Rastgele",
                                konu = "Karışık Sorular",
                                sorular = rastgeleSorular,
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                onFinishClick = { dogru, toplam ->
                                    navController.navigate("anasayfa") {
                                        popUpTo("anasayfa") { inclusive = true }
                                    }
                                }
                            )
                        }

                        // Yanlış Sorular Sayfası
                        composable("yanlisSorular") {
                            val yanlisSorular = soruRepository.getYanlisSorular()
                            SoruEkrani(
                                ders = "Yanlış Sorular",
                                konu = "Tekrar",
                                sorular = yanlisSorular,
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                onFinishClick = { dogru, toplam ->
                                    navController.navigate("anasayfa") {
                                        popUpTo("anasayfa") { inclusive = true }
                                    }
                                }
                            )
                        }

                        // İstatistikler Sayfası
                        composable("istatistikler") {
                            IstatistikSayfasi(
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        // Ayarlar Sayfası
                        composable("ayarlar") {
                            AyarlarSayfasi(
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        // Soru Ekranı
                        // Burada ders ve konu ID'lerini parametre olarak alıyoruz
                        composable("soruEkrani/{dersId}/{konuId}") { backStackEntry ->
                            val dersId = backStackEntry.arguments?.getString("dersId")?.toIntOrNull() ?: 1
                            val konuId = backStackEntry.arguments?.getString("konuId")?.toIntOrNull() ?: 1

                            val ders = getOrnekDersler().find { it.id == dersId }
                            val konu = ders?.konular?.find { it.id == konuId }

                            if (ders != null && konu != null) {
                                // JSON'dan konuya özel soruları al
                                val sorular = soruRepository.getSorular(dersId, konuId)

                                SoruEkrani(
                                    ders = ders.ad,
                                    konu = konu.ad,
                                    sorular = sorular,
                                    onBackClick = {
                                        navController.popBackStack()
                                    },
                                    onFinishClick = { dogru, toplam ->
                                        navController.popBackStack("testSecimi", false)
                                    }
                                )
                            }
                        }

                        // Yardım Sayfası (ileride eklenebilir)
                        composable("yardim") {
                            // Yardım sayfası burada oluşturulabilir
                        }
                    }
                }
            }
        }
    }

    // Örnek dersler (Geçici - Gerçek uygulamada veritabanından çekilecek)
    private fun getOrnekDersler(): List<Ders> {
        // TestSecimiSayfasi.kt içinde tanımlanan dersleri burada da kullanıyoruz
        return listOf(
            Ders(
                id = 1,
                ad = "Sözel Yetenek",
                icon = androidx.compose.material.icons.Icons.Default.MenuBook,
                color = androidx.compose.ui.graphics.Color(0xFF4285F4),
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
                icon = androidx.compose.material.icons.Icons.Default.Calculate,
                color = androidx.compose.ui.graphics.Color(0xFF0F9D58),
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
                icon = androidx.compose.material.icons.Icons.Default.History,
                color = androidx.compose.ui.graphics.Color(0xFFDB4437),
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
                icon = androidx.compose.material.icons.Icons.Default.Public,
                color = androidx.compose.ui.graphics.Color(0xFFF4B400),
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
                icon = androidx.compose.material.icons.Icons.Default.School,
                color = androidx.compose.ui.graphics.Color(0xFF673AB7),
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
                icon = androidx.compose.material.icons.Icons.Default.Gavel,
                color = androidx.compose.ui.graphics.Color(0xFF9E9E9E),
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
}