package com.example.myapplication

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavController) {
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route

    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Ana Sayfa") },
            label = { Text("Ana Sayfa") },
            selected = currentDestination == "anaSayfa",
            onClick = {
                if (currentDestination != "anaSayfa") {
                    navController.navigate("anaSayfa") {
                        popUpTo("anaSayfa") { inclusive = true } // Aynı sayfaya tekrar gidilmesini önler
                    }
                }
            }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Close, contentDescription = "Yanlış Yapılan Sorular") },
            label = {
                Text(
                    text = "Yanlış Yapılan Sorular",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            selected = currentDestination == "yanlisYapilanSorular",
            onClick = {
                if (currentDestination != "yanlisYapilanSorular") {
                    navController.navigate("yanlisYapilanSorular") {
                        popUpTo("yanlisYapilanSorular") { inclusive = true }
                    }
                }
            }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.List, contentDescription = "Rastgele Sorular") },
            label = {
                Text(
                    text = "Rastgele Sorular",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            selected = currentDestination == "rastgeleSorular",
            onClick = {
                if (currentDestination != "rastgeleSorular") {
                    navController.navigate("rastgeleSorular") {
                        popUpTo("rastgeleSorular") { inclusive = true }
                    }
                }
            }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Info, contentDescription = "İstatistik") },
            label = { Text("İstatistik") },
            selected = currentDestination == "istatistik",
            onClick = {
                if (currentDestination != "istatistik") {
                    navController.navigate("istatistik") {
                        popUpTo("istatistik") { inclusive = true }
                    }
                }
            }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Settings, contentDescription = "Ayarlar") },
            label = { Text("Ayarlar") },
            selected = currentDestination == "ayarlar",
            onClick = {
                if (currentDestination != "ayarlar") {
                    navController.navigate("ayarlar") {
                        popUpTo("ayarlar") { inclusive = true }
                    }
                }
            }
        )
    }
}