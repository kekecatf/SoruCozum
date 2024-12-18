package com.example.myapplication

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import java.lang.reflect.Modifier

@Composable
fun QuizScreen(
    questions: List<Question>,
    onQuizFinish: (score: Int) -> Unit
) {
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var score by remember { mutableStateOf(0) }
    val question = questions[currentQuestionIndex]

    Column(modifier = androidx.compose.ui.Modifier.fillMaxSize()) {
        Text(text = question.questionText, style = MaterialTheme.typography.bodyMedium)
        question.options.forEach { option ->
            Button(onClick = {
                if (option == question.correctAnswer) score++
                if (currentQuestionIndex < questions.size - 1) {
                    currentQuestionIndex++
                } else {
                    onQuizFinish(score)
                }
            }) {
                Text(option)
            }
        }
    }
}
