package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.data.GiftRepository
import com.example.data.LovedOneDatabase
import com.example.ui.LovedOneViewModel
import com.example.ui.LovedOneViewModelFactory
import com.example.ui.components.MeshBackground
import com.example.ui.screens.CreateGiftScreen
import com.example.ui.screens.DashboardScreen
import com.example.ui.screens.ViewGiftScreen
import com.example.ui.theme.LovedOneTheme

class MainActivity : ComponentActivity() {

    private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            LovedOneDatabase::class.java,
            "lovedone_db"
        ).build()
    }

    private val repository by lazy {
        GiftRepository(database.giftDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LovedOneTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val viewModel: LovedOneViewModel = viewModel(
                        factory = LovedOneViewModelFactory(repository)
                    )

                    NavHost(navController = navController, startDestination = "login") {
                        composable("login") {
                            MeshBackground {
                                com.example.ui.screens.LoginScreen(navController)
                            }
                        }
                        composable("dashboard") {
                            MeshBackground {
                                DashboardScreen(navController, viewModel)
                            }
                        }
                        composable("create_gift") {
                            MeshBackground {
                                CreateGiftScreen(navController, viewModel)
                            }
                        }
                        composable("view_gift/{giftId}") { backStackEntry ->
                            MeshBackground {
                                val giftId = backStackEntry.arguments?.getString("giftId")
                                ViewGiftScreen(navController, viewModel, giftId)
                            }
                        }
                    }
                }
            }
        }
    }
}
