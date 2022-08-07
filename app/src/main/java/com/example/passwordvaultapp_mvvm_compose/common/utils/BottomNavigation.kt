package com.example.passwordvaultapp_mvvm_compose.common.utils

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.passwordvaultapp_mvvm_compose.feature_password_vault.presentation.screens.*

@Composable
fun BottomNavigation(navController:NavHostController){
    NavHost(navController = navController, startDestination = Screen.PasswordListScreen.route){
        composable(route = Screen.PasswordListScreen.route){
            PasswordListScreen(navController = navController)
        }
        composable(route = Screen.ProfileScreen.route){
            ProfileScreen(navController = navController)
        }
        composable(route = Screen.SettingsScreen.route){
            SettingsScreen(navController = navController)
        }
        composable(route = Screen.ToolsScreen.route){
            ToolsScreen(navController = navController)
        }
    }
}