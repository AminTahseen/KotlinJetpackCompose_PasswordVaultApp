package com.example.passwordvaultapp_mvvm_compose.feature_password_vault.presentation.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.passwordvaultapp_mvvm_compose.common.components.SearchBar
import com.example.passwordvaultapp_mvvm_compose.feature_password_vault.domain.models.VaultPassword
import com.example.passwordvaultapp_mvvm_compose.feature_password_vault.presentation.components.PasswordListCompose
import com.example.passwordvaultapp_mvvm_compose.feature_password_vault.presentation.viewmodels.VaultViewModel
import com.example.passwordvaultapp_mvvm_compose.ui.theme.appBgColor

@Composable
fun PasswordListScreen(
    vaultViewModel: VaultViewModel = hiltViewModel()
) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    var text by remember { mutableStateOf(TextFieldValue("")) }
    val alphabetsList = listOf(
        "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
        "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
        "x", "y", "z"
    )
    val data = vaultViewModel.vaults.observeAsState()
    var searchedList= emptyList<VaultPassword>()
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
                modifier = Modifier.padding(end = 10.dp, start = 10.dp, top = 30.dp),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            SearchBar(text = text, onValueChange = {
                text = it
                if(searchedList.isNotEmpty())
                    searchedList= emptyList()
                vaultViewModel.isSearchFilterApply.value=true
                if(!it.text.isNullOrBlank()){
                    Log.d("searchedInside","if")
                    data.value?.let { listVaults: List<VaultPassword> ->
                        val list = listVaults.filter { s ->
                            s.vaultName.uppercase().startsWith(it.text.uppercase())
                        }
                        when (list.isNotEmpty()) {
                            true->
                                searchedList=list
                        }
                    }
                }else{
                    Log.d("searchedInside","else")
                    searchedList= emptyList()
                }
            }
            )
            Spacer(modifier = Modifier.height(10.dp))
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(450.dp)
            ) {
                item {
                    Log.d("searchedInside",searchedList.size.toString())
                    Log.d("searchedInside Bool",vaultViewModel.isSearchFilterApply.value.toString())
                    when{
                        vaultViewModel.isSearchFilterApply.value->
                            when{
                                searchedList.isNotEmpty() ->
                                    searchedList.iterator().forEach {
                                        when (searchedList.isNotEmpty()) {
                                            true ->
                                                PasswordListCompose(
                                                    startCharacter = it.vaultName[0].toString(),
                                                    passwordList = searchedList,
                                                )
                                        }
                                    }
                                else->
                                    alphabetsList.iterator().forEach {
                                        data.value?.let { listVaults: List<VaultPassword> ->
                                            val list = listVaults.filter { s ->
                                                s.vaultName.uppercase().startsWith(it.uppercase())
                                            }
                                            when (list.isNotEmpty()) {
                                                true ->
                                                    PasswordListCompose(
                                                        startCharacter = it,
                                                        passwordList = list,
                                                    )
                                            }
                                        }
                                    }
                            }
                        else->
                            alphabetsList.iterator().forEach {
                                data.value?.let { listVaults: List<VaultPassword> ->
                                    val list = listVaults.filter { s ->
                                        s.vaultName.uppercase().startsWith(it.uppercase())
                                    }
                                    when (list.isNotEmpty()) {
                                        true ->
                                            PasswordListCompose(
                                                startCharacter = it,
                                                passwordList = list,
                                            )
                                    }
                                }
                            }
                    }
                }
            }
        }
    }
}