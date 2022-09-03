package com.example.passwordvaultapp_mvvm_compose.common.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.passwordvaultapp_mvvm_compose.ui.theme.appBgColor
import com.example.passwordvaultapp_mvvm_compose.ui.theme.textColor
import com.example.passwordvaultapp_mvvm_compose.ui.theme.textFieldColor

@Composable
fun RestoreDialog(
    dialogState:Boolean,
    dialogTitle:String,
    dialogSubtitle:String,
    option1Text:String,
    onDismissRequest:(dialogState:Boolean) -> Unit,
    option1Action:(dialogState:Boolean)->Unit,
){
    when {
        dialogState -> {
            AlertDialog(onDismissRequest = {
                onDismissRequest(dialogState)
            },
                backgroundColor = textFieldColor,
                title = null,
                text=null,
                buttons = {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    ) {
                        Text(
                            dialogTitle,
                            fontSize = 30.sp, color = Color.White, fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(dialogSubtitle,
                            fontSize = 18.sp, color = textColor
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Button(
                            onClick = {
                                option1Action(dialogState)
                                onDismissRequest(dialogState)
                            }, modifier = Modifier
                                .fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(backgroundColor = appBgColor)
                        ) {
                            Text(
                                text = option1Text.uppercase(),
                                color = Color.White,
                                modifier = Modifier.padding(10.dp)
                            )
                        }
                    }
                }
            )
        }
    }

}