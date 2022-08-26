package com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.passwordvaultapp_mvvm_compose.R
import com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.domain.models.VaultCategoryDisplay
import com.example.passwordvaultapp_mvvm_compose.ui.theme.appBgColor
import com.example.passwordvaultapp_mvvm_compose.ui.theme.textColor
import com.example.passwordvaultapp_mvvm_compose.ui.theme.textFieldColor

@Composable
fun CategoryListItemCompose(data: VaultCategoryDisplay){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 10.dp)
        .clip(shape = RoundedCornerShape(20.dp))
        .background(textFieldColor),
    horizontalArrangement = Arrangement.SpaceBetween) {
        Row() {
            Icon(
                painter = painterResource(id = R.drawable.category),
                contentDescription ="",modifier = Modifier
                .size(55.dp)
                .padding(top = 20.dp),
                tint = Color.White
            )
            Column() {
                Text(text = data.categoryName, modifier = Modifier.padding(top=20.dp, start = 20.dp), color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Text(text = data.categoryType, modifier = Modifier.padding(start=20.dp, bottom = 20.dp), color = textColor)
            }
        }
        Button(onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = appBgColor,
            ),
            modifier = Modifier
                .padding(top = 20.dp,end=20.dp)
                .clip(shape = RoundedCornerShape(20.dp))
            ,
            ) {
            when{
                data.isVisible->
                    Icon(
                        painter = painterResource(id = R.drawable.visible),
                        contentDescription ="",
                        modifier = Modifier,
                        tint = Color.White
                    )
                else->
                    Icon(
                        painter = painterResource(id = R.drawable.visibility_off),
                        contentDescription ="",
                        modifier = Modifier,
                        tint = Color.White
                    )
            }

        }
    }
}