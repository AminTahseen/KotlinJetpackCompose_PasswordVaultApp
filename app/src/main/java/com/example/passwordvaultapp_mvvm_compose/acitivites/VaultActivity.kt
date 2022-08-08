package com.example.passwordvaultapp_mvvm_compose.acitivites

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.passwordvaultapp_mvvm_compose.acitivites.ui.theme.PasswordVaultApp_MVVM_ComposeTheme
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

