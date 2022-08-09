package com.example.passwordvaultapp_mvvm_compose.feature_password_vault.presentation.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.passwordvaultapp_mvvm_compose.feature_password_vault.domain.models.VaultPassword
import com.example.passwordvaultapp_mvvm_compose.feature_password_vault.domain.models.toPasswordData
import com.example.passwordvaultapp_mvvm_compose.ui.theme.textColor

@Composable
fun PasswordListCompose(startCharacter:String,passwordList: List<VaultPassword>){
    Log.d("listV",passwordList.toString())
    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(16.dp)
    ) {
        when{
            passwordList.isNotEmpty() ->
                    Text(text = startCharacter.uppercase(), color = textColor ,modifier = Modifier.padding(start=10.dp), fontSize = 20.sp)

        }
        when {
            passwordList.isNotEmpty() ->
                passwordList.iterator().forEach { password ->
                    PasswordListItemCompose(data = password.toPasswordData())
                }
        }
    }
 }