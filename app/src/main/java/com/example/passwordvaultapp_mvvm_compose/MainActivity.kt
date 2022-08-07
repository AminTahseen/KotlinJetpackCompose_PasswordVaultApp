package com.example.passwordvaultapp_mvvm_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.passwordvaultapp_mvvm_compose.common.utils.Navigation
import com.example.passwordvaultapp_mvvm_compose.feature_password_vault.presentation.viewmodels.PassCodeViewModel
import com.example.passwordvaultapp_mvvm_compose.ui.theme.PasswordVaultApp_MVVM_ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PasswordVaultApp_MVVM_ComposeTheme {
                var passCodeViewModel =
                    ViewModelProvider(this@MainActivity)[PassCodeViewModel::class.java]
                Navigation(passCodeViewModel = passCodeViewModel)
            }
        }
    }
}
