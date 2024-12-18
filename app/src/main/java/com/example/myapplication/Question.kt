package com.example.myapplication

data class Question(
    val id: Int,
    val category: String,
    val questionText: String,
    val options: List<String>,
    val correctAnswer: String
)
