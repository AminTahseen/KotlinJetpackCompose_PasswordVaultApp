package com.example.passwordvaultapp_mvvm_compose.feature_password_vault.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.passwordvaultapp_mvvm_compose.feature_password_vault.domain.use_cases.VaultUseCases
import com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.domain.use_cases.CategoryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val vaultUseCases: VaultUseCases,
    private val categoryUseCases: CategoryUseCases
) : ViewModel(){

     fun clearAllData(){
         CoroutineScope(Dispatchers.IO).launch {
            vaultUseCases.clearVaultTableUseCase.invoke()
            categoryUseCases.clearVaultCategoryTableUseCase.invoke()
        }
    }

}