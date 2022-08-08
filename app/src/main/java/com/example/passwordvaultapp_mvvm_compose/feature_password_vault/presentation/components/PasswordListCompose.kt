package com.example.passwordvaultapp_mvvm_compose.feature_password_vault.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.passwordvaultapp_mvvm_compose.feature_password_vault.domain.models.PasswordData
import com.example.passwordvaultapp_mvvm_compose.ui.theme.textColor

@Composable
fun PasswordListCompose(startCharacter:String,passwordList: List<PasswordData>){
    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            Text(text = startCharacter.uppercase(), color = textColor ,modifier = Modifier.padding(start=10.dp), fontSize = 20.sp)

        }
        items(passwordList) { password ->
            PasswordListItemCompose(data = password)
        }
    }
 }