package com.example.myapplication.AnaSayfaDenemeler

import android.annotation.SuppressLint
import android.graphics.Color.rgb
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.istatistik.QuizResult
import com.example.myapplication.istatistik.QuizViewModel
import com.example.myapplication.SayfaGecisleri
import com.mikepenz.iconics.compose.IconicsPainter
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic



@Composable
fun MainScreenWithStats(navController: NavController,quizResult: QuizResult) {
    Scaffold(
        topBar = {
            AppTopBarWithWelcome("Atıf")
                 },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                UserStatsSection(navController,quizResult)
                Spacer(modifier = Modifier.height(16.dp))
                QuickTestOptions(navController)
            }
        },
        bottomBar = { BottomNavigationBar4(navController) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBarWithWelcome(userName: String) {
    TopAppBar(
        title = {
            Text(
                text = "MEB-AGS \nSoruları Çöz",
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        },
        actions = {
            Text(
                text = "Hoş geldin, $userName!",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color(0xFFFFB74D)
            )
        }
    )
}

@Composable
fun UserStatsSection(navController: NavController,quizResult: QuizResult) {
    Card(
        onClick = {
            navController.navigate("istatistik")
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.elevatedCardElevation(6.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF3E5F5))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Bugünkü İstatistikler",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF7E57C2)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                StatBox("Çözülen Sorular", "${quizResult.totalQuest}")
                StatBox("Doğru Cevaplar", "${quizResult.correct}")
                StatBox("Yanlış Cevaplar", "${quizResult.incorrect}")
            }
        }
    }
}

@Composable
fun StatBox(label: String, value: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        Text(text = value, fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color(0xFF512DA8))
        Text(text = label, fontSize = 14.sp, color = Color.Gray)
    }
}

@Composable
fun QuickTestOptions(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Hızlı Test Seçenekleri",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        TestButton(navController,"Türkçe Testi", Icons.Default.Create, Color(0xFF81C784))
        TestButton(navController,"Matematik Testi", Icons.Default.Create, Color(0xFF64B5F6))
        TestButton(navController,"Tarih Testi", Icons.Default.Create, Color(0xFFFFB74D))
        TestButton(navController,"Mevzuat Testi",Icons.Default.Create,Color(rgb(77, 161, 169)))
    }
}

@Composable
fun TestButton(navController: NavController, title: String, icon: ImageVector, color: Color) {
    Button(
        onClick = {
            if (title== "Türkçe Testi") {
                navController.navigate("turkceTesti")
            }
            else if(title=="Matematik Testi"){
                navController.navigate("matematikTesti")
            }
            else if(title=="Tarih Testi"){
                navController.navigate("tarihTesti")
            }
            else if(title=="Mevzuat Testi"){
                navController.navigate("mevzuatTesti")
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        colors = ButtonDefaults.buttonColors(containerColor = color),
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = title, tint = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = title, color = Color.White, fontSize = 16.sp)
        }
    }
}

@Composable
fun BottomNavigationBar4(navController: NavController) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Ana Sayfa") },
            label = { Text("Ana Sayfa") },
            selected = true,
            onClick = { navController.navigate("anaSayfa") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Close, contentDescription = "Yanlış Yapılan Sorular") },
            label = { Text(
                text = "Yanlış Yapılan Sorular",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis // Metin sığmazsa '...' koyar
            ) },
            selected = false,
            onClick = { navController.navigate("Yanlış Yapılan Sorular") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.List, contentDescription = "Rastgele Sorular") },
            label = { Text(
                text = "Yanlış Yapılan Sorular",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis // Metin sığmazsa '...' koyar
            ) },
            selected = false,
            onClick = { navController.navigate("Rastgele Sorular") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Info, contentDescription = "İstatistik") },
            label = { Text("İstatistik") },
            selected = false,
            onClick = { navController.navigate("istatistik") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Settings, contentDescription = "Ayarlar") },
            label = { Text("Ayarlar") },
            selected = false,
            onClick = { navController.navigate("ayarlar") }
        )
    }
}

@SuppressLint("NewApi")
@Preview
@Composable
fun preview2(){
    SayfaGecisleri(viewModel = QuizViewModel())
}