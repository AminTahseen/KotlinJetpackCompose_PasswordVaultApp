package com.example.passwordvaultapp_mvvm_compose.feature_backup_restore.presentation.viewmodels

import android.content.Context
import android.os.Environment
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.passwordvaultapp_mvvm_compose.feature_password_vault.domain.models.VaultPassword
import com.example.passwordvaultapp_mvvm_compose.feature_password_vault.domain.use_cases.VaultUseCases
import com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.domain.models.VaultCategory
import com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.domain.use_cases.CategoryUseCases
import com.github.doyaaaaaken.kotlincsv.dsl.csvWriter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.File
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.schedule


@HiltViewModel
class BackupViewModel @Inject constructor(
     private val getCategoriesUseCase: CategoryUseCases,
     private val getVaultUseCase: VaultUseCases
) : ViewModel(){
     private val totalCategories by lazy { mutableStateOf(0) }
     val progress by lazy { mutableStateOf(0) }
     val message by lazy { mutableStateOf("0/0 Records Remaining") }
     val buttonState by lazy { mutableStateOf(true) }


     private val _categories: MutableLiveData<List<VaultCategory>> = MutableLiveData()
     private val _vaults: MutableLiveData<List<VaultPassword>> = MutableLiveData()

     init {
         viewModelScope.launch {
              getAllCategories()
         }
          viewModelScope.launch {
               getAllVaults()
          }
     }
     private suspend fun getAllCategories(){
          getCategoriesUseCase.getCategories().collect{
               Log.d("category",it.toString())
               _categories.value=it
          }
     }
     private suspend fun getAllVaults(){
          viewModelScope.launch {
               getVaultUseCase.getVaultsUseCase().collect{
                    Log.d("vault",it.toString())
                    _vaults.value=it
               }
          }

     }
     fun backupData(context: Context){
          buttonState.value=false
          totalCategories.value=_categories.value!!.size+_vaults.value!!.size
          backupCategoriesToCSV(context, _categories.value!!)

          for (i in _categories.value!!.indices){
               Log.d("category",_categories.value!![i].toString())
               if(i== _categories.value!!.size-1){
                    break
               }else{
                    progress.value+=i+1
                    message.value="${progress.value}/${totalCategories.value} Records Remaining"
               }

          }
          backupVaultsToCSV(context,_vaults.value!!)
          for (i in _vaults.value!!.indices){
               if(i== _categories.value!!.size-1){
                    progress.value+=100
                    totalCategories.value=100
                    Timer().schedule(5000) {
                         buttonState.value=true
                         message.value="Backup Successful"
                    }
                    break
               }else{
                    progress.value+=progress.value+1
                    message.value="${progress.value}/${totalCategories.value} Records Remaining"
               }
          }
     }
     private fun backupCategoriesToCSV(context:Context,list:List<VaultCategory>){
          var root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

          // select the name for your file
          root = File(root, "categories.csv")

          // create a new file
          val isNewFileCreated :Boolean = root.createNewFile()
          if(isNewFileCreated){
               println("${root.path} is created successfully.")
               val row1 = listOf("Category ID", "Category Name", "Category Type","Visible")
               csvWriter().open(root.path) {
                    writeRow(row1)
                    list.forEach {
                         writeRow(it.id,it.categoryName,it.categoryType,it.isVisible)
                    }
               }
          } else{
               println("${root.path}")
               println("${root.path} already exists.")
               val row1 = listOf("Category ID", "Category Name", "Category Type","Visible")
               csvWriter().open(root.path) {
                    writeRow(row1)
                    list.forEach {
                         writeRow(it.id,it.categoryName,it.categoryType,it.isVisible)
                    }
               }
          }
     }
     private fun backupVaultsToCSV(context:Context,list:List<VaultPassword>){
       //   val fileName = context.filesDir.path.toString()+"/vaults.csv"
         // var file = File(fileName)
          var root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

          //if you want to create a sub-dir
//          root = File(root, "SecureVault-Backup")
//          root.mkdir()

          // select the name for your file
          root = File(root, "vaults.csv")
          // create a new file
          val isNewFileCreated :Boolean =root.createNewFile()
          if(isNewFileCreated){
               println("${root.path} is created successfully.")
               val row1 = listOf("Vault ID","Category ID","Category Name", "Vault Name","Vault Password","Vault Logo")
               csvWriter().open(root.path) {
                    writeRow(row1)
                    list.forEach {
                         writeRow(it.id,it.vaultCategoryId,it.vaultCategory,it.vaultName,it.vaultPassword,it.vaultLogoURL)
                    }
               }
          } else{
               println("${root.path}")
               println("${root.path} already exists.")
               val row1 = listOf("Vault ID","Category ID","Category Name", "Vault Name","Vault Password","Vault Logo")
               csvWriter().open(root.path) {
                    writeRow(row1)
                    list.forEach {
                         writeRow(it.id,it.vaultCategoryId,it.vaultCategory,it.vaultName,it.vaultPassword,it.vaultLogoURL)
                    }
               }
          }
     }
}