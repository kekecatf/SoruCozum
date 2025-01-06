package com.example.myapplication.bottomNavBarSayfalar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myapplication.testSayfalar.SoruViewModel

//androidx.compose.ui.Modifier
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Istatistik(
    viewModel: SoruViewModel,
    onNavigateBack: () -> Unit,
    onDeleteStats: () -> Unit
) {
    // Güncel istatistik bilgilerini DogruYanlisBilgisi'nden alıyoruz
    val dogruYanlisBilgisi = viewModel.dogruYanlisBilgisi

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "İstatistikler") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Geri"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onDeleteStats) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Sil"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = androidx.compose.ui.Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Bilgiler DogruYanlisBilgisi sınıfından alınarak gösteriliyor
            StatisticCard(title = "Toplam çözülen soru", value = dogruYanlisBilgisi.totalQuest)
            StatisticCard(title = "Doğru çözülen soru", value = dogruYanlisBilgisi.correct)
            StatisticCard(title = "Yanlış çözülen soru", value = dogruYanlisBilgisi.incorrect)
        }
    }
}

@Composable
fun StatisticCard(title: String, value: Int) {
    Card(
        modifier = androidx.compose.ui.Modifier
            .fillMaxWidth()
            .height(80.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = androidx.compose.ui.Modifier.padding(16.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = value.toString(),
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
