package com.example.passwordvaultapp_mvvm_compose.di

import android.app.Application
import androidx.room.Room
import com.example.passwordvaultapp_mvvm_compose.common.Constants
import com.example.passwordvaultapp_mvvm_compose.common.data_source.SecureVaultDatabase
import com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.data.data_source.VaultCategoryDao
import com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.data.repository.VaultCategoryRepositoryImpl
import com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.domain.repository.VaultCategoryRepository
import com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.domain.use_cases.AddVaultCategoryUseCase
import com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.domain.use_cases.CategoryUseCases
import com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.domain.use_cases.GetVaultCategoriesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSecureVaultDatabase(app:Application):SecureVaultDatabase{
        return Room.databaseBuilder(
            app,
            SecureVaultDatabase::class.java,
            Constants.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideVaultCategoryRepository(db:SecureVaultDatabase):VaultCategoryRepository{
        return VaultCategoryRepositoryImpl(db.vaultCategoryDao)
    }

    @Provides
    @Singleton
    fun provideVaultCategoriesUseCases(repository: VaultCategoryRepository):CategoryUseCases{
        return CategoryUseCases(
            getCategories = GetVaultCategoriesUseCase(repository = repository),
            addCategory = AddVaultCategoryUseCase(repository=repository)
        )
    }
}