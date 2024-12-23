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
import com.example.myapplication.bottomNavBarSayfalar.ayarlar
import com.example.myapplication.bottomNavBarSayfalar.istatistik
import com.example.myapplication.testSayfalar.matematikTesti
import com.example.myapplication.testSayfalar.mevzuatTesti
import com.example.myapplication.testSayfalar.tarihTesti
import com.example.myapplication.testSayfalar.turkceTesti

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
    NavHost(navController = navController, startDestination = "anaSayfa") {
        composable("anaSayfa") {
            MainScreenWithStats(navController = navController)
        }
        composable("istatistik"){
            istatistik()
        }
        composable("ayarlar"){
            ayarlar()
        }

        composable("turkceTesti"){
            turkceTesti()
        }
        composable("matematikTesti"){
            matematikTesti()
        }
        composable("tarihTesti"){
            tarihTesti()
        }
        composable("mevzuatTesti"){
            mevzuatTesti()
        }


    }
}

@SuppressLint("NewApi")
@Preview
@Composable
fun preview2(){
    SayfaGecisleri()
}