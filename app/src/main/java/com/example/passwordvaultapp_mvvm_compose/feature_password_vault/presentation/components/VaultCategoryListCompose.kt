package com.example.passwordvaultapp_mvvm_compose.feature_password_vault.presentation.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.passwordvaultapp_mvvm_compose.R
import com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.presentation.viewmodels.VaultCategoryViewModel
import com.example.passwordvaultapp_mvvm_compose.ui.theme.appBgColor
import com.example.passwordvaultapp_mvvm_compose.ui.theme.greenButtonColor

@Composable
fun VaultCategoryList(
    selectedIndex:Int,
    vaultCategoryViewModel: VaultCategoryViewModel = hiltViewModel(),
    onCategorySelected: (String,Int?,Int) -> Unit
){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(start = 10.dp, end = 10.dp, top = 5.dp),
    ) {
        val data= vaultCategoryViewModel.categories.observeAsState()
        Log.d("categoryList",data.value?.size.toString())
        data.value?.let { it1 ->
            when{
                it1.toList().isNotEmpty() ->
                    it1.toList().forEachIndexed { index, element ->
                        if(selectedIndex==index){
                            Button(onClick = {
                                onCategorySelected(element.categoryName,element.id,index)
                            }, modifier = Modifier,
                                border = BorderStroke(3.dp, greenButtonColor),

                                colors = ButtonDefaults.buttonColors(backgroundColor = appBgColor)

                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.category),
                                        contentDescription ="",
                                        modifier = Modifier,
                                        tint = Color.White
                                    )
                                    Text("${element.categoryName}", fontSize = 15.sp, color = Color.White)
                                }
                            }
                        }else{
                            Button(onClick = {
                                onCategorySelected(element.categoryName,element.id,index)
                            }, modifier = Modifier,
                                colors = ButtonDefaults.buttonColors(backgroundColor = appBgColor)

                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.category),
                                        contentDescription ="",
                                        modifier = Modifier,
                                        tint = Color.White
                                    )
                                    Text("${element.categoryName}", fontSize = 15.sp, color = Color.White)
                                }
                            }
                        }
                    }
                else->
                    Text(
                        text = "No Categories Found",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.White
                    )
            }
        }
    }
}