package com.example.passwordvaultapp_mvvm_compose.feature_password_vault.presentation.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.abhinav.passwordgenerator.PasswordGenerator
import com.example.passwordvaultapp_mvvm_compose.R
import com.example.passwordvaultapp_mvvm_compose.ui.theme.Purple500
import com.example.passwordvaultapp_mvvm_compose.ui.theme.appBgColor
import com.example.passwordvaultapp_mvvm_compose.ui.theme.textColor
import com.example.passwordvaultapp_mvvm_compose.ui.theme.textFieldColor
import kotlin.math.roundToInt
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.passwordvaultapp_mvvm_compose.feature_password_vault.presentation.viewmodels.VaultViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun GenerateVaultPassScreen(
    navController: NavController,
    vaultViewModel: VaultViewModel = hiltViewModel(),
){
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    var generatedPass by remember { mutableStateOf(TextFieldValue("")) }
    var sliderState by remember { mutableStateOf(6f) }
    val numberCheckedState = remember { mutableStateOf(false) }
    val upperCaselettersCheckedState = remember { mutableStateOf(false) }
    val lowerCaselettersCheckedState = remember { mutableStateOf(false) }
    val symbolsCheckedState = remember { mutableStateOf(false) }
    val clipboardManager: ClipboardManager = LocalClipboardManager.current
    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    Scaffold(scaffoldState = scaffoldState) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(appBgColor)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .fillMaxHeight(),
        ) {
            Text(
                text = "Generate Password",
                color = Color.White,
                modifier = Modifier.padding(end = 10.dp, start = 10.dp, top = 30.dp),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Generated Password",
                color = textColor,
                modifier = Modifier.padding(end = 10.dp, start = 10.dp, top = 30.dp),
                fontSize = 20.sp,
            )
            TextField(
                value = generatedPass,
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
                                !generatedPass.text.isNullOrBlank()->{
                                    clipboardManager.setText(AnnotatedString((generatedPass.text)))
                                    coroutineScope.launch {
                                        scaffoldState.snackbarHostState.showSnackbar(
                                            message = "Copied to clipboard"
                                        )
                                    }

                                }else->{
                                coroutineScope.launch {
                                    scaffoldState.snackbarHostState.showSnackbar(
                                        message = "Please generate before copying"
                                    )
                                }
                                }
                            }
                        }

                    )
                },


            )
            Text(
                text = "Password Length",
                color = textColor,
                modifier = Modifier.padding(end = 10.dp, start = 10.dp, top = 30.dp),
                fontSize = 20.sp,
            )
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, end = 10.dp, start = 10.dp)
                .background(textFieldColor),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "6",
                        color = Color.White,
                        fontSize = 15.sp,
                        modifier = Modifier.padding(end = 10.dp, start = 10.dp, top = 15.dp),
                    )
                    Slider(
                        value = sliderState,
                        steps = 0,
                        valueRange = 6f..32f,
                        onValueChange = { newValue ->
                            sliderState = newValue
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.5f)
                    )
                    Text(
                        text = "32",
                        color = Color.White,
                        fontSize = 15.sp,
                        modifier = Modifier.padding(end = 10.dp, start = 10.dp, top = 15.dp),
                    )
                }
                Text(
                    text = "Length = ${sliderState.roundToInt()}",
                    color = Color.White,
                    fontSize = 15.sp,
                    modifier = Modifier.padding(end = 10.dp, start = 10.dp, top = 5.dp, bottom = 15.dp),
                )
            }
            Text(
                text = "Settings",
                color = textColor,
                modifier = Modifier.padding(end = 10.dp, start = 10.dp, top = 30.dp),
                fontSize = 20.sp,
            )
            SettingsComposable(
                numberCheckedState,
                upperCaselettersCheckedState,
                lowerCaselettersCheckedState,
                symbolsCheckedState
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = {
                val generatedPassword=vaultViewModel.generatePassword(
                    length = sliderState.roundToInt(),
                    includeNumbers = numberCheckedState.value,
                    includeLowerCase = lowerCaselettersCheckedState.value,
                    includeUpperCase = upperCaselettersCheckedState.value,
                    includeSymbols = symbolsCheckedState.value
                )
                generatedPass=TextFieldValue(generatedPassword)
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)) {
                Text(text = "Generate Password".uppercase(), color = Color.White, modifier = Modifier.padding(10.dp))
            }
            Button(onClick = {
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
                border = BorderStroke(3.dp, Purple500),
                colors = ButtonDefaults.buttonColors(backgroundColor = appBgColor)

            ) {
                Text(text = "Select Password".uppercase(), color = Color.White, modifier = Modifier.padding(10.dp))
            }
            Spacer(modifier = Modifier.height(20.dp))

        }
    }
}
@Composable
fun SettingsComposable(
    numberCheckedState: MutableState<Boolean>,
    upperCaseLettersCheckedState: MutableState<Boolean>,
    lowerCaseLettersCheckedState: MutableState<Boolean>,
    symbolsCheckedState: MutableState<Boolean>
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 20.dp, end = 10.dp, start = 10.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)

    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(textFieldColor),

        horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = "Include Numbers",
                color = Color.White,
                modifier = Modifier.padding(end = 10.dp, start = 10.dp, top = 20.dp, bottom = 20.dp),
                fontSize = 16.sp,
            )
            Switch(
                modifier = Modifier.padding(end = 10.dp, start = 10.dp, top = 20.dp, bottom = 20.dp),
                checked = numberCheckedState.value,
                onCheckedChange = { numberCheckedState.value = it },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    uncheckedThumbColor = Color.LightGray,
                    checkedTrackColor = Color.Blue,
                    uncheckedTrackColor = appBgColor,
                    checkedTrackAlpha = 1.0f,
                    uncheckedTrackAlpha = 1.0f
                )
            )
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(textFieldColor),

            horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = "Include Uppercase Letters",
                color = Color.White,
                modifier = Modifier.padding(end = 10.dp, start = 10.dp, top = 20.dp, bottom = 20.dp),
                fontSize = 16.sp,
            )
            Switch(
                modifier = Modifier.padding(end = 10.dp, start = 10.dp, top = 20.dp, bottom = 20.dp),
                checked = upperCaseLettersCheckedState.value,
                onCheckedChange = { upperCaseLettersCheckedState.value = it },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    uncheckedThumbColor = Color.LightGray,
                    checkedTrackColor = Color.Blue,
                    uncheckedTrackColor = appBgColor,
                    checkedTrackAlpha = 1.0f,
                    uncheckedTrackAlpha = 1.0f
                )
            )
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .background(textFieldColor),

            horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = "Include Lowercase Letters",
                color = Color.White,
                modifier = Modifier.padding(end = 10.dp, start = 10.dp, top = 20.dp, bottom = 20.dp),
                fontSize = 16.sp,
            )
            Switch(
                modifier = Modifier.padding(end = 10.dp, start = 10.dp, top = 20.dp, bottom = 20.dp),
                checked = lowerCaseLettersCheckedState.value,
                onCheckedChange = { lowerCaseLettersCheckedState.value = it },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    uncheckedThumbColor = Color.LightGray,
                    checkedTrackColor = Color.Blue,
                    uncheckedTrackColor = appBgColor,
                    checkedTrackAlpha = 1.0f,
                    uncheckedTrackAlpha = 1.0f
                )
            )
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(textFieldColor),

            horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = "Include Symbols",
                color = Color.White,
                modifier = Modifier.padding(end = 10.dp, start = 10.dp, top = 20.dp, bottom = 20.dp),
                fontSize = 16.sp,
            )
            Switch(
                modifier = Modifier.padding(end = 10.dp, start = 10.dp, top = 20.dp, bottom = 20.dp),
                checked =symbolsCheckedState.value,
                onCheckedChange = { symbolsCheckedState.value = it },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    uncheckedThumbColor = Color.LightGray,
                    checkedTrackColor = Color.Blue,
                    uncheckedTrackColor = appBgColor,
                    checkedTrackAlpha = 1.0f,
                    uncheckedTrackAlpha = 1.0f
                )
            )
        }

    }
}