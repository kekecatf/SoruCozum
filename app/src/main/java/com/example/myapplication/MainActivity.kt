package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.AnaSayfaDenemeler.MainScreenWithStats
import com.example.myapplication.bottomNavBarSayfalar.YanlisYapilanSorular
import com.example.myapplication.bottomNavBarSayfalar.ayarlar
import com.example.myapplication.bottomNavBarSayfalar.Istatistik
import com.example.myapplication.bottomNavBarSayfalar.rastgeleSorular
import com.example.myapplication.testSayfalar.SoruViewModel
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
            SayfaGecisleri(viewModel = SoruViewModel())
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SayfaGecisleri(viewModel: SoruViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "anaSayfa") {
        composable("anaSayfa") {
            MainScreenWithStats(navController = navController,dogruYanlisBilgisi = viewModel.dogruYanlisBilgisi)
        }
        composable("yanlisCozulenSorular") {
            YanlisYapilanSorular(
                viewModel = viewModel, // ViewModel'den yanlisSorular'ı alır
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable("rastgeleSorular") {
            rastgeleSorular(navController=navController)
        }
        composable("istatistik") { backStackEntry ->
            // SoruViewModel'i ilgili Composable'da bağlamak için
            val viewModel: SoruViewModel = viewModel(backStackEntry)

            Istatistik(
                viewModel = viewModel,
                onNavigateBack = { navController.popBackStack() }, // Geri düğmesine basıldığında bir önceki ekrana dön
                onDeleteStats = {
                    viewModel.resetStats() // İstatistikleri sıfırla
                }
            )
        }
        composable("ayarlar"){
            ayarlar(navController=navController)
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
        composable("mevzuatTesti") {
            mevzuatTesti(
                viewModel = viewModel,  // SoruViewModel'ı burada kullanıyoruz
                onNavigateBack = { navController.popBackStack() } // Geri butonu için callback
            )
        }


    }
}

@SuppressLint("NewApi")
@Preview
@Composable
fun preview2(){
    SayfaGecisleri(viewModel = SoruViewModel())
}