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
import com.example.myapplication.bottomNavBarSayfalar.istatistik.QuizViewModel
import com.example.myapplication.bottomNavBarSayfalar.istatistik.istatistik
import com.example.myapplication.bottomNavBarSayfalar.rastgeleSorular
import com.example.myapplication.bottomNavBarSayfalar.yanlisYapilanSorular
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
            SayfaGecisleri(viewModel = QuizViewModel())
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SayfaGecisleri(viewModel: QuizViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "anaSayfa") {
        composable("anaSayfa") {
            MainScreenWithStats(navController = navController,quizResult = viewModel.quizResult)
        }
        composable("yanlisYapilanSorular") {
            yanlisYapilanSorular(navController=navController)
        }
        composable("rastgeleSorular") {
            rastgeleSorular(navController=navController)
        }
        composable("istatistik") {
            istatistik(
                viewModel = viewModel,
                onNavigateBack = { navController.popBackStack() },
                onDeleteStats = { viewModel.resetStats() }
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
        composable("mevzuatTesti"){
            mevzuatTesti(
                viewModel = viewModel, // ViewModel doğrudan Page2Screen'e gönderilir
                onNavigateBack = { navController.popBackStack() })// Geri butonu için callback
        }

    }
}

@SuppressLint("NewApi")
@Preview
@Composable
fun preview2(){
    SayfaGecisleri(viewModel = QuizViewModel())
}