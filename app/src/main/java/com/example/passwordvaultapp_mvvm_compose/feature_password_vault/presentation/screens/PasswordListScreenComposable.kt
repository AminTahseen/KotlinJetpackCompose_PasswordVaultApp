package com.example.passwordvaultapp_mvvm_compose.feature_password_vault.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.passwordvaultapp_mvvm_compose.ui.theme.appBgColor

@Composable
fun PasswordListScreen(navController: NavController){
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    Scaffold(scaffoldState = scaffoldState) {
       Column(
           modifier = Modifier
               .fillMaxWidth()
               .fillMaxHeight()
               .background(appBgColor)
           .fillMaxWidth()
           .fillMaxHeight(),) {
           Text(text = "Password Screen", color = Color.White)

       }
   }
}