package com.example.passwordvaultapp_mvvm_compose.common.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.passwordvaultapp_mvvm_compose.common.utils.Screen
import com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.presentation.screens.AddNewVaultCategoryScreen
import com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.presentation.screens.VaultCategoriesScreen

@Composable
fun VaultCategoryNavigation(navController: NavHostController,startRoute:String){
    NavHost(navController = navController, startDestination = startRoute){
        composable(route = Screen.VaultCategoriesScreen.route){
            VaultCategoriesScreen(navController = navController)
        }
        composable(route = Screen.AddNewVaultCategoryScreen.route){
            AddNewVaultCategoryScreen()
        }
    }
}