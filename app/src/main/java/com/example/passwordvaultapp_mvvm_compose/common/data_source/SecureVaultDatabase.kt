package com.example.passwordvaultapp_mvvm_compose.common.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.passwordvaultapp_mvvm_compose.feature_password_vault.data.data_source.VaultPasswordDao
import com.example.passwordvaultapp_mvvm_compose.feature_password_vault.domain.models.VaultPassword
import com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.data.data_source.VaultCategoryDao
import com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.domain.models.VaultCategory

@Database(
    entities = [
         VaultCategory::class,
         VaultPassword::class,
               ],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class SecureVaultDatabase:RoomDatabase() {
    abstract val categoryDao:VaultCategoryDao
    abstract val vaultPasswordDao:VaultPasswordDao
}