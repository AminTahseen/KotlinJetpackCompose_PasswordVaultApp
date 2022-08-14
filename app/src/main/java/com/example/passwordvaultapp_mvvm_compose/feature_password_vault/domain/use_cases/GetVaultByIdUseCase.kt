package com.example.passwordvaultapp_mvvm_compose.feature_password_vault.domain.use_cases

import com.example.passwordvaultapp_mvvm_compose.feature_password_vault.domain.models.VaultPassword
import com.example.passwordvaultapp_mvvm_compose.feature_password_vault.domain.repository.VaultPasswordRepository
import kotlinx.coroutines.flow.Flow

class GetVaultByIdUseCase(
    private val passwordRepository: VaultPasswordRepository

) {
    operator fun invoke(id:Int): Flow<VaultPassword> {
        return passwordRepository.getVaultById(id)
    }
}