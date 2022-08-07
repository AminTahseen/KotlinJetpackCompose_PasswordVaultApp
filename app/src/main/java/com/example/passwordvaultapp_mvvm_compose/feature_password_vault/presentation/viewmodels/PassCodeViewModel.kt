package com.example.passwordvaultapp_mvvm_compose.feature_password_vault.presentation.viewmodels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import com.example.passwordvaultapp_mvvm_compose.common.utils.PrefManager
import com.example.passwordvaultapp_mvvm_compose.common.utils.Screen
import kotlinx.coroutines.launch

class PassCodeViewModel(application: Application) : AndroidViewModel(application) {
    var passCodeValue by mutableStateOf("")
    private var prefs = PrefManager(application.applicationContext)

    fun storePassCode(value:String){
        prefs.passCodeValue=value
    }

    fun getStoredPassCode():String{
        return prefs?.passCodeValue
    }
    fun verifyPassCode():Boolean{
        return when {
            getStoredPassCode() == passCodeValue -> {
                true
            }
            getStoredPassCode() == "00000" -> {
                storePassCode(passCodeValue)
                true
            }
            else -> {
                false
            }
        }
    }
}