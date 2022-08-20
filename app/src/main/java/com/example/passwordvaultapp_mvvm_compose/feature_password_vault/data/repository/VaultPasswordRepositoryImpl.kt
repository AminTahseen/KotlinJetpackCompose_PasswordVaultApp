package com.example.passwordvaultapp_mvvm_compose.feature_password_vault.data.repository

import com.example.passwordvaultapp_mvvm_compose.feature_password_vault.data.data_source.VaultPasswordDao
import com.example.passwordvaultapp_mvvm_compose.feature_password_vault.domain.models.VaultPassword
import com.example.passwordvaultapp_mvvm_compose.feature_password_vault.domain.repository.VaultPasswordRepository
import kotlinx.coroutines.flow.Flow

class VaultPasswordRepositoryImpl(private val vaultPasswordDao: VaultPasswordDao):VaultPasswordRepository {
    override fun getAllVaultPassword(): Flow<List<VaultPassword>> {
        return vaultPasswordDao.getAllVaultPassword()
    }

    override suspend fun addNewVaultPassword(vaultPassword: VaultPassword) {
        vaultPasswordDao.addNewVaultPassword(vaultPassword)
    }

    override fun getVaultById(id: Int): Flow<VaultPassword> {
        return vaultPasswordDao.getVaultById(id = id)
    }

    override suspend fun deleteVaultPassword(vaultPassword: VaultPassword) {
        vaultPasswordDao.deleteVault(vaultPassword)
    }

}