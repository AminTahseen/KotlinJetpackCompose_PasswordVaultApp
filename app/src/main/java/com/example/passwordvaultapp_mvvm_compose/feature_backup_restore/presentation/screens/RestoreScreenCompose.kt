package com.example.passwordvaultapp_mvvm_compose.feature_backup_restore.presentation.screens

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.passwordvaultapp_mvvm_compose.R
import com.example.passwordvaultapp_mvvm_compose.common.components.ProgressBar
import com.example.passwordvaultapp_mvvm_compose.common.components.RestoreDialog
import com.example.passwordvaultapp_mvvm_compose.common.utils.FileUtils
import com.example.passwordvaultapp_mvvm_compose.feature_backup_restore.presentation.viewmodels.RestoreViewModel
import com.example.passwordvaultapp_mvvm_compose.ui.theme.appBgColor
import com.example.passwordvaultapp_mvvm_compose.ui.theme.textColor


@Composable
fun RestoreScreen(
    restoreViewModel: RestoreViewModel = hiltViewModel()
){
    var dialogState by remember { mutableStateOf(false) }

    val animatedProgress= animateIntAsState(
        targetValue = restoreViewModel.progress.value,
        animationSpec = tween(
            durationMillis = 5000,
            easing = FastOutSlowInEasing
        )
    )

    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents()
    ) {
        // uri result
        it.forEach {uri->
            val fileUtils=FileUtils()
            val fullFilePath: String? =fileUtils.getPathFromUri(context = context, uri = uri!!)
            Log.d("filePath",fullFilePath!!)
            if(fullFilePath!!.contains("categories"))
                restoreViewModel.restoreData(fullFilePath!!)
            else
                restoreViewModel.restoreVaultData(fullFilePath!!)
        }

    }
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // Permission Accepted: Do something
            launcher.launch("*/*")
        } else {
            // Permission Denied: Do something
        }
    }

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
            Text(text = "${restoreViewModel.message.value}", color = textColor)
        }
        Button(
            onClick ={
                dialogState=true
                     },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)) {
            Text(text = "Restore Data".uppercase(), color = Color.White, modifier = Modifier.padding(10.dp))
        }
        RestoreDialog(
            dialogState=dialogState,
            dialogTitle="Restore",
            dialogSubtitle = "Please select a csv file to restore",
            option1Text="Select File",
            onDismissRequest={ dialogState=!it },
            option1Action={
                restoreViewModel.selectFileFromStorage(
                    launcher =   launcher,
                    permissionLauncher =  permissionLauncher,
                    context =  context,
                    fileType = "*/*"
                    )
            }
        )
    }
}
