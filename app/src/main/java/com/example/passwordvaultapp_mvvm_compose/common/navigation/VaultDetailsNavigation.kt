package com.example.passwordvaultapp_mvvm_compose.common.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.passwordvaultapp_mvvm_compose.common.utils.Screen
import com.example.passwordvaultapp_mvvm_compose.feature_password_vault.presentation.screens.AddPasswordVaultScreen
import com.example.passwordvaultapp_mvvm_compose.feature_password_vault.presentation.screens.ViewVaultDetailsScreen

@Composable
fun VaultDetailsNavigation(navController: NavHostController,startRoute:String,id:Int=-1){
    NavHost(navController = navController, startDestination = startRoute){
        composable(
            route = Screen.VaultDetailsScreen.route
        ){
            ViewVaultDetailsScreen(id,navController)
        }
        composable(
            route = Screen.EditVaultScreen.route,
            arguments = listOf(
                navArgument("vaultId") {
                    type = NavType.IntType
                },
            )
        ){ backStackEntry ->
            val vaultId = backStackEntry.arguments?.getInt("vaultId") ?: -1
            AddPasswordVaultScreen(navController = navController,vaultId)
        }
    }
}