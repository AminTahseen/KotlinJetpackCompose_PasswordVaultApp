package com.example.passwordvaultapp_mvvm_compose.feature_backup_restore.presentation.viewmodels

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.passwordvaultapp_mvvm_compose.common.utils.FilePicker
import com.example.passwordvaultapp_mvvm_compose.feature_password_vault.domain.models.VaultPassword
import com.example.passwordvaultapp_mvvm_compose.feature_password_vault.domain.use_cases.VaultUseCases
import com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.domain.models.VaultCategory
import com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.domain.use_cases.CategoryUseCases
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RestoreViewModel @Inject constructor(
    private val vaultUseCases: VaultUseCases,
    private val categoryUseCases: CategoryUseCases
) : ViewModel() {
     val progress by lazy { mutableStateOf(0) }
     val message by lazy { mutableStateOf("0/0 Records Remaining") }
     fun selectFileFromStorage(
         launcher: ManagedActivityResultLauncher<String, MutableList<Uri>>,
         permissionLauncher: ManagedActivityResultLauncher<String, Boolean>,
         context: Context,
         fileType:String
                               )
     {
          FilePicker.filePicker(
               launcher = launcher,
               permissionLauncher = permissionLauncher,
               context = context,
               fileType = fileType
          )
     }

    fun restoreData(fileName:String) {
        progress.value+=100
        csvReader().open(fileName = fileName) {
            readAllAsSequence().forEach { row: List<String> ->
                //Do something
                message.value="Restoring Categories Data, Please Wait"
                if(
                    row[0]!="Category ID" && row[1]!="Category Name"
                    && row[2]!="Category Type" && row[3]!="Visible"
                ){
                    // insert to db
                    Log.d("row","${row[0]} ${row[1]} ${row[2]} ${row[3]}")
                    insertCategoryDataToDB(row[0].toInt(),row[1],row[2],row[3].toBoolean())
                }
            }
        }
    }
    fun restoreVaultData(fileName:String) {
        progress.value+=100
        csvReader().open(fileName = fileName) {
            readAllAsSequence().forEach { row: List<String> ->
                message.value="Restoring Vaults Data, Please Wait"
                //Do something
                if(
                    row[0]!="Vault ID" && row[2]!="Category Name"
                    && row[1]!="Category ID" && row[3]!="Vault Name"
                    && row[4]!="Vault Password" && row[5]!="Vault Logo"
                ){
                    // insert to db

                    Log.d("row Vault","${row[0]} ${row[2]} ${row[1]} ${row[3]} ${row[4]} ${decodeBase64(row[5])}")
                    insertVaultDataToDB(row[0].toInt(),row[2],row[1].toInt(),row[3],row[4],decodeBase64(row[5]))
                }
            }
        }
    }

    private fun insertCategoryDataToDB(id:Int, name:String, type:String, visible:Boolean){
        val category= VaultCategory(
            id=id,
            categoryName = name,
            categoryType = type,
            isVisible = visible
        )
        viewModelScope.launch {
            categoryUseCases.addCategory(category)
            message.value = "Category, $name Restored Successfully"
        }
    }
    private fun insertVaultDataToDB(id:Int, categoryName:String, categoryId:Int,name:String,vaultPassword:String, logoURL: Bitmap?){
        val vault= VaultPassword(
            id=id,
            vaultName = name,
            vaultPassword =vaultPassword,
            vaultCategory = categoryName,
            vaultCategoryId = categoryId,
            vaultLogoURL = logoURL
        )
        viewModelScope.launch {
            vaultUseCases.addVaultUseCase(vault)
            message.value = "Vault, $name Restored Successfully"
        }
    }
    fun decodeBase64(input: String?): Bitmap? {
        val decodedByte = Base64.decode(input, 0)
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.size)
    }





}