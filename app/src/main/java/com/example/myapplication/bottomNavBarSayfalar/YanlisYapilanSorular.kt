package com.example.myapplication.bottomNavBarSayfalar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.testSayfalar.QuestionComponent
import com.example.myapplication.testSayfalar.SoruViewModel
import java.lang.reflect.Modifier

@Composable
fun YanlisYapilanSorular(viewModel: SoruViewModel, // Yanlış yapılan soruları ViewModel'den alıyoruz
                         onNavigateBack: () -> Unit // Geri dönmek için callback
) {
    // Eğer yanlisSorular listesi boşsa bir mesaj gösterelim
    if (viewModel.yanlisSorular.isEmpty()) {
        Text(
            text = "Henüz yanlış yaptığınız soru yok.",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black
        )
    } else {
        // Eğer yanlış sorular varsa, her birini listeleyelim
        LazyColumn {
            items(viewModel.yanlisSorular, key = { it.soruMetni }) { soru ->
                QuestionComponent(
                    questionText = soru.soruMetni,
                    options = soru.secenekler,
                    correctAnswer = soru.dogruCevap,
                    onAnswerSelected = {} // Bu sayfada cevap kontrolü yapılmaz
                )
                Spacer(modifier = androidx.compose.ui.Modifier.height(24.dp))
            }
        }
    }

    // Geri dönme butonu
    Button(onClick = onNavigateBack) {
        Text(text = "Geri Dön")
    }
}