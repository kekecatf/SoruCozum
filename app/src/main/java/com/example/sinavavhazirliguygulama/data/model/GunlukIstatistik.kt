package com.example.sinavavhazirliguygulama.data.model

// Günlük istatistik veri modeli
data class GunlukIstatistik(
    val tarih: Long, // Timestamp olarak
    val cozulenSoruSayisi: Int,
    val dogruSayisi: Int,
    val toplamSure: Int // dakika cinsinden
)