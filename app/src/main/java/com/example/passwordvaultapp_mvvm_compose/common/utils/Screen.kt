package com.example.passwordvaultapp_mvvm_compose.common.utils

sealed class Screen(val route:String){
    object MainScreen:Screen("main_screen")
    object PasscodeScreen:Screen("Passcode_screen")

    // Bottom navigation
    object PasswordListScreen:Screen("password_list_screen")
    object ProfileScreen:Screen("profile_screen")
    object SettingsScreen:Screen("settings_screen")
    object ToolsScreen:Screen("tools_screen")
}
