package com.example.passwordvaultapp_mvvm_compose.di

import android.app.Application
import androidx.room.Room
import com.example.passwordvaultapp_mvvm_compose.common.Constants
import com.example.passwordvaultapp_mvvm_compose.common.data_source.SecureVaultDatabase
import com.example.passwordvaultapp_mvvm_compose.feature_password_vault.data.repository.VaultPasswordRepositoryImpl
import com.example.passwordvaultapp_mvvm_compose.feature_password_vault.domain.repository.VaultPasswordRepository
import com.example.passwordvaultapp_mvvm_compose.feature_password_vault.domain.use_cases.*
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
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideVaultCategoryRepository(db:SecureVaultDatabase):VaultCategoryRepository{
        return VaultCategoryRepositoryImpl(db.categoryDao)
    }

    @Provides
    @Singleton
    fun provideVaultPasswordRepository(db:SecureVaultDatabase):VaultPasswordRepository{
        return VaultPasswordRepositoryImpl(db.vaultPasswordDao)
    }

    @Provides
    @Singleton
    fun provideVaultCategoriesUseCases(repository: VaultCategoryRepository):CategoryUseCases{
        return CategoryUseCases(
            getCategories = GetVaultCategoriesUseCase(repository = repository),
            addCategory = AddVaultCategoryUseCase(repository=repository)
        )
    }

    @Provides
    @Singleton
    fun provideVaultUseCases(repository: VaultPasswordRepository):VaultUseCases{
        return VaultUseCases(
            addVaultUseCase = AddVaultUseCase(repository),
            getVaultsUseCase = GetVaultsUseCase(repository),
            getVaultByIdUseCase = GetVaultByIdUseCase(repository),
            deleteVaultUseCase = DeleteVaultUseCase(repository)
        )
    }
}