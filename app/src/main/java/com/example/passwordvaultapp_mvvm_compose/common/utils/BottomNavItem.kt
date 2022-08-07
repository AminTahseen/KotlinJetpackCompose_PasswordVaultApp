package com.example.passwordvaultapp_mvvm_compose.common.utils

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val name:String,
    val route:String,
    val icon:ImageVector,
    val iconSelected:ImageVector
)
