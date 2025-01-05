package com.example.myapplication.bottomNavBarSayfalar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.AnaSayfaDenemeler.AppTopBarWithWelcome
import com.example.myapplication.AnaSayfaDenemeler.BottomNavigationBar
import com.example.myapplication.AnaSayfaDenemeler.QuickTestOptions
import com.example.myapplication.AnaSayfaDenemeler.UserStatsSection

@Composable
fun rastgeleSorular(navController: NavController){
    Scaffold(
        topBar = {
            Text("rastgele sorular")
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))
            }
        },
        bottomBar = { BottomNavigationBar(navController) }
    )
}