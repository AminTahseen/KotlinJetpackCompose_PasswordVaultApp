package com.example.passwordvaultapp_mvvm_compose.acitivites

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.passwordvaultapp_mvvm_compose.acitivites.ui.theme.PasswordVaultApp_MVVM_ComposeTheme
import com.example.passwordvaultapp_mvvm_compose.common.navigation.SettingsNavigation
import com.example.passwordvaultapp_mvvm_compose.common.utils.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PasswordVaultApp_MVVM_ComposeTheme {
                val screen=intent.extras?.get("screen")
                val navController= rememberNavController()
                if(screen.toString()==Screen.BackupScreen.route)
                    SettingsNavigation(
                        navController = navController,
                        startDestination = Screen.BackupScreen.route
                    )
                else
                    SettingsNavigation(
                        navController = navController,
                        startDestination = Screen.RestoreScreen.route
                    )

            }
        }
    }
}
