package com.example.passwordvaultapp_mvvm_compose.feature_backup_restore.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class RestoreViewModel : ViewModel() {
     val progress by lazy { mutableStateOf(0) }

     fun restoreData(){
          progress.value+=100
     }
}