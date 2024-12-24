package com.example.myapplication.sorular

val sampleQuestions = listOf(
    Question(
        id = 1,
        category = "Genel Kültür",
        questionText = "Türkiye'nin başkenti neresidir?",
        options = listOf("İstanbul", "Ankara", "İzmir"),
        correctAnswer = "Ankara"
    ),
    Question(
        id = 2,
        category = "Bilim",
        questionText = "Einstein hangi teoriyi geliştirdi?",
        options = listOf("Görelilik", "Kuantum", "Termodinamik"),
        correctAnswer = "Görelilik"
    ),
    Question(
        id = 3,
        category = "Tarih",
        questionText = "İstanbul'un fethi hangi yıl gerçekleşti?",
        options = listOf("1453", "1071", "1923"),
        correctAnswer = "1453"
    )
)