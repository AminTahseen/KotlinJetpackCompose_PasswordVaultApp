package com.example.passwordvaultapp_mvvm_compose.feature_password_vault.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.passwordvaultapp_mvvm_compose.feature_password_vault.domain.models.PasswordData
import com.example.passwordvaultapp_mvvm_compose.feature_password_vault.presentation.components.PasswordListCompose
import com.example.passwordvaultapp_mvvm_compose.ui.theme.appBgColor
import com.example.passwordvaultapp_mvvm_compose.ui.theme.textColor
import com.example.passwordvaultapp_mvvm_compose.ui.theme.textFieldColor

@Composable
fun PasswordListScreen(navController: NavController){
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    var text by remember { mutableStateOf(TextFieldValue("")) }
    val listItems=listOf(
        PasswordData("Amazon","1232323","https://pngimg.com/uploads/amazon/amazon_PNG18.png"),
        PasswordData("Apple","1232323","https://upload.wikimedia.org/wikipedia/commons/a/ab/Apple-logo.png"),
        PasswordData("Bmazon","1232323","https://pngimg.com/uploads/amazon/amazon_PNG18.png"),
        PasswordData("Bpple","1232323","https://upload.wikimedia.org/wikipedia/commons/a/ab/Apple-logo.png"),
        PasswordData("Bpple","1232323","https://upload.wikimedia.org/wikipedia/commons/a/ab/Apple-logo.png")
    )
    Scaffold(scaffoldState = scaffoldState) {
       Column(
           modifier = Modifier
               .fillMaxWidth()
               .fillMaxHeight()
               .background(appBgColor)
               .fillMaxWidth()
               .fillMaxHeight(),
       ) {
           Text(
               text = "Passwords",
               color = Color.White,
               modifier = Modifier.padding(end=10.dp, start = 10.dp, top = 30.dp),
               fontSize = 30.sp,
               fontWeight = FontWeight.Bold
               )
           TextField(
               value = text,
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(top = 20.dp, end = 10.dp, start = 10.dp)
                   .background(textFieldColor)
               ,
               onValueChange = {
                  text=it
               },
               textStyle = TextStyle(
                   fontSize = 20.sp,
                   color = Color.White
               ),
               singleLine = true,
               leadingIcon = {
                   Icon(
                       imageVector = Icons.Outlined.Search,
                       contentDescription = "", tint = textColor
                   )
               },
               placeholder = {
                   Text("Search", color = textColor, fontSize = 17.sp, modifier = Modifier.padding(bottom=4.dp))
               }
           )
           Spacer(modifier = Modifier.height(10.dp))
           PasswordListCompose(
               startCharacter = "a",
               listItems.filter { s -> s.storeFor.uppercase().startsWith("a".uppercase()) }
           )
           PasswordListCompose(
               startCharacter = "b",
               listItems.filter { s -> s.storeFor.uppercase().startsWith("b".uppercase()) }
           )
       }
   }
}