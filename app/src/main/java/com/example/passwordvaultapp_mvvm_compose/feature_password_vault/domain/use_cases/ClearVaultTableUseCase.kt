package com.example.passwordvaultapp_mvvm_compose.feature_password_vault.domain.use_cases

import com.example.passwordvaultapp_mvvm_compose.feature_password_vault.domain.repository.VaultPasswordRepository

class ClearVaultTableUseCase(
    private val passwordRepository: VaultPasswordRepository
) {
    suspend operator fun invoke(){
        passwordRepository.clearTable()
    }
}