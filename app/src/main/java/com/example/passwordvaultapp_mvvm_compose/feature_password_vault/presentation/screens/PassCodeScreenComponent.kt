package com.example.passwordvaultapp_mvvm_compose.feature_password_vault.presentation.screens

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.passwordvaultapp_mvvm_compose.LoggedInActivity
import com.example.passwordvaultapp_mvvm_compose.common.utils.Screen
import com.example.passwordvaultapp_mvvm_compose.feature_password_vault.presentation.viewmodels.PassCodeViewModel
import com.example.passwordvaultapp_mvvm_compose.ui.theme.appBgColor
import com.example.passwordvaultapp_mvvm_compose.ui.theme.greenTextColor
import com.example.passwordvaultapp_mvvm_compose.ui.theme.textFieldColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun PassCodeScreen(navController: NavController,passCodeViewModel: PassCodeViewModel){
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    val maxLength=5
    val context = LocalContext.current

    Scaffold(scaffoldState = scaffoldState) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(appBgColor),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                readOnly = true,
                value = passCodeViewModel.passCodeValue,
                modifier = Modifier
                    .background(textFieldColor),
                onValueChange = {
                    if (passCodeViewModel.passCodeValue.length <= maxLength) {
                        passCodeViewModel.passCodeValue = it
                    } else {
                        Log.d("err", "maxxx")
                    }
                },
                textStyle = TextStyle(
                    color = greenTextColor,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 5.sp
                ),
                singleLine = true,
            )
            Spacer(modifier = Modifier.height(20.dp))
            passCodeKeyboard(
                passCodeViewModel,
                scaffoldState,
                coroutineScope,
                maxLength,
                context
                )
        }
    }
}

@Composable
fun passCodeKeyboard(
    passCodeViewModel: PassCodeViewModel,
    scaffoldState: ScaffoldState,
    coroutineScope: CoroutineScope,
    maxLength: Int,
    context:Context
    ){
    Column(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
        Row(Modifier.fillMaxWidth()) {
            Column(modifier =  Modifier.fillMaxWidth().weight(1f)) {
                passCodeButton("1"){
                    if (passCodeViewModel.passCodeValue.length < maxLength) {
                        passCodeViewModel.passCodeValue=passCodeViewModel.passCodeValue+"1"
                    }
                }
            }
            Column(modifier =  Modifier.fillMaxWidth().weight(1f)) {
                passCodeButton("2"){
                    if (passCodeViewModel.passCodeValue.length < maxLength) {
                        passCodeViewModel.passCodeValue=passCodeViewModel.passCodeValue+"2"
                    }
                }
            }
            Column(modifier =  Modifier.fillMaxWidth().weight(1f)) {
                passCodeButton("3"){
                    if (passCodeViewModel.passCodeValue.length < maxLength) {
                        passCodeViewModel.passCodeValue=passCodeViewModel.passCodeValue+"3"
                    }
                }
            }
        }
        Row(Modifier.fillMaxWidth()) {
            Column(modifier =  Modifier.fillMaxWidth().weight(1f)) {
                passCodeButton("4"){
                    if (passCodeViewModel.passCodeValue.length < maxLength) {
                        passCodeViewModel.passCodeValue=passCodeViewModel.passCodeValue+"4"
                    }
                }
            }
            Column(modifier =  Modifier.fillMaxWidth().weight(1f)) {
                passCodeButton("5"){
                    if (passCodeViewModel.passCodeValue.length < maxLength) {
                        passCodeViewModel.passCodeValue=passCodeViewModel.passCodeValue+"5"
                    }
                }
            }
            Column(modifier =  Modifier.fillMaxWidth().weight(1f)) {
                passCodeButton("6"){
                    if (passCodeViewModel.passCodeValue.length < maxLength) {
                        passCodeViewModel.passCodeValue=passCodeViewModel.passCodeValue+"6"
                    }
                }
            }
        }
        Row(Modifier.fillMaxWidth()) {
            Column(modifier =  Modifier.fillMaxWidth().weight(1f)) {
                passCodeButton("7"){
                    if (passCodeViewModel.passCodeValue.length < maxLength) {
                        passCodeViewModel.passCodeValue=passCodeViewModel.passCodeValue+"7"
                    }
                }
            }
            Column(modifier =  Modifier.fillMaxWidth().weight(1f)) {
                passCodeButton("8"){
                    if (passCodeViewModel.passCodeValue.length < maxLength) {
                        passCodeViewModel.passCodeValue=passCodeViewModel.passCodeValue+"8"
                    }
                }
            }
            Column(modifier =  Modifier.fillMaxWidth().weight(1f)) {
                passCodeButton("9"){
                    if (passCodeViewModel.passCodeValue.length < maxLength) {
                        passCodeViewModel.passCodeValue=passCodeViewModel.passCodeValue+"9"
                    }
                }
            }
        }
        Row(Modifier.fillMaxWidth()) {
            Column(modifier =  Modifier.fillMaxWidth().weight(1f)) {
                passCodeButton("0"){
                    if (passCodeViewModel.passCodeValue.length < maxLength) {
                        passCodeViewModel.passCodeValue=passCodeViewModel.passCodeValue+"0"
                    }
                }
            }
            Column(modifier =  Modifier.fillMaxWidth().weight(1f)) {
                passCodeButton("X", forDelete = true){
                    passCodeViewModel.passCodeValue=passCodeViewModel.passCodeValue.substring(0, passCodeViewModel.passCodeValue.length-1)
                }
            }
            Column(modifier =  Modifier.fillMaxWidth().weight(1f).padding(end = 30.dp)) {
                passCodeButton("OK"){
                    if (passCodeViewModel.passCodeValue.length == maxLength) {
                        when{
                            passCodeViewModel.verifyPassCode()->{
                                val loggedInIntent=Intent(context,LoggedInActivity::class.java)
                                context.startActivity(loggedInIntent)
                            }
                            else->{
                                coroutineScope.launch {
                                    scaffoldState.snackbarHostState.showSnackbar(
                                        message = "Incorrect Passcode !",
                                    )
                                }
                            }
                        }

                    }else{
                        coroutineScope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = "Passcode cannot be less than 5 digits !",
                            )
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun passCodeButton(number:String,forDelete:Boolean?=false,onClick:()->Unit){
    if(number=="OK") {
        Button(
            onClick = { onClick() }, modifier = Modifier.fillMaxWidth(),
        ) {
            if (forDelete == true) {
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = "Icon",
                    modifier = Modifier.padding(10.dp),

                    tint = Color.White
                )
            } else {
                Text(
                    text = number,
                    color = Color.White,
                    modifier = Modifier.padding(10.dp),
                    fontSize = 20.sp
                )
            }
        }
    }else{
        Button(
            onClick = { onClick() }, modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = appBgColor)
        ) {
            if (forDelete == true) {
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = "Icon",
                    modifier = Modifier.padding(10.dp),

                    tint = Color.White
                )
            } else {
                Text(
                    text = number,
                    color = Color.White,
                    modifier = Modifier.padding(10.dp),
                    fontSize = 20.sp
                )
            }
        }
    }
}
