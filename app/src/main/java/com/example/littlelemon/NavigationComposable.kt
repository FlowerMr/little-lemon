package com.example.littlelemon

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun MyNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Onboarding.route // مقصد شروع
    ) {
        composable(Onboarding.route) { OnboardingScreen(navController) }
        composable(Home.route) { HomeScreen() }
        composable(Profile.route) { ProfileScreen() }
    }
}