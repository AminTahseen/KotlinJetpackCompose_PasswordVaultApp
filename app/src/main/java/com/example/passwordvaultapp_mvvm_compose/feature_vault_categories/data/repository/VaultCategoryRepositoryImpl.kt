package com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.data.repository

import com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.data.data_source.VaultCategoryDao
import com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.domain.models.VaultCategory
import com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.domain.repository.VaultCategoryRepository
import kotlinx.coroutines.flow.Flow

class VaultCategoryRepositoryImpl(private val vaultCategoryDao: VaultCategoryDao):VaultCategoryRepository {
    override fun getAllVaultCategories(): Flow<List<VaultCategory>> {
        return vaultCategoryDao.getAllVaultCategories()
    }

    override suspend fun addNewVaultCategory(vaultCategory: VaultCategory) {
        vaultCategoryDao.addNewVaultCategory(vaultCategory)
    }
}