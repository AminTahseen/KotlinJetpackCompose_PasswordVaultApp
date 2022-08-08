package com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.passwordvaultapp_mvvm_compose.R
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.passwordvaultapp_mvvm_compose.common.utils.Screen
import com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.presentation.components.CategoryListCompose
import com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.presentation.viewmodels.VaultCategoryViewModel
import com.example.passwordvaultapp_mvvm_compose.ui.theme.appBgColor
import com.example.passwordvaultapp_mvvm_compose.ui.theme.textColor
import com.example.passwordvaultapp_mvvm_compose.ui.theme.textFieldColor

@Composable
fun VaultCategoriesScreen(
    navController: NavController,
    vaultViewModel: VaultCategoryViewModel = hiltViewModel()
){
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    var searchText by remember { mutableStateOf(TextFieldValue("")) }
    Scaffold(scaffoldState = scaffoldState) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(appBgColor)
        )
        {
            Row(
                modifier = Modifier
                    .padding(end = 10.dp, start = 10.dp, top = 30.dp)
                    .fillMaxWidth()
                ,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Vault Categories",
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
                Button(onClick = {
                    navController.navigate(Screen.AddNewVaultCategoryScreen.route)
                },
                    colors = ButtonDefaults.buttonColors(backgroundColor = appBgColor)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.add_box),
                        contentDescription ="Add Vault Category",
                        tint = Color.White
                        )
                }
            }

            TextField(
                value = searchText,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, end = 10.dp, start = 10.dp)
                    .background(textFieldColor)
                ,
                onValueChange = {
                    searchText=it
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

            val data= vaultViewModel.categories.observeAsState()

            data.value?.let { it1 -> CategoryListCompose(categoryList = it1.toList()) }
        }
    }
}