package com.example.passwordvaultapp_mvvm_compose.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.passwordvaultapp_mvvm_compose.ui.theme.textFieldColor

@Composable
fun ProgressBar(
    value: State<Int>
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(15.dp)
            .background(color = textFieldColor)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth(value.value.toFloat() / 100f)
                .fillMaxHeight()
                .background(color = Color.White)
        ) {
        }
    }
}