package com.example.passwordvaultapp_mvvm_compose.feature_password_vault.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Lock

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.passwordvaultapp_mvvm_compose.ui.theme.appBgColor
import com.example.passwordvaultapp_mvvm_compose.R
import com.example.passwordvaultapp_mvvm_compose.common.utils.Screen
import com.example.passwordvaultapp_mvvm_compose.ui.theme.textColor


@Composable
fun MainScreen(navController: NavController){
    Column(
        verticalArrangement = Arrangement.Center, modifier = Modifier
        .background(appBgColor)
        .fillMaxWidth()
        .fillMaxHeight(), horizontalAlignment = Alignment.CenterHorizontally) {
        Column( modifier = Modifier.fillMaxWidth().weight(1f)
        , horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.Lock,
                contentDescription = "Icon",
                modifier = Modifier.padding(10.dp).size(150.dp),
                tint = Color.White,

            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Secure Vault", fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(text = "Visually Takes Care of your sensitive" +
                    "\n data using smart encryption." +
                    "\n Its the most powerful encryption ever",
                fontSize = 18.sp,
                color = textColor,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                lineHeight = 23.sp
            )
        }
        Button(onClick = {
            navController.navigate(Screen.PasscodeScreen.route)
        }, modifier = Modifier
            .fillMaxWidth()

            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)) {

            Text(text = "Provide Access".uppercase(), color = Color.White, modifier = Modifier.padding(10.dp))
        }
    }
}