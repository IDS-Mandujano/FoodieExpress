package com.mandujano.foodieexpress.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mandujano.foodieexpress.features.menu.presentation.screens.DetailScreen
import com.mandujano.foodieexpress.features.menu.presentation.screens.MenuScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "menu"
    ) {
        composable("menu") {
            MenuScreen(
                onDishClick = { dishId ->
                    navController.navigate("detail/$dishId")
                }
            )
        }

        composable(
            route = "detail/{dishId}",
            arguments = listOf(
                navArgument("dishId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val dishId = backStackEntry.arguments?.getString("dishId") ?: ""

            DetailScreen(
                dishId = dishId,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}