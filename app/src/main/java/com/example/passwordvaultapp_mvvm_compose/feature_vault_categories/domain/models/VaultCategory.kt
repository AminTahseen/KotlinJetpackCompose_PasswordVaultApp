package com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.lang.Exception

@Entity
data class VaultCategory(
    val categoryName:String="",
    val categoryType:String="",
    val isVisible:Boolean=true,
    @PrimaryKey val id:Int?=null
)

fun VaultCategory.toVaultCategoryDisplay():VaultCategoryDisplay{
    return VaultCategoryDisplay(
      categoryName = categoryName,
      categoryType = categoryType,
      isVisible = isVisible,
      id=id
    )
}