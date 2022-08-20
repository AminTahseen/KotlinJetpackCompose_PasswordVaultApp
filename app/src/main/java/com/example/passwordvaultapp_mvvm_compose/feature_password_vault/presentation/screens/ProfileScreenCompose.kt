package com.example.passwordvaultapp_mvvm_compose.feature_password_vault.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.passwordvaultapp_mvvm_compose.R
import com.example.passwordvaultapp_mvvm_compose.ui.theme.appBgColor

@Composable
fun ProfileScreen(navController: NavController){
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    Scaffold(scaffoldState = scaffoldState) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(appBgColor)
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.person),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .padding(10.dp),
                contentScale = ContentScale.Fit,
            )
            Text(
                text = "User Name",
                color = Color.White,
                modifier = Modifier.padding(end = 10.dp, start = 10.dp),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "username@domain.com",
                color = Color.White,
                modifier = Modifier.padding(end = 10.dp, start = 10.dp),
                fontSize = 20.sp,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {

                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 10.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
            ) {
                Text(
                    text = "Logout".uppercase(),
                    color = Color.White,
                    modifier = Modifier.padding(10.dp)
                )
            }

        }
    }
}