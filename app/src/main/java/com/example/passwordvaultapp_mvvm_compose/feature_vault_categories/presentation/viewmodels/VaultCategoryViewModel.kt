package com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.domain.models.VaultCategory
import com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.domain.use_cases.CategoryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VaultCategoryViewModel @Inject constructor(
    private val categoryUseCases: CategoryUseCases
    ) : ViewModel(){

    val message by lazy { MutableLiveData<String>() }

    private val hasLoaded by lazy { MutableLiveData<Boolean>() }

    val isMessageVisible =  mutableStateOf(false)

    private val _categories: MutableLiveData<List<VaultCategory>> = MutableLiveData()
    val categories: LiveData<List<VaultCategory>> get() = _categories

    init {
        viewModelScope.launch {
            getAllVaultCategories()
        }
    }
    fun addVaultCategory(vaultCategory: VaultCategory){
            if(vaultCategory.categoryName.isBlank()){
                isMessageVisible.value=true
                message.value="Category name cannot be blank !"
            }else {
                viewModelScope.launch {
                    categoryUseCases.addCategory(vaultCategory)
                }
                isMessageVisible.value=true
                message.value="New Vault Category Created"
            }
        }
    private suspend fun getAllVaultCategories(){
        hasLoaded.value=false
        viewModelScope.launch {
            categoryUseCases.getCategories().collect{
                Log.d("category",it.toString())
                _categories.value=it
            }
            hasLoaded.value=true
        }
    }
 }