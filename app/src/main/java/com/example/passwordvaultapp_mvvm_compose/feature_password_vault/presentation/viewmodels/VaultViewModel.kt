package com.example.passwordvaultapp_mvvm_compose.feature_password_vault.presentation.viewmodels

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhinav.passwordgenerator.PasswordGenerator
import com.example.passwordvaultapp_mvvm_compose.feature_password_vault.domain.models.VaultPassword
import com.example.passwordvaultapp_mvvm_compose.feature_password_vault.domain.use_cases.VaultUseCases
import com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.domain.models.VaultCategory
import com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.domain.use_cases.CategoryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject


@HiltViewModel
class VaultViewModel @Inject constructor(
    private val vaultUseCases: VaultUseCases,
    private val categoryUseCases: CategoryUseCases
) : ViewModel(){
    val message by lazy { MutableLiveData<String>() }
    val isMessageVisible =  mutableStateOf(false)

    private val _vaults: MutableLiveData<List<VaultPassword>> = MutableLiveData()
    val vaults: LiveData<List<VaultPassword>> get() = _vaults
    private val hasLoaded by lazy { MutableLiveData<Boolean>() }

    private val _selectedVault:MutableLiveData<VaultPassword> = MutableLiveData()
    val selectedVault:MutableLiveData<VaultPassword> get() = _selectedVault

    val isSearchFilterApply =  mutableStateOf(false)
    private val _categories: MutableLiveData<List<VaultCategory>> = MutableLiveData()


    init {
        viewModelScope.launch {
            getAllVaults()
            getAllVaultCategories()
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
        Log.d("bitmapInside",vaultPassword.vaultLogoURL.toString())
        isMessageVisible.value=true
        when {
            vaultPassword.vaultName.isNullOrBlank() -> {
                message.value="Vault name cannot be empty !"
            }
            vaultPassword.vaultPassword.isNullOrBlank() -> {
                message.value="Vault password cannot be empty !"
            }
            vaultPassword.vaultPassword.isNullOrBlank() -> {
                message.value="Vault password cannot be empty !"
            }
            vaultPassword.vaultPassword.length<6 -> {
                message.value="Vault password cannot be less than 6 !"
            }
            vaultPassword.vaultLogoURL==null-> {
                message.value="Vault logo image cannot be empty !"
            }
            vaultPassword.vaultCategory.isNullOrBlank() && vaultPassword.vaultCategoryId==-1 -> {
                message.value="Select a vault category !"
            }
            else -> {
                viewModelScope.launch {
                    vaultUseCases.addVaultUseCase(vaultPassword)
                }
                if(vaultPassword.id!=null)
                    message.value="Vault Updated"
                else
                    message.value="New Vault Created"
            }
        }
    }

    fun deleteVault(vaultPassword: VaultPassword){
        CoroutineScope(Dispatchers.IO).launch {
            vaultUseCases.deleteVaultUseCase(vaultPassword)
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
                _selectedVault.value=it
            }
        }
    }
    fun getVaultImageFromLocal(imageUri:Uri):Uri{
        if (imageUri.toString().substring(0, 21) == "content://com.android")
        {
            Log.d("FILEEXIST","IF")
            Log.d("FILEEXIST",imageUri.toString())
            val photoSplit: List<String> = imageUri.toString().split("%3A")
            val imageUriBasePath = "content://media/external/images/media/" + photoSplit[1]
            val file = File(imageUriBasePath)
            return if (file.exists()) {
                //Do something
                Log.d("FILEEXIST","IF > IF")
                Log.d("FILEEXIST",imageUri.toString())
                Uri.parse(imageUriBasePath)
            }else{
                Log.d("FILEEXIST","IF > ELSE")
                Log.d("FILEEXIST",imageUri.toString())
                imageUri
            }
        }else{
            Log.d("FILEEXIST","ELSE")
            Log.d("FILEEXIST",imageUri.toString())
        }
        return imageUri
    }
    fun getActualPath(context: Context,contentURI:Uri):Uri{
        var thePath = "no-path-found"
        val filePathColumn = arrayOf(MediaStore.Images.Media.DISPLAY_NAME)
        val cursor: Cursor? =
            context.contentResolver.query(contentURI, filePathColumn, null, null, null)
        if (cursor!!.moveToFirst()) {
            val columnIndex: Int = cursor.getColumnIndex(filePathColumn[0])
            thePath = cursor.getString(columnIndex)
        }
        cursor.close()
        return Uri.parse(thePath)
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
    fun getSelectedCategoryIndex(categoryId:Int):Int{
        var index=-1
        for (i in 0 until _categories.value!!.size)
            if(_categories.value!![i].id==categoryId)
                index=i

        return index
    }
    fun uriToBitmap(imageUri: Uri,context: Context): Bitmap?{
        var bitmap: Bitmap? = null
        val contentResolver: ContentResolver = context.contentResolver
        try {
            bitmap = if (Build.VERSION.SDK_INT < 28) {
                Log.d("bitmapInside", "try if")
                MediaStore.Images.Media.getBitmap(contentResolver, imageUri)
                return bitmap
            } else {
                Log.d("bitmapInside", "try else")
                val source = ImageDecoder.createSource(contentResolver, imageUri)
                return ImageDecoder.decodeBitmap(source)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("bitmapInside", "catch")
            return bitmap
        }
    }
}