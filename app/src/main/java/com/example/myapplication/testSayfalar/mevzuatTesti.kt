package com.example.myapplication.testSayfalar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myapplication.bottomNavBarSayfalar.istatistik.istatistikViewModel

@Composable
fun mevzuatTesti(
    viewModel: SoruViewModel, // ViewModel'den veri alınır ve güncellenir
    onNavigateBack: () -> Unit // Geri dönmek için callback
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Soruları dinamik olarak oluşturuyoruz
        viewModel.sorular.forEach { soru ->
            QuestionComponent(
                questionText = soru.soruMetni,
                options = soru.secenekler,
                correctAnswer = soru.dogruCevap
            ) { isCorrect ->
                if (isCorrect) {
                    viewModel.updateCorrectAnswers() // Doğru cevap artırılır
                } else {
                    viewModel.updateIncorrectAnswers(soru) // Yanlış cevap artırılır ve soruyu kaydet
                }
                viewModel.updateTotalQuest() // Toplam soru sayısını artır
            }

            Spacer(modifier = Modifier.height(24.dp))
        }

        // Sonuçları göster
        Text(
            text = "Doğru: ${viewModel.dogruYanlisBilgisi.correct}, Yanlış: ${viewModel.dogruYanlisBilgisi.incorrect}, Toplam: ${
                viewModel.dogruYanlisBilgisi.correct + viewModel.dogruYanlisBilgisi.incorrect
            }",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black,
            modifier = Modifier.padding(top = 16.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Geri dönme butonu
        Button(onClick = onNavigateBack) {
            Text(text = "Geri Dön")
        }
    }
}


@Composable
fun QuestionComponent(
    questionText: String,
    options: List<String>,
    correctAnswer: String,
    onAnswerSelected: (Boolean) -> Unit
) {
    // Hangi butonun seçildiğini ve cevap verilip verilmediğini tutan state
    var selectedOptionIndex by remember { mutableStateOf<Int?>(null) }
    var isAnswered by remember { mutableStateOf(false) }

    Column {
        Text(
            text = questionText,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Şıklar için kutular (Box)
        options.forEachIndexed { index, option ->
            val isCorrect = option.startsWith(correctAnswer) // Doğru cevabı kontrol et
            val backgroundColor = when {
                selectedOptionIndex == null -> Color.Gray // Henüz bir buton seçilmediyse gri
                selectedOptionIndex == index && isCorrect -> Color.Green // Doğru cevap için yeşil
                selectedOptionIndex == index && !isCorrect -> Color.Red // Yanlış cevap için kırmızı
                else -> Color.Gray // Diğer butonlar gri kalır
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .background(backgroundColor, shape = RoundedCornerShape(8.dp))
                    .clickable(enabled = !isAnswered) {
                        if (!isAnswered) {
                            selectedOptionIndex = index // Seçilen butonun indeksini kaydet
                            isAnswered = true // Cevap hakkını devre dışı bırak
                            onAnswerSelected(isCorrect) // Cevap kontrolünü bildir
                        }
                    }
                    .padding(16.dp) // İçerik boşluğu
            ) {
                Text(
                    text = option,
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}





