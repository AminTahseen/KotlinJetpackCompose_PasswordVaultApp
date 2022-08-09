package com.example.passwordvaultapp_mvvm_compose.feature_password_vault.domain.models

data class PasswordData(
    val storeFor:String,
    val password:String,
    val vaultCategory:String,
    val vaultCategoryId: Int,
    val logoURL:String,
    val id:Int?
)
