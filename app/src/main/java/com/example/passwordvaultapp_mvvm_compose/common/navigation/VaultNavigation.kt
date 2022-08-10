package com.example.passwordvaultapp_mvvm_compose.common.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.passwordvaultapp_mvvm_compose.common.utils.Screen
import com.example.passwordvaultapp_mvvm_compose.feature_password_vault.presentation.screens.AddPasswordVaultScreen
import com.example.passwordvaultapp_mvvm_compose.feature_password_vault.presentation.screens.GenerateVaultPassScreen

@Composable
fun VaultNavigation(navController: NavHostController,startRoute:String){
    NavHost(navController = navController, startDestination = startRoute){
        composable(route = Screen.AddVaultScreen.route){
            AddPasswordVaultScreen(navController = navController)
        }
        composable(route = Screen.GenerateVaultPassScreen.route){
           GenerateVaultPassScreen(navController = navController)
        }

    }
}