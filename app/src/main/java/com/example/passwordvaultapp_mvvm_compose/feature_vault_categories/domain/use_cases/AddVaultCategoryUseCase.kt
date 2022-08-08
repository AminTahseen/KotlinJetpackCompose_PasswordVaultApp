package com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.domain.use_cases

import com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.domain.models.VaultCategory
import com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.domain.repository.VaultCategoryRepository

class AddVaultCategoryUseCase(
    private val repository: VaultCategoryRepository
) {
    suspend operator fun invoke(vaultCategory: VaultCategory){
        repository.addNewVaultCategory(vaultCategory)
    }
}