package com.example.passwordvaultapp_mvvm_compose.common.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.passwordvaultapp_mvvm_compose.common.utils.Screen
import com.example.passwordvaultapp_mvvm_compose.feature_password_vault.presentation.screens.PasswordListScreen
import com.example.passwordvaultapp_mvvm_compose.feature_password_vault.presentation.screens.ProfileScreen
import com.example.passwordvaultapp_mvvm_compose.feature_password_vault.presentation.screens.SettingsScreen
import com.example.passwordvaultapp_mvvm_compose.feature_password_vault.presentation.screens.ToolsScreen

@Composable
fun BottomNavigation(navController:NavHostController,modifier: Modifier){
    NavHost(navController = navController, startDestination = Screen.PasswordListScreen.route){
        composable(route = Screen.PasswordListScreen.route){
            PasswordListScreen()
        }
        composable(route = Screen.ProfileScreen.route){
            ProfileScreen()
        }
        composable(route = Screen.SettingsScreen.route){
            SettingsScreen(navController = navController)
        }
        composable(route = Screen.ToolsScreen.route){
            ToolsScreen()
        }

    }
}