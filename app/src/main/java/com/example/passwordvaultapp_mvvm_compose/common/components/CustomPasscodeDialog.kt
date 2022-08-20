package com.example.passwordvaultapp_mvvm_compose.common.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.passwordvaultapp_mvvm_compose.feature_authentication.presentation.viewmodels.PassCodeViewModel
import com.example.passwordvaultapp_mvvm_compose.ui.theme.Purple500
import com.example.passwordvaultapp_mvvm_compose.ui.theme.textColor
import com.example.passwordvaultapp_mvvm_compose.ui.theme.textFieldColor

@Composable
fun PassCodeDialog(
    dialogState:Boolean,
    onDismissRequest:(dialogState:Boolean) -> Unit,
    passCodeViewModel: PassCodeViewModel= hiltViewModel(),
    onShowPassword: (Boolean) -> Unit
){
    val maxLength=5
    when{
        dialogState->{
            AlertDialog(onDismissRequest = {
                onDismissRequest(dialogState)
            },
                backgroundColor = textFieldColor,
                title = null,
                text=null,
                buttons = {
                          Column(modifier =Modifier
                              .fillMaxWidth(),
                              horizontalAlignment = Alignment.CenterHorizontally,
                          ) {
                              Spacer(modifier = Modifier.padding(vertical = 10.dp))
                              Text("Verify Passcode",
                              fontSize = 20.sp, color = Color.White
                                  )


                              TextField(
                                  value = passCodeViewModel.passCodeValue,
                                  modifier = Modifier
                                      .fillMaxWidth()
                                      .padding(top = 20.dp, end = 10.dp, start = 10.dp)
                                      .background(textFieldColor),
                                  onValueChange = {
                                      if (passCodeViewModel.passCodeValue.length <= maxLength) {
                                          passCodeViewModel.passCodeValue = it
                                      } else {
                                          Log.d("err", "maxxx")
                                      }
                               },
                                  textStyle = TextStyle(
                                      fontSize = 20.sp,
                                      color = Color.White
                                  ),
                                  singleLine = true,
                                  readOnly = true,
                                  placeholder = {
                                      Text(
                                          "Please verify pin code",
                                          color = textColor,
                                          fontSize = 17.sp,
                                          modifier = Modifier.padding(bottom = 4.dp)
                                      )
                                  }
                              )
                              PassCodeKeyboard(passCodeViewModel = passCodeViewModel, maxLength = maxLength)
                              Button(
                                  onClick = {
                                      if (passCodeViewModel.passCodeValue.length == maxLength) {
                                          when {
                                              passCodeViewModel.verifyPassCode() -> {
                                                  onShowPassword(true)
                                              }
                                              else->{
                                                  onShowPassword(false)
                                              }
                                          }
                                          onDismissRequest(dialogState)
                                      }
                                  }, modifier = Modifier
                                      .fillMaxWidth()
                                      .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
                              ) {
                                  Text(
                                      text = "Verify".uppercase(),
                                      color = Color.White,
                                      modifier = Modifier.padding(10.dp)
                                  )
                              }
                              Text(text = "Cancel".uppercase(),
                                  color = Purple500
                              , modifier = Modifier
                                      .padding(top = 5.dp, bottom = 10.dp)
                                      .clickable {
                                          onDismissRequest(dialogState)
                                      }
                              )

                          }
                },
                properties = DialogProperties(dismissOnBackPress = false,dismissOnClickOutside = false),
                shape = RoundedCornerShape(8.dp)
            )
        }
    }
}
@Composable
fun PassCodeKeyboard(
    passCodeViewModel: PassCodeViewModel,
    maxLength: Int,
){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)) {
        Row(Modifier.fillMaxWidth()) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)) {
                passCodeButton("1"){
                    if (passCodeViewModel.passCodeValue.length < maxLength) {
                        passCodeViewModel.passCodeValue=passCodeViewModel.passCodeValue+"1"
                    }
                }
            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)) {
                passCodeButton("2"){
                    if (passCodeViewModel.passCodeValue.length < maxLength) {
                        passCodeViewModel.passCodeValue=passCodeViewModel.passCodeValue+"2"
                    }
                }
            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)) {
                passCodeButton("3"){
                    if (passCodeViewModel.passCodeValue.length < maxLength) {
                        passCodeViewModel.passCodeValue=passCodeViewModel.passCodeValue+"3"
                    }
                }
            }
        }
        Row(Modifier.fillMaxWidth()) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)) {
                passCodeButton("4"){
                    if (passCodeViewModel.passCodeValue.length < maxLength) {
                        passCodeViewModel.passCodeValue=passCodeViewModel.passCodeValue+"4"
                    }
                }
            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)) {
                passCodeButton("5"){
                    if (passCodeViewModel.passCodeValue.length < maxLength) {
                        passCodeViewModel.passCodeValue=passCodeViewModel.passCodeValue+"5"
                    }
                }
            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)) {
                passCodeButton("6"){
                    if (passCodeViewModel.passCodeValue.length < maxLength) {
                        passCodeViewModel.passCodeValue=passCodeViewModel.passCodeValue+"6"
                    }
                }
            }
        }
        Row(Modifier.fillMaxWidth()) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)) {
                passCodeButton("7"){
                    if (passCodeViewModel.passCodeValue.length < maxLength) {
                        passCodeViewModel.passCodeValue=passCodeViewModel.passCodeValue+"7"
                    }
                }
            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)) {
                passCodeButton("8"){
                    if (passCodeViewModel.passCodeValue.length < maxLength) {
                        passCodeViewModel.passCodeValue=passCodeViewModel.passCodeValue+"8"
                    }
                }
            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)) {
                passCodeButton("9"){
                    if (passCodeViewModel.passCodeValue.length < maxLength) {
                        passCodeViewModel.passCodeValue=passCodeViewModel.passCodeValue+"9"
                    }
                }
            }
        }
        Row(Modifier.fillMaxWidth()) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)) {
                passCodeButton("0"){
                    if (passCodeViewModel.passCodeValue.length < maxLength) {
                        passCodeViewModel.passCodeValue=passCodeViewModel.passCodeValue+"0"
                    }
                }
            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)) {
                passCodeButton("X", forDelete = true){
                    passCodeViewModel.passCodeValue=passCodeViewModel.passCodeValue.substring(0, passCodeViewModel.passCodeValue.length-1)
                }
            }
        }
    }
}
@Composable
fun passCodeButton(number:String,forDelete:Boolean?=false,onClick:()->Unit){
        Button(
            onClick = { onClick() }, modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = textFieldColor)
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
