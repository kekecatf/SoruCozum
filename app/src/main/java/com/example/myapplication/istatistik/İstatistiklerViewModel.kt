package com.example.myapplication.istatistik

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {
    var quizResult by mutableStateOf(QuizResult())
        private set

    fun updateCorrectAnswers() {
        quizResult = quizResult.copy(correct = quizResult.correct + 1)
    }

    fun updateIncorrectAnswers() {
        quizResult = quizResult.copy(incorrect = quizResult.incorrect + 1)
    }
    fun updateTotalQuest(){
        quizResult = quizResult.copy(totalQuest = quizResult.totalQuest + 1)
    }
}