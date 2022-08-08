package com.example.passwordvaultapp_mvvm_compose.common.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.data.data_source.VaultCategoryDao
import com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.domain.models.VaultCategory

@Database(
    entities = [VaultCategory::class],
    version = 1
)
abstract class SecureVaultDatabase:RoomDatabase() {
    abstract val vaultCategoryDao:VaultCategoryDao
}