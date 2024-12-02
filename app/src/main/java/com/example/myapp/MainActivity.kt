package com.example.myapp

import Acteurs
import Profil
import Films
import Series
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.MovieCreation
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material3.*
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.myapp.ui.theme.MyAppTheme
import kotlinx.serialization.Serializable

@Serializable class ProfilScreen
@Serializable class FilmsScreen
@Serializable class SeriesScreen

@Serializable class ActeursScreen


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
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

                    val viewModel: MainViewModel = viewModel()

                    var isSearchOpen by remember { mutableStateOf(false) }
                    var motCle by remember { mutableStateOf("") }

                    LaunchedEffect(currentDestination) {
                        isSearchOpen = false
                        motCle = ""
                    }

                    Scaffold(
                        topBar = {
                            when (windowSizeClass.windowWidthSizeClass) {
                                WindowWidthSizeClass.COMPACT -> {
                                    if (currentDestination?.hasRoute<ProfilScreen>() != true) {
                                        TopAppBar(
                                            title = {
                                                if (isSearchOpen) {
                                                    TextField(
                                                        value = motCle,
                                                        onValueChange = {
                                                            motCle = it
                                                            if (currentDestination?.hasRoute<FilmsScreen>() == true) {
                                                                viewModel.searchMovies(it)
                                                            } else if (currentDestination?.hasRoute<SeriesScreen>() == true) {
                                                                viewModel.searchSeries(it)
                                                            } else if (currentDestination?.hasRoute<ActeursScreen>() == true) {
                                                                viewModel.searchActors(it)
                                                            }
                                                        },
                                                        placeholder = { Text("Rechercher...") },
                                                        singleLine = true,
                                                        modifier = Modifier.fillMaxWidth()
                                                    )
                                                } else {
                                                    Text("MyApp", color = Color.White)
                                                }
                                            },
                                            actions = {
                                                IconButton(onClick = {
                                                    isSearchOpen = !isSearchOpen
                                                    if (!isSearchOpen) {
                                                        motCle = ""
                                                    }
                                                }) {
                                                    if (isSearchOpen) {
                                                        Icon(
                                                            imageVector = Icons.Filled.Close,
                                                            contentDescription = "Fermer la recherche",
                                                            tint = Color.White
                                                        )
                                                    } else {
                                                        Icon(
                                                            imageVector = Icons.Filled.Search,
                                                            contentDescription = "Rechercher",
                                                            tint = Color.White
                                                        )
                                                    }
                                                }
                                            },
                                            colors = TopAppBarDefaults.topAppBarColors(
                                                containerColor = Color(0xFF2B59C3)
                                            )
                                        )

                                    }
                                }

                                    else -> {

                                    }
                            }
                        },
                        bottomBar = {
                            when (windowSizeClass.windowWidthSizeClass) {
                                WindowWidthSizeClass.COMPACT -> {
                                    if (currentDestination?.hasRoute<ProfilScreen>() != true) {
                                        NavigationBar(
                                            containerColor = Color(0xFF2B59C3),
                                            contentColor = Color.White,
                                        ) {
                                            NavigationBarItem(
                                                icon = {
                                                    Icon(
                                                        imageVector = Icons.Filled.MovieCreation,
                                                        contentDescription = "Films",
                                                        tint = if (currentDestination?.hasRoute<FilmsScreen>() == true) Color.Black else Color.White
                                                    )
                                                },
                                                label = {
                                                    Text("Films", color = Color.White)
                                                },
                                                selected = currentDestination?.hasRoute<FilmsScreen>() == true,
                                                onClick = { navController.navigate(FilmsScreen()) },
                                            )
                                            NavigationBarItem(
                                                icon = {
                                                    Icon(
                                                        imageVector = Icons.Filled.Tv,
                                                        contentDescription = "Séries",
                                                        tint = if (currentDestination?.hasRoute<SeriesScreen>() == true) Color.Black else Color.White
                                                    )
                                                },
                                                label = {
                                                    Text("Séries", color = Color.White)
                                                },
                                                selected = currentDestination?.hasRoute<SeriesScreen>() == true,
                                                onClick = { navController.navigate(SeriesScreen()) },
                                            )
                                            NavigationBarItem(
                                                icon = {
                                                    Icon(
                                                        imageVector = Icons.Filled.Person,
                                                        contentDescription = "Acteurs",
                                                        tint = if (currentDestination?.hasRoute<ActeursScreen>() == true) Color.Black else Color.White
                                                    )
                                                },
                                                label = {
                                                    Text("Acteurs", color = Color.White)
                                                },
                                                selected = currentDestination?.hasRoute<ActeursScreen>() == true,
                                                onClick = { navController.navigate(ActeursScreen()) },
                                            )
                                        }
                                    }
                                }
                                else -> {
                                    
                                }
                            }
                        }


                    ) { innerPadding ->
                        NavHost(
                            navController = navController,
                            startDestination = ProfilScreen(),
                            Modifier.padding(innerPadding)
                        ) {
                            composable<ProfilScreen> { Profil(firstName = "Thomas", lastName = "Cerqueira", windowClass = windowSizeClass, navController = navController)}
                            composable<FilmsScreen> { Films(navController = navController, motCle = motCle) }
                            composable<SeriesScreen> { Series(navController = navController, motCle = motCle) }
                            composable<ActeursScreen> { Acteurs(navController = navController, motCle = motCle) }
                        }
                    }
                }
            }
        }
    }
}
