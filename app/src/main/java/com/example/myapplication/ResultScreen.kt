package com.example.myapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ResultScreen(score: Int, totalQuestions: Int, onRestart: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Sonuçlarınız", style = MaterialTheme.typography.bodyMedium)
        Text("Doğru Sayısı: $score / $totalQuestions")
        Button(onClick = onRestart) {
            Text("Baştan Başla")
        }
    }
}
