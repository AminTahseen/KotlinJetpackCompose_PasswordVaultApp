package com.example.passwordvaultapp_mvvm_compose.feature_password_vault.presentation.viewmodels

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhinav.passwordgenerator.PasswordGenerator
import com.example.passwordvaultapp_mvvm_compose.feature_password_vault.domain.models.VaultPassword
import com.example.passwordvaultapp_mvvm_compose.feature_password_vault.domain.use_cases.VaultUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VaultViewModel @Inject constructor(
    private val vaultUseCases: VaultUseCases
) : ViewModel(){
    val message by lazy { MutableLiveData<String>() }
    val isMessageVisible =  mutableStateOf(false)

    private val _vaults: MutableLiveData<List<VaultPassword>> = MutableLiveData()
    val vaults: LiveData<List<VaultPassword>> get() = _vaults
    private val hasLoaded by lazy { MutableLiveData<Boolean>() }

    private val _selectedVault:MutableLiveData<VaultPassword> = MutableLiveData()
    val selectedVault:MutableLiveData<VaultPassword> get() = _selectedVault

    val isSearchFilterApply =  mutableStateOf(false)


    init {
        viewModelScope.launch {
            getAllVaults()
        }
    }
    fun generatePassword(
        length: Int = 6,
        includeNumbers: Boolean = false,
        includeUpperCase: Boolean = false,
        includeLowerCase: Boolean = false,
        includeSymbols: Boolean = false,
    ): String {
        return try {
            val passwordGenerator = PasswordGenerator(
                length = length,
                includeUpperCaseLetters = includeUpperCase,
                includeLowerCaseLetters = includeLowerCase,
                includeSymbols = includeSymbols,
                includeNumbers = includeNumbers,
            )
            passwordGenerator.generatePassword()
        } catch (e: Exception) {
            "Cannot Generate"
        }
    }

    fun addNewVault(vaultPassword: VaultPassword){
        when {
            vaultPassword.vaultName.isNullOrBlank() -> {
                isMessageVisible.value=true
                message.value="Vault name cannot be empty !"
            }
            vaultPassword.vaultPassword.isNullOrBlank() -> {
                isMessageVisible.value=true
                message.value="Vault password cannot be empty !"
            }
            vaultPassword.vaultPassword.isNullOrBlank() -> {
                isMessageVisible.value=true
                message.value="Vault password cannot be empty !"
            }
            vaultPassword.vaultPassword.length<6 -> {
                isMessageVisible.value=true
                message.value="Vault password cannot be less than 6 !"
            }
            vaultPassword.vaultLogoURL.isNullOrBlank() -> {
                isMessageVisible.value=true
                message.value="Vault logo image cannot be empty !"
            }
            vaultPassword.vaultCategory.isNullOrBlank() && vaultPassword.vaultCategoryId==-1 -> {
                isMessageVisible.value=true
                message.value="Select a vault category !"
            }
            else -> {
                viewModelScope.launch {
                    vaultUseCases.addVaultUseCase(vaultPassword)
                }
                isMessageVisible.value=true
                message.value="New Vault Created"
            }
        }
    }
    private suspend fun getAllVaults(){
        hasLoaded.value=false
        viewModelScope.launch {
            vaultUseCases.getVaultsUseCase().collect{
                _vaults.value=it
            }
            hasLoaded.value=true
        }
    }
    fun getVaultById(id:Int){
        viewModelScope.launch {
            vaultUseCases.getVaultByIdUseCase(id).collect{
                Log.d("vaultDetails",it.toString())
                _selectedVault.value=it
            }
        }
    }
    fun getVaultImageFromLocal(imageUri:Uri):Uri{
        if (imageUri.toString().substring(0, 21) == "content://com.android") {
            val photoSplit: List<String> = imageUri.toString().split("%3A")
            val imageUriBasePath = "content://media/external/images/media/" + photoSplit[1]
            return Uri.parse(imageUriBasePath)
        }
        return imageUri
    }
}