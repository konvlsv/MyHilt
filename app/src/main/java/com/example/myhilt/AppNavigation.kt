package com.example.myhilt

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.myhilt.ui.detail.DetailScreen
import com.example.myhilt.ui.user.UserScreen

// Выносим маршруты в константы, чтобы избежать опечаток в будущем
object Screen {
    const val Main = "main_screen"
    const val Detail = "detail_screen/{userId}"

    // Функция для удобного формирования пути с аргументом
    fun createDetailRoute(userId: Int) = "detail_screen/$userId"
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Main,
        modifier = modifier
    ) {
        // Первый экран
        composable(route = Screen.Main) {
            UserScreen(
                onNavigateToDetail = { id ->
                    navController.navigate(Screen.createDetailRoute(id))
                }
            )
        }

        // Второй экран с аргументом
        composable(
            route = Screen.Detail,
            arguments = listOf(
                navArgument("userId") { type = NavType.IntType }
            )
        ) {
            DetailScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}