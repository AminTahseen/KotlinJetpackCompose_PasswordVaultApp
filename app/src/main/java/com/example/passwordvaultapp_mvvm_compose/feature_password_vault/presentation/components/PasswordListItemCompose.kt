package com.example.passwordvaultapp_mvvm_compose.feature_password_vault.presentation.components

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.passwordvaultapp_mvvm_compose.R
import com.example.passwordvaultapp_mvvm_compose.activities.VaultDetailsActivity
import com.example.passwordvaultapp_mvvm_compose.feature_password_vault.domain.models.PasswordData
import com.example.passwordvaultapp_mvvm_compose.feature_password_vault.presentation.viewmodels.VaultViewModel
import com.example.passwordvaultapp_mvvm_compose.ui.theme.textFieldColor

@Composable
fun PasswordListItemCompose(
    data: PasswordData,
    vaultViewModel: VaultViewModel = hiltViewModel()
){
    val context= LocalContext.current


    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 10.dp)
        .background(textFieldColor)
        .clickable {
            val vaultDetails= Intent(context, VaultDetailsActivity::class.java)
            vaultDetails.putExtra("vaultId",data.id)
            context.startActivity(vaultDetails)
        }
    ) {

        if (data.logoURL==null)
            Image(
                painter = painterResource(id = R.drawable.lock),
                contentDescription = null,
                modifier = Modifier
                    .size(55.dp)
                    .padding(10.dp),
                contentScale = ContentScale.Fit,
            )
        else
            Image(
                bitmap = data.logoURL.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier
                    .size(55.dp)
                    .padding(10.dp),
                contentScale = ContentScale.Fit,
            )
        Text(text = data.storeFor, modifier = Modifier.padding(20.dp), color = Color.White, fontWeight = FontWeight.Bold)
    }
}