package com.example.sinavavhazirliguygulama.data.model

// Test sonucu veri modeli
data class TestSonucu(
    val id: Int,
    val dersId: Int,
    val konuId: Int,
    val toplamSoru: Int,
    val dogruSayisi: Int,
    val yanlisSayisi: Int,
    val tamamlanmaTarihi: Long, // Timestamp olarak
    val toplamSure: Int // saniye cinsinden
)