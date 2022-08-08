package com.example.passwordvaultapp_mvvm_compose.feature_password_vault.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.abhinav.passwordgenerator.PasswordGenerator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VaultViewModel @Inject constructor(
) : ViewModel(){

    fun generatePassword(
        length: Int = 6,
        includeNumbers: Boolean = false,
        includeUpperCase: Boolean = false,
        includeLowerCase: Boolean = false,
        includeSymbols: Boolean = false,
    ): String {
        return try {
            var passwordGenerator = PasswordGenerator(
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
}