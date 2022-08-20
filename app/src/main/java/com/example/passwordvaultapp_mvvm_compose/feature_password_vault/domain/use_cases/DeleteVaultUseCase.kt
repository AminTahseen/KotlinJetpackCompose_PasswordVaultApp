package com.example.passwordvaultapp_mvvm_compose.feature_password_vault.domain.use_cases

import com.example.passwordvaultapp_mvvm_compose.feature_password_vault.domain.models.VaultPassword
import com.example.passwordvaultapp_mvvm_compose.feature_password_vault.domain.repository.VaultPasswordRepository

class DeleteVaultUseCase(
    private val passwordRepository: VaultPasswordRepository
) {
    suspend operator fun invoke(vaultPassword: VaultPassword){
        passwordRepository.deleteVaultPassword(vaultPassword)
    }
}