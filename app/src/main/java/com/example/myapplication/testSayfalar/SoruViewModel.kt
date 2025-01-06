package com.example.myapplication.testSayfalar

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SoruViewModel : ViewModel() {
    // Soruları tanımlıyoruz
    private val _sorular = mutableStateListOf(
        Soru(
            "Kınama ve Ödeme kesintisi gerektiren fiil ve davranışlar kaç gün içerisinde disiplin cezası verilmediği takdirde zaman aşımına uğrar?",
            listOf("a) 3 ay", "b) 6 ay", "c) 9 ay", "d) 12 ay"),
            "b"
        ),
        Soru(
            "Disiplin cezalarının yazılı tebliğ süresi kaç gündür?",
            listOf("a) 5 gün", "b) 7 gün", "c) 10 gün", "d) 15 gün"),
            "c"
        )
        // Diğer sorular burada tanımlanabilir
    )

    val sorular: List<Soru> get() = _sorular

    // Yanlış yapılan soruları tutan liste
    private val _yanlisSorular = mutableStateListOf<Soru>()
    val yanlisSorular: List<Soru> get() = _yanlisSorular

    var dogruYanlisBilgisi by mutableStateOf(DogruYanlisBilgisi())
        private set

    fun updateCorrectAnswers() {
        dogruYanlisBilgisi = dogruYanlisBilgisi.copy(correct = dogruYanlisBilgisi.correct + 1)
    }

    fun updateIncorrectAnswers(soru: Soru) {
        if (!yanlisSorular.contains(soru)) {
            _yanlisSorular.add(soru) // Yanlış yapılan soruyu ekle
        } else {
            // Daha önce eklenmişse herhangi bir şey yapma
        }
        dogruYanlisBilgisi = dogruYanlisBilgisi.copy(incorrect = dogruYanlisBilgisi.incorrect + 1)
    }

    fun updateTotalQuest() {
        dogruYanlisBilgisi = dogruYanlisBilgisi.copy(totalQuest = dogruYanlisBilgisi.totalQuest + 1)
    }

    fun resetStats() {
        dogruYanlisBilgisi = DogruYanlisBilgisi(0, 0, 0)
        _yanlisSorular.clear() // Yanlış yapılan soruları sıfırla
    }
}





