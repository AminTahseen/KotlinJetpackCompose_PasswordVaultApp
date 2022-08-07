package com.example.passwordvaultapp_mvvm_compose.common.utils

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.passwordvaultapp_mvvm_compose.feature_password_vault.presentation.screens.MainScreen
import com.example.passwordvaultapp_mvvm_compose.feature_password_vault.presentation.screens.PassCodeScreen
import com.example.passwordvaultapp_mvvm_compose.feature_password_vault.presentation.screens.PasswordListScreen
import com.example.passwordvaultapp_mvvm_compose.feature_password_vault.presentation.viewmodels.PassCodeViewModel

@Composable
fun Navigation(passCodeViewModel: PassCodeViewModel){
    val navController= rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route){

        composable(route = Screen.MainScreen.route){
            MainScreen(navController = navController)
        }
        composable(route = Screen.PasscodeScreen.route){
            PassCodeScreen(navController = navController,passCodeViewModel)
        }
        composable(route = Screen.PasswordListScreen.route){
            PasswordListScreen(navController = navController)
        }

    }
}