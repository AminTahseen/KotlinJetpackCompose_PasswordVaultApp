package com.example.passwordvaultapp_mvvm_compose.common.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.example.passwordvaultapp_mvvm_compose.ui.theme.Purple500
import com.example.passwordvaultapp_mvvm_compose.ui.theme.greenButtonColor
import com.example.passwordvaultapp_mvvm_compose.ui.theme.textColor
import com.example.passwordvaultapp_mvvm_compose.ui.theme.textFieldColor

@Composable
fun YesNoDialog(
    dialogState:Boolean,
    dialogTitle:String,
    dialogSubtitle: String,
    dialogButtonYesText:String,
    dialogButtonNoText:String,
    onDismissRequest:(dialogState:Boolean) -> Unit,
    forDelete:Boolean,
    yesButtonAction:(dialogState:Boolean)->Unit
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
                        Text(dialogTitle,
                            fontSize = 30.sp, color = Color.White, fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(dialogSubtitle,
                            fontSize = 15.sp, color = textColor
                        )
                        Spacer(modifier = Modifier.padding(vertical = 10.dp))

                        Row(modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                            ) {
                            Text(text = dialogButtonNoText.uppercase(),
                                color = Purple500
                                , modifier = Modifier
                                    .padding(top = 5.dp, bottom = 10.dp)
                                    .clickable {
                                        onDismissRequest(dialogState)
                                    }
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            when{
                                forDelete->
                                    Text(text = dialogButtonYesText.uppercase(),
                                        color = Color.Red,
                                        fontWeight = FontWeight.Bold
                                        , modifier = Modifier
                                            .padding(top = 5.dp, bottom = 10.dp)
                                            .clickable {
                                                yesButtonAction(dialogState)
                                              //  onDismissRequest(dialogState)
                                            }
                                    )
                                else->
                                    Text(text = dialogButtonYesText.uppercase(),
                                        color = greenButtonColor,
                                        fontWeight = FontWeight.Bold
                                        , modifier = Modifier
                                            .padding(top = 5.dp, bottom = 10.dp)
                                            .clickable {
                                                onDismissRequest(dialogState)
                                            }
                                    )
                            }

                        }
                    }
                },
                properties = DialogProperties(dismissOnBackPress = false,dismissOnClickOutside = false),
                shape = RoundedCornerShape(8.dp)
            )
        }
    }
}