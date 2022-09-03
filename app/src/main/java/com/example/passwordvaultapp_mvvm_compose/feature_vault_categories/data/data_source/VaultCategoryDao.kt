package com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.domain.models.VaultCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface VaultCategoryDao {

    @Query("SELECT * FROM VaultCategory")
    fun getAllVaultCategories():Flow<List<VaultCategory>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewVaultCategory(vaultCategory: VaultCategory)

    @Query("DELETE FROM VaultCategory")
    fun clearTable()
}