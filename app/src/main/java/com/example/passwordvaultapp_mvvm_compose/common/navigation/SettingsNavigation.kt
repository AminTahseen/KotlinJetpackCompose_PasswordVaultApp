package com.example.passwordvaultapp_mvvm_compose.common.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.passwordvaultapp_mvvm_compose.common.utils.Screen
import com.example.passwordvaultapp_mvvm_compose.feature_backup_restore.presentation.screens.BackupScreen
import com.example.passwordvaultapp_mvvm_compose.feature_backup_restore.presentation.screens.RestoreScreen

@Composable
fun SettingsNavigation(navController: NavHostController,startDestination: String){
    NavHost(navController = navController, startDestination = startDestination){
        composable(route = Screen.BackupScreen.route){
            BackupScreen()
        }
        composable(route = Screen.RestoreScreen.route){
            RestoreScreen()
        }
    }
}