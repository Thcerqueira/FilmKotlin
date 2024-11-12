package com.example.myapp

import Profil
import Film
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.*
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapp.ui.theme.MyAppTheme
import kotlinx.serialization.Serializable

@Serializable class ProfilScreen
@Serializable class FilmScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination
                    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass

                    Scaffold(
                        bottomBar = {
                            if(currentDestination?.hasRoute<ProfilScreen>() != true){
                                NavigationBar {
                                    NavigationBarItem(
                                        icon = { Icon(imageVector = Icons.Filled.Create, contentDescription = "Film") },
                                        label = { Text("Film") },
                                        selected = currentDestination?.hasRoute<FilmScreen>() == true,
                                        onClick = { navController.navigate(FilmScreen()) }
                                    )
                                }
                            }

                        }
                    ) { innerPadding ->
                        NavHost(
                            navController = navController,
                            startDestination = ProfilScreen(),
                            Modifier.padding(innerPadding)
                        ) {
                            composable<ProfilScreen> { Profil(firstName = "Thomas", lastName = "Cerqueira", windowClass = windowSizeClass, navController = navController) }
                            composable<FilmScreen> { Film(navController = navController) }
                        }
                    }
                }
            }
        }
    }
}
