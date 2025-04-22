package com.example.sinavavhazirliguygulama.data.model

import android.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

// Ders veri modeli
data class Ders(
    val id: Int,
    val ad: String,
    val icon: ImageVector,
    val color: androidx.compose.ui.graphics.Color,
    val konular: List<Konu>
)


// Ders istatistiği veri modeli (UI için)
data class DersIstatistik(
    val dersAdi: String,
    val icon: ImageVector,
    val renk: androidx.compose.ui.graphics.Color,
    val dogruSayisi: Int,
    val yanlisSayisi: Int,
    val toplamSure: Int, // dakika cinsinden
    val tamamlananTest: Int
)
