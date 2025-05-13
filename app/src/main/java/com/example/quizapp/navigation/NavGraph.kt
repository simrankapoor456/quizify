package com.example.quizapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.quizapp.ui.screens.*
import com.example.quizapp.navigation.Routes


@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.AUTH_CHOICE) {

        composable(Routes.AUTH_CHOICE) {
            AuthChoiceScreen(
                onLoginClick = { navController.navigate(Routes.LOGIN) },
                onSignupClick = { navController.navigate(Routes.SIGNUP) }
            )
        }

        composable(Routes.SIGNUP) {
            SignupScreen(navController)
        }

        composable(Routes.LOGIN) {
            LoginScreen(navController)
        }

        composable(Routes.HOME) {
            HomeScreen(navController = navController)
        }

        composable(Routes.QUIZ) {
            QuizScreen(navController = navController)
        }

        composable(Routes.LEADERBOARD) {
            LeaderboardScreen(navController = navController)
        }

        composable(Routes.CHANGE_PASSWORD) {
            ChangePasswordScreen(navController = navController)
        }
    }
}
