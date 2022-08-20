package com.example.passwordvaultapp_mvvm_compose.feature_password_vault.domain.repository


import com.example.passwordvaultapp_mvvm_compose.feature_password_vault.domain.models.VaultPassword
import kotlinx.coroutines.flow.Flow

interface VaultPasswordRepository {
    fun getAllVaultPassword(): Flow<List<VaultPassword>>

    suspend fun addNewVaultPassword(vaultPassword: VaultPassword)

    fun getVaultById(id:Int):Flow<VaultPassword>

    suspend fun deleteVaultPassword(vaultPassword: VaultPassword)
}