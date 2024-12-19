package com.example.myapplication

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class Category(
    val title: String,
    val subcategories: List<String>,
    var isExpanded: Boolean = false // Genişleme durumu (varsayılan false)
) {
    // Compose için isExpanded'ı mutable hale getiriyoruz
    var isExpandedState by mutableStateOf(isExpanded)
}
