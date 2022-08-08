package com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.lang.Exception

@Entity
data class VaultCategory(
    val categoryName:String,
    val categoryType:String,
    val isVisible:Boolean,
    @PrimaryKey val id:Int?=null
)
class InvalidCategoryException(message:String): Exception(message)
