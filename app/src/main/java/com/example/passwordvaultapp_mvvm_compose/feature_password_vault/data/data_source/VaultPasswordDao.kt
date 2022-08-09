package com.example.passwordvaultapp_mvvm_compose.feature_password_vault.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.passwordvaultapp_mvvm_compose.feature_password_vault.domain.models.VaultPassword
import kotlinx.coroutines.flow.Flow

@Dao
interface VaultPasswordDao {

    @Query("SELECT * FROM VaultPassword")
    fun getAllVaultPassword(): Flow<List<VaultPassword>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewVaultPassword(vaultPassword: VaultPassword)
}