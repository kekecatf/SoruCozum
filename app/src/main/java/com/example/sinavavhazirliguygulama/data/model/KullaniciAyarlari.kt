package com.example.sinavavhazirliguygulama.data.model

// Kullanıcı ayarları veri modeli
data class KullaniciAyarlari(
    val bildirimlerAktif: Boolean = true,
    val sesAktif: Boolean = true,
    val karanlikTema: Boolean = false,
    val cevrimdisiModAktif: Boolean = false,
    val yaziTipiBoyu: YaziTipiBoyu = YaziTipiBoyu.ORTA
)

// Yazı tipi boyutu için enum
enum class YaziTipiBoyu(val boyut: Float) {
    KUCUK(0.8f),
    ORTA(1.0f),
    BUYUK(1.2f),
    COK_BUYUK(1.4f)
}

// İstatistik tipi için enum
enum class IstatistikTipi {
    GÜNLÜK, HAFTALIK, AYLIK
}