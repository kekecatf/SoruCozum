package com.example.sinavavhazirliguygulama.data.model

import android.content.Context
import com.example.sinavavhazirliguygulama.data.model.Soru
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class SoruRepository(private val context: Context) {

    fun getSorular(dersId: Int, konuId: Int): List<Soru> {
        val dosyaAdi = getDosyaAdi(dersId, konuId)
        val sorular = loadSorularFromJson(dosyaAdi) ?: emptyList()

        // Soru bulunamadıysa varsayılan soruları döndür
        return if (sorular.isEmpty()) getVarsayilanSorular() else sorular
    }

    private fun getDosyaAdi(dersId: Int, konuId: Int): String {
        // Ders ve konu ID'lerine göre dosya adı oluştur
        val dersAdi = when (dersId) {
            1 -> "sozel"
            2 -> "sayisal"
            3 -> "tarih"
            4 -> "cografya"
            5 -> "egitim"
            6 -> "mevzuat"
            else -> "genel"
        }

        val konuAdi = when (konuId) {
            1 -> when (dersId) {
                1 -> "paragraf"
                2 -> "temelmatematik"
                3 -> "ilkcag"
                4 -> "fizikiCografya"
                5 -> "egitimFelsefesi"
                6 -> "anayasa"
                else -> "konu1"
            }
            2 -> when (dersId) {
                1 -> "dilbilgisi"
                2 -> "sayilar"
                3 -> "ortacag"
                4 -> "iklim"
                5 -> "egitimSosyolojisi"
                6 -> "egitimHukuku"
                else -> "konu2"
            }
            // Diğer konular için de benzer şekilde ekleyebilirsiniz
            else -> "genel"
        }

        return "${dersAdi}_${konuAdi}.json"
    }

    private fun loadSorularFromJson(fileName: String): List<Soru>? {
        // JSON dosyasını oku
        val jsonString: String
        try {
            jsonString = context.assets.open("sorular/$fileName").bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }

        // JSON verisini Soru listesine dönüştür
        val listType = object : TypeToken<List<Soru>>() {}.type
        return Gson().fromJson(jsonString, listType)
    }

    // Rastgele sorular için
    fun getRastgeleSorular(adet: Int = 5): List<Soru> {
        val tumSorular = mutableListOf<Soru>()

        // Tüm JSON dosyalarını okuyarak bir havuz oluştur
        // Gerçek bir uygulamada bu yöntem yerine veritabanı kullanmak daha etkili olur
        for (dersId in 1..6) {
            for (konuId in 1..5) {
                val sorular = getSorular(dersId, konuId)
                tumSorular.addAll(sorular)
            }
        }

        // Rastgele seçim yap
        return tumSorular.shuffled().take(adet.coerceAtMost(tumSorular.size))
    }

    // Hatalı soruları getirmek için
    // Bu fonksiyon gerçek bir uygulamada kullanıcı tercihlerine göre doldurulabilir
    fun getYanlisSorular(): List<Soru> {
        // Örnek olarak belirli ID'lere sahip soruları getirme
        return listOf()
    }
}
fun getVarsayilanSorular(): List<Soru> {
    return listOf(
        Soru(
            id = 1,
            soruMetni = "Bu konu için henüz soru eklenmemiştir. Aşağıdaki örnek soruyu cevaplayabilirsiniz.",
            secenekler = listOf("Seçenek A", "Seçenek B", "Seçenek C", "Seçenek D", "Seçenek E"),
            dogruCevap = 0,
            aciklama = "Bu bir örnek sorudur."
        )
    )
}