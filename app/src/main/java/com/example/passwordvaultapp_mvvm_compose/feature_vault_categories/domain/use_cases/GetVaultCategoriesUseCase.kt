package com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.domain.use_cases

import com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.domain.models.VaultCategory
import com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.domain.repository.VaultCategoryRepository
import kotlinx.coroutines.flow.Flow

class GetVaultCategoriesUseCase(
    private val repository: VaultCategoryRepository
) {
    operator fun invoke():Flow<List<VaultCategory>>{
        return repository.getAllVaultCategories()
    }
}