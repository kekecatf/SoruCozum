package com.example.myapplication

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizApp()
        }
    }
}
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun QuizApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScreen(
                navController = navController,
                onStartQuiz = { navController.navigate("quiz") })
        }
        composable("quiz") {
            QuizScreen(
                questions = sampleQuestions,
                onQuizFinish = { score ->
                navController.navigate("result/$score")
            })
        }
        composable("result/{score}") { backStackEntry ->
            val score = backStackEntry.arguments?.getString("score")?.toInt() ?: 0
            ResultScreen(
                score = score,
                totalQuestions = sampleQuestions.size) {
                navController.popBackStack("main", inclusive = false)
            }
        }
    }
}