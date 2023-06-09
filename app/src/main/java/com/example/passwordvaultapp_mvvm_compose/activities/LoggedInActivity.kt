package com.example.passwordvaultapp_mvvm_compose.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.passwordvaultapp_mvvm_compose.common.navigation.BottomNavigation
import com.example.passwordvaultapp_mvvm_compose.common.utils.BottomNavItem
import com.example.passwordvaultapp_mvvm_compose.common.utils.Screen
import com.example.passwordvaultapp_mvvm_compose.ui.theme.PasswordVaultApp_MVVM_ComposeTheme
import com.example.passwordvaultapp_mvvm_compose.ui.theme.Purple500
import com.example.passwordvaultapp_mvvm_compose.ui.theme.textColor
import com.example.passwordvaultapp_mvvm_compose.ui.theme.textFieldColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoggedInActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PasswordVaultApp_MVVM_ComposeTheme {
                val navController = rememberNavController()
                Scaffold(floatingActionButton = {
                    FloatingActionButton(
                        onClick = { /*TODO*/ },
                        elevation = FloatingActionButtonDefaults.elevation(
                            defaultElevation = 10.dp
                        )
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "")
                    }
                },
                    isFloatingActionButtonDocked = true,
                    floatingActionButtonPosition = FabPosition.Center,

                    bottomBar = {
                        BottomNavigationBar(items = listOf(
                            BottomNavItem(
                                name = "Vault",
                                route = Screen.PasswordListScreen.route,
                                icon = Icons.Outlined.Lock,
                                iconSelected = Icons.Default.Lock
                            ),
                            BottomNavItem(
                                name = "Tools",
                                route = Screen.ToolsScreen.route,
                                icon = Icons.Outlined.Build,
                                iconSelected = Icons.Default.Build
                            ),
                            BottomNavItem(
                                name = "Profile",
                                route = Screen.ProfileScreen.route,
                                icon = Icons.Outlined.Person,
                                iconSelected = Icons.Default.Person
                            ),
                            BottomNavItem(
                                name = "Settings",
                                route = Screen.SettingsScreen.route,
                                icon = Icons.Outlined.Settings,
                                iconSelected = Icons.Default.Settings
                            ),
                        ), navController = navController, onItemClick = {
                            navController.navigate(it.route)
                        })
                    }) {
                    BottomNavigation(
                        navController = navController, modifier = Modifier.padding(it)
                    )
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomAppBar(
        backgroundColor = textFieldColor,
        elevation = 5.dp,
        cutoutShape = CircleShape
    ) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(selected = selected,
                onClick = { onItemClick(item) },
                selectedContentColor = Purple500,
                unselectedContentColor = textColor,
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        if (selected) {
                            Icon(
                                imageVector = item.iconSelected,
                                contentDescription = item.name,
                                modifier = Modifier.size(25.dp)
                            )
                        } else {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.name,
                                modifier = Modifier.size(25.dp)
                            )
                        }
                        if (selected) {
                            Text(
                                text = item.name,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp
                            )
                        } else {
                            Text(
                                text = item.name,
                                textAlign = TextAlign.Center,
                                fontSize = 13.sp

                            )
                        }
                    }
                }
            )
        }
    }
}

