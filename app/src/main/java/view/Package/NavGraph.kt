package view.Package

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun setUpNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = "animateSplashScreen_Screen"
    ) {
        composable("welcome_Screen"
        ) {
            welcomeScreen()
        }

        composable("animateSplashScreen_Screen") {
            animateSplashScreen(navController)
        }
    }
}