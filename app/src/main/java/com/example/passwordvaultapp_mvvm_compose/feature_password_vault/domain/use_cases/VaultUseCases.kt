package com.example.passwordvaultapp_mvvm_compose.feature_password_vault.domain.use_cases

data class VaultUseCases(
    val addVaultUseCase: AddVaultUseCase,
    val getVaultsUseCase: GetVaultsUseCase,
    val getVaultByIdUseCase: GetVaultByIdUseCase
)
