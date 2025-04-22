package com.example.sinavavhazirliguygulama.data.model

// Kullanıcı veri modeli
data class Kullanici(
    val id: Int,
    val kullaniciAdi: String,
    val email: String,
    val profilResmi: String? = null
)