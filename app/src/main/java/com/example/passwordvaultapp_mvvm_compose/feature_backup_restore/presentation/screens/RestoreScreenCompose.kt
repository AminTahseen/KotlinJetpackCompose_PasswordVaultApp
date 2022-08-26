package com.example.passwordvaultapp_mvvm_compose.feature_backup_restore.presentation.screens

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.passwordvaultapp_mvvm_compose.R
import com.example.passwordvaultapp_mvvm_compose.common.components.ProgressBar
import com.example.passwordvaultapp_mvvm_compose.ui.theme.appBgColor
import com.example.passwordvaultapp_mvvm_compose.ui.theme.textColor

@Composable
fun RestoreScreen(){
    var progress by remember { mutableStateOf(0) }
    val animatedProgress= animateIntAsState(
        targetValue = progress,
        animationSpec = tween(
            durationMillis = 5000,
            easing = FastOutSlowInEasing
        )
    )
    Column(
        verticalArrangement = Arrangement.Center, modifier = Modifier
            .background(appBgColor)
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Column( modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            , horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.backup_restore),
                contentDescription = "Icon",
                modifier = Modifier
                    .padding(10.dp)
                    .size(150.dp),
                tint = Color.White,

                )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Restore Data", fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            // progress bar
            Box(modifier = Modifier
                .padding(start = 30.dp, end = 30.dp, top = 30.dp)
                .fillMaxWidth()
            ){
                ProgressBar(value = animatedProgress)
            }
            Spacer(modifier = Modifier.height(30.dp))
            when(animatedProgress.value){
                100->
                    Text(text = "Restore Successful", color = textColor)
                else->
                    Text(text = "${animatedProgress.value}/100 Records Remaining", color = textColor)

            }
        }
        Button(
            onClick ={
                progress+=100
                     },
            modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)) {
            Text(text = "Restore Data".uppercase(), color = Color.White, modifier = Modifier.padding(10.dp))
        }
    }
}
