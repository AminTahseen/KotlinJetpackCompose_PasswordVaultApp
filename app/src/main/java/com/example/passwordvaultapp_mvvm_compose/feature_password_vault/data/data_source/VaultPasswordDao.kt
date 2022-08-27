package com.example.passwordvaultapp_mvvm_compose.feature_password_vault.data.data_source

import androidx.room.*
import com.example.passwordvaultapp_mvvm_compose.feature_password_vault.domain.models.VaultPassword
import kotlinx.coroutines.flow.Flow

@Dao
interface VaultPasswordDao {

    @Query("SELECT * FROM VaultPassword")
    fun getAllVaultPassword(): Flow<List<VaultPassword>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewVaultPassword(vaultPassword: VaultPassword)

    @Query("SELECT * FROM VaultPassword WHERE id=:id")
    fun getVaultById(id:Int):Flow<VaultPassword>

    @Delete
    fun deleteVault(vaultPassword:VaultPassword)

    @Query("DELETE FROM VaultPassword")
    fun clearTable()
}