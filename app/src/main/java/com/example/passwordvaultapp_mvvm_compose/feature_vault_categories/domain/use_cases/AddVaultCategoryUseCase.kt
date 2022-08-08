package com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.domain.use_cases

import com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.domain.models.InvalidCategoryException
import com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.domain.models.VaultCategory
import com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.domain.repository.VaultCategoryRepository

class AddVaultCategoryUseCase(
    private val repository: VaultCategoryRepository
) {
    suspend operator fun invoke(vaultCategory: VaultCategory){
        if(vaultCategory.categoryName.isBlank()){
            throw InvalidCategoryException("Category name cannot be blank !")
        }else{
            return repository.addNewVaultCategory(vaultCategory)
        }
    }
}