package com.example.passwordvaultapp_mvvm_compose.feature_backup_restore.presentation.screens

import android.content.Context
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import com.example.passwordvaultapp_mvvm_compose.feature_backup_restore.presentation.viewmodels.BackupViewModel
import com.example.passwordvaultapp_mvvm_compose.ui.theme.appBgColor
import com.example.passwordvaultapp_mvvm_compose.ui.theme.textColor
import com.example.passwordvaultapp_mvvm_compose.ui.theme.textFieldColor

@Composable
fun BackupScreen(
    backupViewModel:BackupViewModel = hiltViewModel()
){
    val context= LocalContext.current
    val animatedProgress= animateIntAsState(
        targetValue =backupViewModel.progress.value,
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
                painter = painterResource(id = R.drawable.backup),
                contentDescription = "Icon",
                modifier = Modifier
                    .padding(10.dp)
                    .size(150.dp),
                tint = Color.White,

                )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Backup Records", fontSize = 30.sp,
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
            Text(text = backupViewModel.message.value, color = textColor)
        }
        when(backupViewModel.buttonState.value){
            true->
                CreateBackupButton(
                    context,
                    backupViewModel
                )
            else->
                Button(
                    enabled = false,
                    onClick ={
                    },
                    colors = ButtonDefaults.buttonColors(
                        disabledBackgroundColor = textFieldColor,
                        disabledContentColor = textColor
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)) {
                    Text(text = "Working...".uppercase(), color = Color.White, modifier = Modifier.padding(10.dp))
                }
        }

    }
}
@Composable
fun CreateBackupButton(context: Context, backupViewModel: BackupViewModel
) {
    Button(
        onClick ={
            backupViewModel.backupData(context = context)
//            when (PackageManager.PERMISSION_GRANTED) {
//                ContextCompat.checkSelfPermission(
//                    context,
//                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                ) -> {
//                    // Some works that require permission
//
//                }
//                else -> {
//                    // Asking for permission
//                    permissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                }
//            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)) {
        Text(text = "Create Backup".uppercase(), color = Color.White, modifier = Modifier.padding(10.dp))
    }
}