package com.example.passwordvaultapp_mvvm_compose.feature_password_vault.domain.models

import android.graphics.Bitmap

data class PasswordData(
    val storeFor:String,
    val password:String,
    val vaultCategory:String,
    val vaultCategoryId: Int,
    val logoURL:Bitmap?,
    val id:Int?
)
