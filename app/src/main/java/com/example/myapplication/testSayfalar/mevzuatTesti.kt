package com.example.myapplication.testSayfalar

import android.graphics.Color.rgb
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.format.TextStyle

@Composable
fun mevzuatTesti() {
    // Doğru, yanlış ve toplam çözülen soru sayıları
    var correctCount by remember { mutableStateOf(0) }
    var wrongCount by remember { mutableStateOf(0) }
    var totalCount by remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Soru 1
        QuestionComponent(
            questionText = "Kınama ve Ödeme kesintisi gerektiren fiil ve davranışlar kaç gün içerisinde disiplin cezası verilmediği takdirde zaman aşımına uğrar?",
            options = listOf("a) 3 ay", "b) 6 ay", "c) 9 ay", "d) 12 ay"),
            correctAnswer = "b"
        ) { isCorrect ->
            totalCount++ // Toplam çözülen soruların sayısını artır
            if (isCorrect) {
                correctCount++ // Doğru cevap sayısını artır
            } else {
                wrongCount++ // Yanlış cevap sayısını artır
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Soru 2
        QuestionComponent(
            questionText = "Disiplin cezalarının yazılı tebliğ süresi kaç gündür?",
            options = listOf("a) 5 gün", "b) 7 gün", "c) 10 gün", "d) 15 gün"),
            correctAnswer = "c"
        ) { isCorrect ->
            totalCount++ // Toplam çözülen soruların sayısını artır
            if (isCorrect) {
                correctCount++ // Doğru cevap sayısını artır
            } else {
                wrongCount++ // Yanlış cevap sayısını artır
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Sonuçları göster
        Text(
            text = "Doğru: $correctCount, Yanlış: $wrongCount, Toplam: $totalCount",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

@Composable
fun QuestionComponent(
    questionText: String,
    options: List<String>,
    correctAnswer: String,
    onAnswerSelected: (Boolean) -> Unit
) {
    var selectedAnswer by remember { mutableStateOf<String?>(null) }
    var isCorrect by remember { mutableStateOf<Boolean?>(null) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Soru kısmı
        Text(
            text = questionText,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Şıklar
        options.forEach { option ->
            val optionKey = option.substring(0, 1) // Şıkların anahtar harfi
            val backgroundColor = when {
                selectedAnswer == null -> Color(rgb(46, 80, 119)) // Henüz seçim yapılmadıysa
                optionKey == selectedAnswer && isCorrect == true -> Color.Green // Doğru cevap
                optionKey == selectedAnswer && isCorrect == false -> Color.Red // Yanlış cevap
                else -> Color(rgb(46, 80, 119)) // Seçilmeyen şıklar
            }

            Text(
                text = option,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White, // Şık yazı rengi
                modifier = Modifier
                    .fillMaxWidth()
                    .background(backgroundColor) // Arkaplan rengini dinamik ayarla
                    .clickable(enabled = selectedAnswer == null) { // Tek seçim hakkı
                        selectedAnswer = optionKey
                        isCorrect = selectedAnswer == correctAnswer
                        onAnswerSelected(isCorrect == true) // Sonucu üst seviyeye ilet
                    }
                    .padding(vertical = 8.dp)
                    .padding(horizontal = 8.dp) // İçerik dolgusu
            )
        }
    }
}



@Preview
@Composable
fun mevzuatTestiPreview(){
    mevzuatTesti()
}