package com.example.passwordvaultapp_mvvm_compose.feature_password_vault.domain.models

data class PasswordData(
    val storeFor:String,
    val password:String,
    val logoURL:String,
)
