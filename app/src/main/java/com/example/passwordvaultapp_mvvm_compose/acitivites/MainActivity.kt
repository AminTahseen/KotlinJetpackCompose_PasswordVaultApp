package com.example.passwordvaultapp_mvvm_compose.acitivites

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.passwordvaultapp_mvvm_compose.common.navigation.AuthNavigation
import com.example.passwordvaultapp_mvvm_compose.feature_authentication.presentation.viewmodels.PassCodeViewModel
import com.example.passwordvaultapp_mvvm_compose.ui.theme.PasswordVaultApp_MVVM_ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // development-issues-resolution branch created
        setContent {
            PasswordVaultApp_MVVM_ComposeTheme {
                var passCodeViewModel =
                    ViewModelProvider(this@MainActivity)[PassCodeViewModel::class.java]
               AuthNavigation(passCodeViewModel = passCodeViewModel)
            }
        }
    }
}
