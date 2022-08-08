package com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.domain.use_cases

data class CategoryUseCases(
    val getCategories:GetVaultCategoriesUseCase,
    val addCategory:AddVaultCategoryUseCase
)
