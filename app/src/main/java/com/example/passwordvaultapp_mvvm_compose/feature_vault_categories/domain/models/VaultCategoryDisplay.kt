package com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.domain.models


data class VaultCategoryDisplay(
    val categoryName:String="",
    val categoryType:String="",
    val isVisible:Boolean=true,
    val id:Int?=null
)
