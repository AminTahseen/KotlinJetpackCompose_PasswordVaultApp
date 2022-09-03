package com.example.passwordvaultapp_mvvm_compose.feature_password_vault.presentation.screens

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.passwordvaultapp_mvvm_compose.R
import com.example.passwordvaultapp_mvvm_compose.common.components.PassCodeDialog
import com.example.passwordvaultapp_mvvm_compose.common.components.YesNoDialog
import com.example.passwordvaultapp_mvvm_compose.feature_password_vault.presentation.viewmodels.VaultViewModel
import com.example.passwordvaultapp_mvvm_compose.ui.theme.appBgColor
import com.example.passwordvaultapp_mvvm_compose.ui.theme.textColor
import com.example.passwordvaultapp_mvvm_compose.ui.theme.textFieldColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun ViewVaultDetailsScreen(
    id:Int,
    navController: NavHostController,
    vaultViewModel: VaultViewModel = hiltViewModel(),
    ){
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    var generatedPass by remember { mutableStateOf(TextFieldValue("")) }
    var showHidePass by remember { mutableStateOf(false) }
    val clipboardManager: ClipboardManager = LocalClipboardManager.current
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    var dialogState by remember {mutableStateOf(false)}
    var deleteDialogState by remember {mutableStateOf(false)}
    val activity = (LocalContext.current as? Activity)
    val context= LocalContext.current

    fun onShowPassword(
        status:Boolean
    ) {
        when{
            status->{
                coroutineScope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = "Pin Verified !"
                    )
                }
                showHidePass=true
            }
            else->
                coroutineScope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = "Wrong Pin !"
                    )
                }
        }
        Log.d("status", status.toString())
    }
    Scaffold(scaffoldState = scaffoldState) {
        Log.d("vaultId",id.toString())
        vaultViewModel.getVaultById(id)
        val data=vaultViewModel.selectedVault.observeAsState()
        Log.d("vaultDet",data.value.toString())
        val dataValue= data?.value
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

            Spacer(modifier = Modifier.height(50.dp))
            if (dataValue != null) {
                if (dataValue.vaultLogoURL==null)
                    Image(
                        painter = painterResource(id = R.drawable.lock),
                        contentDescription = null,
                        modifier = Modifier
                            .size(100.dp)
                            .padding(10.dp),
                        contentScale = ContentScale.Fit,
                    )
                else
                    Image(
                       bitmap = dataValue.vaultLogoURL.asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier
                            .size(100.dp)
                            .padding(10.dp),
                        contentScale = ContentScale.Fit,
                    )
                Text(
                    text = dataValue.vaultName,
                    color = Color.White,
                    modifier = Modifier.padding(end = 10.dp, start = 10.dp, top = 30.dp),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    value = TextFieldValue(dataValue.vaultPassword),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, end = 10.dp, start = 10.dp)
                        .background(textFieldColor),
                    onValueChange = {
                        generatedPass = it
                    },
                    textStyle = TextStyle(
                        fontSize = 30.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    ),
                    readOnly = true,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = if (showHidePass) VisualTransformation.None else PasswordVisualTransformation(),

                    placeholder = {
                        Text(
                            "Please adjust the settings to generate",
                            color = textColor,
                            fontSize = 17.sp,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                    },
                    trailingIcon = {
                       Icon(
                            painter = painterResource(id = R.drawable.copy),
                            contentDescription = "", tint = textColor,
                           modifier = Modifier.clickable {
                               when{
                                   showHidePass->{
                                       clipboardManager.setText(AnnotatedString((generatedPass.text)))
                                       coroutineScope.launch {
                                           scaffoldState.snackbarHostState.showSnackbar(
                                               message = "Copied to clipboard"
                                           )
                                       }
                                       showHidePass=false
                                   }else->{
                                   coroutineScope.launch {
                                       scaffoldState.snackbarHostState.showSnackbar(
                                           message = "Please Verify Pin First !"
                                       )
                                   }
                               }
                               }
                           }
                        )
                    },
                )
            }
            Button(
                onClick = {
                          dialogState=true

                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
            ) {
                Text(
                    text = "View Password".uppercase(),
                    color = Color.White,
                    modifier = Modifier.padding(10.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            if(dataValue!=null){
                Icon(
                    painter = painterResource(id = R.drawable.category),
                    contentDescription ="",modifier = Modifier
                        .size(55.dp)
                        .padding(top = 20.dp),
                    tint = Color.White
                )
                Text(
                    text = dataValue.vaultCategory.uppercase(),
                    color = Color.White,
                    modifier = Modifier.padding(10.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .padding(end = 10.dp, start = 10.dp, top = 30.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick =
                {
                    when{
                        showHidePass->
                            navController.navigate("edit_vault_screen?" + "vaultId=${dataValue?.id}")
                        else->
                            dialogState=true
                    }

                },
                    colors = ButtonDefaults.buttonColors(backgroundColor = appBgColor)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.edit),
                        contentDescription ="Edit",
                        tint = Color.White
                    )
                }
                Button(onClick =
                {
                    when{
                        showHidePass->
                            deleteDialogState=true
                    else->
                        dialogState=true
                    }
                },
                    colors = ButtonDefaults.buttonColors(backgroundColor = appBgColor)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.delete),
                        contentDescription ="Delete",
                        tint = Color.White
                    )
                }
            }
            PassCodeDialog(
                dialogState = dialogState,
                onDismissRequest = { dialogState=!it },
                onShowPassword =::onShowPassword
            )
            YesNoDialog(
                dialogState = deleteDialogState,
                dialogTitle = "Delete Vault ?",
                dialogSubtitle = "Are you sure you want to delete this vault ?",
                dialogButtonYesText = "Delete",
                dialogButtonNoText = "Cancel",
                onDismissRequest ={ deleteDialogState=!it },
                forDelete = true,
                yesButtonAction = {
                    if (dataValue != null) {
                            vaultViewModel.deleteVault(dataValue)
                            deleteDialogState=!it
                    }
                    activity?.finish()

                }
            )
        }
    }
}