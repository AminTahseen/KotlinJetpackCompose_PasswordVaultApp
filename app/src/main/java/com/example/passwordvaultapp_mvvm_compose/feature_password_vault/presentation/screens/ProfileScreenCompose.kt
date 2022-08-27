package com.example.passwordvaultapp_mvvm_compose.feature_password_vault.presentation.screens

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.passwordvaultapp_mvvm_compose.R
import com.example.passwordvaultapp_mvvm_compose.acitivites.MainActivity
import com.example.passwordvaultapp_mvvm_compose.common.components.YesNoDialog
import com.example.passwordvaultapp_mvvm_compose.feature_password_vault.presentation.viewmodels.ProfileScreenViewModel
import com.example.passwordvaultapp_mvvm_compose.ui.theme.appBgColor
import com.example.passwordvaultapp_mvvm_compose.ui.theme.textColor
import kotlinx.coroutines.CoroutineScope

@Composable
fun ProfileScreen(
    profileScreenViewModel: ProfileScreenViewModel= hiltViewModel()
){
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val context = LocalContext.current
    var dialogState by remember { mutableStateOf(false) }
    var dialogStateClear by remember { mutableStateOf(false) }
    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    Scaffold(scaffoldState = scaffoldState) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(appBgColor)
                .fillMaxWidth()
                .fillMaxHeight(),
        ) {
            ProfileCardComposable()
            Spacer(modifier = Modifier.height(10.dp))
            ProfileOption("My Profile",painterResource(id = R.drawable.profile), action = {

            })
            ProfileOption("Clear Data", painterResource(id = R.drawable.delete), action = {
                dialogStateClear=true
            })
            Button(
                onClick = {
                    dialogState=true

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
            YesNoDialog(
                dialogState = dialogState,
                dialogTitle = "Logout ?",
                dialogSubtitle = "Are you sure you want to logout ?",
                dialogButtonYesText = "Logout",
                dialogButtonNoText = "Cancel",
                onDismissRequest ={ dialogState=!it },
                forDelete =true,
                yesButtonAction = {
                    (context as Activity).finish()
                    val mainIntent=Intent(context,MainActivity::class.java)
                    context.startActivity(mainIntent)
                }
            )
            YesNoDialog(
                dialogState = dialogStateClear,
                dialogTitle = "Clear Data ?",
                dialogSubtitle = "This will clear all your available data. Do you wish to proceed ?",
                dialogButtonYesText = "Clear Data",
                dialogButtonNoText = "Cancel",
                onDismissRequest ={ dialogStateClear=!it },
                forDelete =true,
                yesButtonAction = {
                    profileScreenViewModel.clearAllData()
                    dialogStateClear=!it
                }
            )
        }
    }
}
@Composable
fun ProfileCardComposable() {
    Card(
        modifier = Modifier
            .wrapContentSize()
            .clip(RoundedCornerShape(4.dp))
            .padding(16.dp),
        backgroundColor = appBgColor,
        elevation = 10.dp
    ) {
        Row(modifier = Modifier
            .height(intrinsicSize = IntrinsicSize.Max)
            .fillMaxWidth()
            .background(appBgColor)
        ) {
            ProfilePictureComposable()
            ProfileContentComposable()
        }
    }
}
@Composable
fun ProfilePictureComposable() {
    Image(
        painter = painterResource(id = R.drawable.profile),
        contentScale = ContentScale.Crop,
        modifier = Modifier.size(50.dp),
        contentDescription = "Profile picture holder"
    )
}

@Composable
fun ProfileContentComposable() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(start = 8.dp),
        verticalArrangement = Arrangement.aligned(Alignment.CenterVertically)
    ) {
        Text(
            text="Catalin Ghita",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 25.sp
        )
        Text(
            text = "Active now",
            style = MaterialTheme.typography.body2,
            color = textColor,
            fontSize = 18.sp
        )
    }
}
@Composable
fun ProfileOption(text: String, painterResource: Painter,action:()-> Unit){
    Row(modifier = Modifier
        .height(intrinsicSize = IntrinsicSize.Max)
        .fillMaxWidth()
        .background(appBgColor)
        .padding(10.dp)
        .clickable {
            action()
        }
    ) {
        Image(
            painter = painterResource,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(30.dp),
            contentDescription = "Profile picture holder"
        )
        Text(
            text=text,
            color = textColor,
            fontSize = 18.sp,
            modifier = Modifier.padding(start = 10.dp)
        )
    }
}