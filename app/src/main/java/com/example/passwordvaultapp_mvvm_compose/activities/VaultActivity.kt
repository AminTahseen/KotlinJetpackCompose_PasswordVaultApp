package com.example.passwordvaultapp_mvvm_compose.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.passwordvaultapp_mvvm_compose.activities.ui.theme.PasswordVaultApp_MVVM_ComposeTheme
import com.example.passwordvaultapp_mvvm_compose.common.navigation.VaultNavigation
import com.example.passwordvaultapp_mvvm_compose.common.utils.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VaultActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PasswordVaultApp_MVVM_ComposeTheme {
                // A surface container using the 'background' color from the theme
                val navController= rememberNavController()
                VaultNavigation(navController = navController, startRoute = Screen.AddVaultScreen.route)
            }
        }
    }
}

