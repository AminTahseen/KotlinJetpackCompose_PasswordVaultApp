package com.example.passwordvaultapp_mvvm_compose.common.utils

sealed class Screen(val route:String){
    // Auth navigation
    object MainScreen:Screen("main_screen")
    object PasscodeScreen:Screen("Passcode_screen")

    // Bottom navigation
    object PasswordListScreen:Screen("password_list_screen")
    object ProfileScreen:Screen("profile_screen")
    object SettingsScreen:Screen("settings_screen")
    object ToolsScreen:Screen("tools_screen")

    // Vault categories navigation
    object VaultCategoriesScreen:Screen("vault_categories")
    object AddNewVaultCategoryScreen:Screen("add_vault_category")

}
