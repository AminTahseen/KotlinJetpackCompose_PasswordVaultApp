package com.example.passwordvaultapp_mvvm_compose.feature_password_vault.presentation.screens

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.passwordvaultapp_mvvm_compose.R
import com.example.passwordvaultapp_mvvm_compose.acitivites.SettingsActivity
import com.example.passwordvaultapp_mvvm_compose.common.utils.Screen
import com.example.passwordvaultapp_mvvm_compose.ui.theme.appBgColor
import com.example.passwordvaultapp_mvvm_compose.ui.theme.textColor
import com.example.passwordvaultapp_mvvm_compose.ui.theme.textFieldColor

@Composable
fun SettingsScreen(navController: NavController){
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val context = LocalContext.current

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
                text = "Settings",
                color = Color.White,
                modifier = Modifier.padding(end=10.dp, start = 10.dp, top = 30.dp),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Column(modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Card(
                    elevation = 10.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end=20.dp, start = 20.dp, bottom=20.dp)
                        .clip(shape = RoundedCornerShape(20.dp))
                        .clickable{
                            val backup=Intent(context, SettingsActivity::class.java)
                            backup.putExtra("screen",Screen.BackupScreen.route)
                            context.startActivity(backup)
                        },
                    backgroundColor = textFieldColor
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Spacer(modifier = Modifier.height(20.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.backup),
                            tint = textColor,
                            contentDescription = "TOOLS 1",
                            modifier = Modifier
                            .size(50.dp)
                        )
                        Text(text = "Create Backup",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = textColor
                        )
                    }
                }
                Card(
                    elevation = 10.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end=20.dp, start = 20.dp)
                        .clip(shape = RoundedCornerShape(20.dp))

                        .clickable{
                            val restore=Intent(context, SettingsActivity::class.java)
                            restore.putExtra("screen",Screen.RestoreScreen.route)
                            context.startActivity(restore)
                        },
                    backgroundColor = textFieldColor
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Spacer(modifier = Modifier.height(20.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.backup_restore),
                            tint = textColor,
                            contentDescription = "TOOLS 1",
                            modifier = Modifier
                            .size(50.dp)
                        )
                        Text(text = "Restore Backup",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = textColor
                        )
                    }
                }
            }

        }
    }
}