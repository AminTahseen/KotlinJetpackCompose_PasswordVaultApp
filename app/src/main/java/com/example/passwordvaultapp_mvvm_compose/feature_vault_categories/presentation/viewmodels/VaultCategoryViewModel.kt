package com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.presentation.viewmodels

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.domain.models.InvalidCategoryException
import com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.domain.models.VaultCategory
import com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.domain.use_cases.CategoryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VaultCategoryViewModel @Inject constructor(
    private val categoryUseCases: CategoryUseCases
    ) : ViewModel(){
        fun addVaultCategory(vaultCategory: VaultCategory){
           try {
               viewModelScope.launch {
                   categoryUseCases.addCategory(vaultCategory)
               }
           }catch (e:InvalidCategoryException){

           }
        }
    }