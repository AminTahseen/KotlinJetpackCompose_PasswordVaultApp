package com.example.passwordvaultapp_mvvm_compose.feature_password_vault.presentation.screens

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.passwordvaultapp_mvvm_compose.R
import com.example.passwordvaultapp_mvvm_compose.common.utils.Screen
import com.example.passwordvaultapp_mvvm_compose.feature_password_vault.presentation.components.VaultCategoryList
import com.example.passwordvaultapp_mvvm_compose.ui.theme.*
import kotlinx.coroutines.CoroutineScope

@Composable
fun AddPasswordVaultScreen(navController: NavController,
){
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    var vaultName by remember { mutableStateOf(TextFieldValue("")) }
    var vaultPass by remember { mutableStateOf(TextFieldValue("")) }
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    var selectedIndex by remember { mutableStateOf(0) }

    var imageURI by remember { mutableStateOf<Uri?>(null)}
    val context= LocalContext.current
    val bitmap= remember {mutableStateOf<Bitmap?>(null)}
    val launcher= rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()){uri:Uri? ->
        imageURI=uri
        Log.d("fileURI",uri.toString())
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // Permission Accepted: Do something
            launcher.launch("image/*")
        } else {
            // Permission Denied: Do something
        }
    }
    fun onCategorySelected(
        categoryName:String,
        categoryId: Int?,
        index:Int)
    {
        selectedIndex=index
        Log.d("selected",categoryName)
    }
    Scaffold(scaffoldState = scaffoldState) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(appBgColor)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .fillMaxHeight(),
        ) {
            Text(
                text = "New Vault",
                color = Color.White,
                modifier = Modifier.padding(end = 10.dp, start = 10.dp, top = 30.dp),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Vault Name",
                color = textColor,
                modifier = Modifier.padding(end = 10.dp, start = 10.dp, top = 30.dp),
                fontSize = 20.sp,
            )
            TextField(
                value = vaultName,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, end = 10.dp, start = 10.dp)
                    .background(textFieldColor),
                onValueChange = {
                    vaultName = it
                },
                textStyle = TextStyle(
                    fontSize = 20.sp,
                    color = Color.White
                ),
                singleLine = true,
                placeholder = {
                    Text(
                        "Please specify vault name",
                        color = textColor,
                        fontSize = 17.sp,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }
            )
            Text(
                text = "Vault Password Value",
                color = textColor,
                modifier = Modifier.padding(end = 10.dp, start = 10.dp, top = 30.dp),
                fontSize = 20.sp,
            )
            TextField(
                value = vaultPass,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, end = 10.dp, start = 10.dp)
                    .background(textFieldColor),
                onValueChange = {
                    vaultPass = it
                },
                textStyle = TextStyle(
                    fontSize = 20.sp,
                    color = Color.White
                ),
                singleLine = true,
                placeholder = {
                    Text(
                        "Please specify password to store",
                        color = textColor,
                        fontSize = 17.sp,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                },
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.visible),
                        contentDescription = "", tint = textColor,
                        modifier = Modifier.clickable {

                        }
                    )
                }
            )
            Button(onClick = {
                navController.navigate(Screen.GenerateVaultPassScreen.route)
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, top = 5.dp),
                border = BorderStroke(3.dp, Purple500),
                colors = ButtonDefaults.buttonColors(backgroundColor = appBgColor)

            ) {
                Text(text = "Generate".uppercase(), color = Color.White, modifier = Modifier.padding(10.dp))
            }
            Text(
                text = "Vault Category",
                color = textColor,
                modifier = Modifier.padding(end = 10.dp, start = 10.dp, top = 30.dp),
                fontSize = 20.sp,
            )
            VaultCategoryList(selectedIndex, onCategorySelected = ::onCategorySelected)
            VaultImagePicker(launcher,permissionLauncher,context,imageURI)
            Spacer(modifier = Modifier.height(30.dp))
            Button(onClick = {
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)) {
                Text(text = "Create".uppercase(), color = Color.White, modifier = Modifier.padding(10.dp))
            }
            Spacer(modifier = Modifier.height(20.dp))

        }

    }
}
@Composable
fun VaultImagePicker(
    launcher: ManagedActivityResultLauncher<String, Uri>,
    permissionlauncher: ManagedActivityResultLauncher<String, Boolean>,
    context: Context,
    imageURI: Uri?
) {
    Text(
        text = "Vault For Image",
        color = textColor,
        modifier = Modifier.padding(end = 10.dp, start = 10.dp, top = 30.dp),
        fontSize = 20.sp,
    )
    Button(onClick = {
        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) -> {
                // Some works that require permission
                Log.d("ExampleScreen","Code requires permission")
                launcher.launch("image/*")

            }
            else -> {
                // Asking for permission
                permissionlauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }
    }, modifier = Modifier
        .fillMaxWidth()
        .padding(start = 10.dp, end = 10.dp, top = 10.dp),
        border = BorderStroke(3.dp, Purple500),
        colors = ButtonDefaults.buttonColors(backgroundColor = appBgColor)

    ) {
        when{
            imageURI!=null->
                Text(text = "Change Logo".uppercase(), color = Color.White, modifier = Modifier.padding(10.dp))
            else->
                Text(text = "Choose Logo".uppercase(), color = Color.White, modifier = Modifier.padding(10.dp))
        }
    }
}