package com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.domain.models.VaultCategory
import com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.presentation.viewmodels.VaultCategoryViewModel
import com.example.passwordvaultapp_mvvm_compose.ui.theme.appBgColor
import com.example.passwordvaultapp_mvvm_compose.ui.theme.textColor
import com.example.passwordvaultapp_mvvm_compose.ui.theme.textFieldColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun AddNewVaultCategoryScreen(
    vaultViewModel: VaultCategoryViewModel = hiltViewModel()
){
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    var categoryName by remember { mutableStateOf(TextFieldValue("")) }
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    val categoryTypeOptions=
        listOf(
        "Bank Account",
        "Social Login",
        "Social Security Number",
        "Others"
    )
    var expanded by remember {
        mutableStateOf(false)
    }
    var selectedOption by remember {
        mutableStateOf(categoryTypeOptions[0])
    }
    val icon=if(expanded){
        Icons.Filled.KeyboardArrowUp
    }else{
        Icons.Filled.KeyboardArrowDown
    }
    Scaffold(scaffoldState = scaffoldState) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(appBgColor)
                .fillMaxWidth()
                .fillMaxHeight(),) {


            Text(
                text = "New Category",
                color = Color.White,
                modifier = Modifier.padding(end=10.dp, start = 10.dp, top = 30.dp),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Category Name",
                color = textColor,
                modifier = Modifier.padding(end=10.dp, start = 10.dp, top = 30.dp),
                fontSize = 20.sp,
            )
            TextField(
                value = categoryName,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, end = 10.dp, start = 10.dp)
                    .background(textFieldColor),
                onValueChange = {
                    categoryName = it
                },
                textStyle = TextStyle(
                    fontSize = 20.sp,
                    color = Color.White
                ),
                singleLine = true,
                placeholder = {
                    Text("Please specify category name", color = textColor, fontSize = 17.sp, modifier = Modifier.padding(bottom=4.dp))
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Category Type",
                color = textColor,
                modifier = Modifier.padding(end=10.dp, start = 10.dp, top = 30.dp),
                fontSize = 20.sp,
            )
            Box {
                TextField(
                    value = selectedOption,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, end = 10.dp, start = 10.dp)
                        .background(textFieldColor),
                    onValueChange = {},
                    textStyle = TextStyle(
                        fontSize = 20.sp,
                        color = Color.White
                    ),
                    singleLine = true,
                    trailingIcon = {
                        Icon(icon, "", modifier = Modifier.clickable {
                            expanded = !expanded
                        }, tint = Color.White)
                    },
                    readOnly = true
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 10.dp, start = 10.dp)
                ) {
                    categoryTypeOptions.forEach { label ->
                        DropdownMenuItem(onClick = {
                            selectedOption = label
                            expanded = false
                        },
                        modifier = Modifier.padding(end = 10.dp, start = 10.dp)
                        ) {
                            Text(label)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
            Button(onClick = {
                vaultViewModel.addVaultCategory(
                    VaultCategory(
                        categoryName = categoryName.text,
                        categoryType = selectedOption,
                        isVisible = true
                    )
                )
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)) {
                Text(text = "Create".uppercase(), color = Color.White, modifier = Modifier.padding(10.dp))
            }
            when{
                vaultViewModel.isMessageVisible.value->
                    LaunchedEffect(key1 = Unit) {
                        coroutineScope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = vaultViewModel.message.value.toString()
                            )
                        }
                        vaultViewModel.isMessageVisible.value=false
                    }
            }

        }
    }
}