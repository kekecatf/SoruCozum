package com.example.sinavavhazirliguygulama.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sinavavhazirliguygulama.data.model.Soru
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SoruEkrani(
    ders: String,
    konu: String,
    sorular: List<Soru>,
    onBackClick: () -> Unit,
    onFinishClick: (Int, Int) -> Unit // Parametreler: doğru cevap sayısı ve toplam soru sayısı
) {
    // Boş liste kontrolü ekleyin
    if (sorular.isEmpty()) {
        // Boş liste durumunda kullanıcıya bir mesaj gösterin
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = null,
                    modifier = Modifier.size(64.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Henüz yanlış çözülen soru bulunmamaktadır.",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))
                Button(onClick = onBackClick) {
                    Text("Ana Sayfaya Dön")
                }
            }
        }
        return
    }
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var selectedOption by remember { mutableStateOf<Int?>(null) }
    var showExplanation by remember { mutableStateOf(false) }
    var dogruCevapSayisi by remember { mutableStateOf(0) }
    var yanlisCevapSayisi by remember { mutableStateOf(0) }
    var testBitti by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()

    val currentQuestion = sorular[currentQuestionIndex]

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "$ders",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Text(
                            text = konu,
                            fontSize = 14.sp
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Geri"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // İlerleme çubuğu
            LinearProgressIndicator(
                progress = (currentQuestionIndex + 1).toFloat() / sorular.size,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(RoundedCornerShape(4.dp))
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Soru numarası ve kalan soru sayısı
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Soru ${currentQuestionIndex + 1}",
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${currentQuestionIndex + 1}/${sorular.size}",
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (testBitti) {
                // Test sonu ekranı
                TestSonucEkrani(
                    dogruSayisi = dogruCevapSayisi,
                    toplamSoru = sorular.size,
                    onFinishClick = { onFinishClick(dogruCevapSayisi, sorular.size) }
                )
            } else {
                // Soru içeriği
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                ) {
                    // Soru metni
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Text(
                            text = currentQuestion.soruMetni,
                            modifier = Modifier.padding(16.dp),
                            fontSize = 16.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Şıklar
                    currentQuestion.secenekler.forEachIndexed { index, secenek ->
                        val isSelected = selectedOption == index
                        val isCorrect = index == currentQuestion.dogruCevap

                        val backgroundColor = when {
                            !showExplanation -> {
                                if (isSelected) MaterialTheme.colorScheme.primaryContainer
                                else MaterialTheme.colorScheme.surface
                            }
                            isCorrect -> Color(0xFFD3F8D3) // Doğru yanıt rengi (açık yeşil)
                            isSelected -> Color(0xFFFFCCCC) // Yanlış yanıt rengi (açık kırmızı)
                            else -> MaterialTheme.colorScheme.surface
                        }

                        val borderColor = when {
                            !showExplanation && isSelected -> MaterialTheme.colorScheme.primary
                            showExplanation && isCorrect -> Color(0xFF4CAF50) // Koyu yeşil
                            showExplanation && isSelected -> Color(0xFFE57373) // Koyu kırmızı
                            else -> MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
                        }

                        val secenekHarfi = when (index) {
                            0 -> "A"
                            1 -> "B"
                            2 -> "C"
                            3 -> "D"
                            4 -> "E"
                            else -> ""
                        }

                        OutlinedCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp)
                                .clickable(
                                    enabled = !showExplanation && selectedOption == null
                                ) {
                                    selectedOption = index

                                    // Cevabı kontrol et
                                    coroutineScope.launch {
                                        delay(500) // Biraz bekle
                                        showExplanation = true

                                        if (index == currentQuestion.dogruCevap) {
                                            dogruCevapSayisi++
                                        } else {
                                            yanlisCevapSayisi++
                                        }
                                    }
                                },
                            colors = CardDefaults.outlinedCardColors(
                                containerColor = backgroundColor
                            ),
                            border = BorderStroke(
                                width = 1.5.dp,
                                color = borderColor
                            )
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // Şık harfi (A, B, C...)
                                Surface(
                                    modifier = Modifier.size(32.dp),
                                    shape = RoundedCornerShape(16.dp),
                                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Text(
                                            text = secenekHarfi,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.primary
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.width(12.dp))

                                // İçerik
                                Text(text = secenek)
                            }
                        }
                    }

                    // Açıklama
                    AnimatedVisibility(
                        visible = showExplanation,
                        enter = fadeIn(animationSpec = tween(300)),
                        exit = fadeOut(animationSpec = tween(300))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp)
                        ) {
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                                )
                            ) {
                                Column(
                                    modifier = Modifier.padding(16.dp)
                                ) {
                                    Text(
                                        text = "Açıklama",
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSecondaryContainer
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = currentQuestion.aciklama,
                                        color = MaterialTheme.colorScheme.onSecondaryContainer
                                    )
                                }
                            }
                        }
                    }
                }

                // Alt butonlar
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Sıradaki soru butonu
                    AnimatedVisibility(
                        visible = showExplanation,
                        enter = fadeIn(animationSpec = tween(300)),
                        exit = fadeOut(animationSpec = tween(300))
                    ) {
                        Button(
                            onClick = {
                                if (currentQuestionIndex < sorular.size - 1) {
                                    // Sıradaki soruya geç
                                    currentQuestionIndex++
                                    selectedOption = null
                                    showExplanation = false
                                } else {
                                    // Test bitti
                                    testBitti = true
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Text(
                                text = if (currentQuestionIndex < sorular.size - 1) "Sıradaki Soru" else "Testi Bitir",
                                modifier = Modifier.padding(vertical = 4.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TestSonucEkrani(
    dogruSayisi: Int,
    toplamSoru: Int,
    onFinishClick: () -> Unit
) {
    val basariYuzdesi = (dogruSayisi.toFloat() / toplamSoru) * 100
    val yuzdeText = String.format("%.1f", basariYuzdesi)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        Icon(
            imageVector = if (basariYuzdesi >= 50) Icons.Default.CheckCircle else Icons.Default.Info,
            contentDescription = null,
            tint = if (basariYuzdesi >= 50) Color(0xFF4CAF50) else MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(80.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Test Tamamlandı!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Başarı Oranınız: %$yuzdeText",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Doğru ve yanlış sayıları
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Doğru sayısı
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = dogruSayisi.toString(),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4CAF50)
                )
                Text(
                    text = "Doğru",
                    color = Color(0xFF4CAF50)
                )
            }

            // Yanlış sayısı
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = (toplamSoru - dogruSayisi).toString(),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFE57373)
                )
                Text(
                    text = "Yanlış",
                    color = Color(0xFFE57373)
                )
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Diğer bilgiler
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Test İstatistikleri",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Toplam Soru:")
                    Text(text = "$toplamSoru", fontWeight = FontWeight.Medium)
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Doğru Sayısı:")
                    Text(
                        text = "$dogruSayisi",
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF4CAF50)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Yanlış Sayısı:")
                    Text(
                        text = "${toplamSoru - dogruSayisi}",
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFFE57373)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onFinishClick,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(
                text = "Ana Sayfaya Dön",
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SoruEkraniPreview() {
    val ornek_sorular = listOf(
        Soru(
            id = 1,
            soruMetni = "Türkiye'nin başkenti neresidir?",
            secenekler = listOf("İstanbul", "Ankara", "İzmir", "Bursa", "Antalya"),
            dogruCevap = 1,
            aciklama = "Türkiye'nin başkenti Ankara'dır. 13 Ekim 1923'te başkent ilan edilmiştir."
        ),
        Soru(
            id = 2,
            soruMetni = "Aşağıdakilerden hangisi Türkiye'nin en yüksek dağıdır?",
            secenekler = listOf("Erciyes", "Ağrı", "Uludağ", "Palandöken", "Kaçkar"),
            dogruCevap = 1,
            aciklama = "Türkiye'nin en yüksek dağı 5137 metre ile Ağrı Dağı'dır."
        )
    )

    MaterialTheme {
        SoruEkrani(
            ders = "Türkiye Coğrafyası",
            konu = "Fiziki Coğrafya",
            sorular = ornek_sorular,
            onBackClick = {},
            onFinishClick = { _, _ -> }
        )
    }
}