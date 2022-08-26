package com.example.passwordvaultapp_mvvm_compose.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.passwordvaultapp_mvvm_compose.ui.theme.textColor
import com.example.passwordvaultapp_mvvm_compose.ui.theme.textFieldColor

@Composable
fun SearchBar(
    text: TextFieldValue,
    onValueChange:(value:TextFieldValue)->Unit
){
    TextField(
        value = text,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, end = 10.dp, start = 10.dp)
            .background(textFieldColor),
        onValueChange =onValueChange,
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
            Text(
                "Search",
                color = textColor,
                fontSize = 17.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
    )

}