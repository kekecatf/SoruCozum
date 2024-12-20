package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.AnaSayfaDenemeler.MainScreenWithStats

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //com.example.myapplication.AnaSayfaDenemeler.MainScreen()
            SayfaGecisleri()
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SayfaGecisleri() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "AnaSayfa") {
        composable("AnaSayfa") {
            MainScreenWithStats(navController = navController)
        }
        composable("Ayarlar"){
            ayarlar()
        }
    }
}

@SuppressLint("NewApi")
@Preview
@Composable
fun preview2(){
    SayfaGecisleri()
}