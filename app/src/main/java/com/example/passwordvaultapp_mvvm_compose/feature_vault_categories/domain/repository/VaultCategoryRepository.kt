package com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.domain.repository


import com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.domain.models.VaultCategory
import kotlinx.coroutines.flow.Flow

interface VaultCategoryRepository {
    fun getAllVaultCategories(): Flow<List<VaultCategory>>

    suspend fun addNewVaultCategory(vaultCategory: VaultCategory)
}