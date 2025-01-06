package com.example.myapplication.bottomNavBarSayfalar.istatistik

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.myapplication.testSayfalar.DogruYanlisBilgisi

class istatistikViewModel : ViewModel() {
    var dogruYanlisBilgisi by mutableStateOf(DogruYanlisBilgisi())
        private set

    fun updateCorrectAnswers() {
        dogruYanlisBilgisi = dogruYanlisBilgisi.copy(correct = dogruYanlisBilgisi.correct + 1)
    }

    fun updateIncorrectAnswers() {
        dogruYanlisBilgisi = dogruYanlisBilgisi.copy(incorrect = dogruYanlisBilgisi.incorrect + 1)
    }
    fun updateTotalQuest(){
        dogruYanlisBilgisi = dogruYanlisBilgisi.copy(totalQuest = dogruYanlisBilgisi.totalQuest + 1)
    }
    fun resetStats() {
        dogruYanlisBilgisi = DogruYanlisBilgisi(0, 0, 0)
    }
}