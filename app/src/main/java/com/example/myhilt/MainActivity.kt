package com.example.myhilt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myhilt.ui.detail.DetailScreen
import com.example.myhilt.ui.user.UserScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // 1. Создаем контроллер навигации
            val navController = rememberNavController()

            // 2. Описываем граф экранов
            NavHost(
                navController = navController,
                startDestination = "main_screen" // Начальный экран
            ) {
                // Первый экран
                composable("main_screen") {
                    UserScreen(
                        onNavigateToDetail = { id ->
                            // Переходим на экран деталей и передаем id в пути
                            navController.navigate("detail_screen/$id")
                        }
                    )
                }

                // Второй экран с аргументом
                composable(
                    route = "detail_screen/{userId}",
                    arguments = listOf(
                        navArgument("userId") { type = NavType.IntType } // Указываем тип аргумента
                    )
                ) {
                    DetailScreen(
                        onBackClick = {
                            navController.popBackStack() // Возврат назад
                        }
                    )
                }
            }
        }
    }
}