package com.example.passwordvaultapp_mvvm_compose.acitivites

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.navigation.compose.rememberNavController
import com.example.passwordvaultapp_mvvm_compose.common.utils.Screen
import com.example.passwordvaultapp_mvvm_compose.common.navigation.VaultCategoryNavigation
import com.example.passwordvaultapp_mvvm_compose.ui.theme.PasswordVaultApp_MVVM_ComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VaultCategoryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PasswordVaultApp_MVVM_ComposeTheme {
                val navController= rememberNavController()
                // A surface container using the 'background' color from the theme
                VaultCategoryNavigation(
                    navController = navController,
                    startRoute = Screen.VaultCategoriesScreen.route
                )

            }
        }
    }
}
