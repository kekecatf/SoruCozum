package com.example.sinavavhazirliguygulama.data.model

data class Soru(
    val id: Int,
    val soruMetni: String,
    val secenekler: List<String>,
    val dogruCevap: Int, // Doğru cevabın indeksi (0'dan başlayarak)
    val aciklama: String
)