package com.example.passwordvaultapp_mvvm_compose.feature_password_vault.domain.models

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class VaultPassword(
    val vaultName:String,
    val vaultPassword:String,
    val vaultCategoryId:Int,
    val vaultCategory:String,
    val vaultLogoURL:Bitmap?,
    @PrimaryKey val id:Int?=null
)
fun VaultPassword.toPasswordData():PasswordData{
    return PasswordData(
        storeFor = vaultName,
        password = vaultPassword,
        vaultCategory=vaultCategory,
        vaultCategoryId = vaultCategoryId,
        logoURL =vaultLogoURL,
        id=id
    )
}