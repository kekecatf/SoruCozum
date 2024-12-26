package com.example.myapplication.istatistik

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import java.lang.reflect.Modifier

@Composable
fun istatistik(
    viewModel: QuizViewModel, // ViewModel'den veri alınır
    onNavigateBack: () -> Unit // Geri dönmek için callback
) {
    val quizResult = viewModel.quizResult

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "İstatistikler",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = "Doğru Cevaplar: ${quizResult.correct}",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "Yanlış Cevaplar: ${quizResult.incorrect}",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "Toplam Çözülen Sorular: ${quizResult.totalQuest}",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onNavigateBack) {
            Text(text = "Geri Dön")
        }
    }
}
