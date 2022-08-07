package com.example.passwordvaultapp_mvvm_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.passwordvaultapp_mvvm_compose.common.utils.BottomNavItem
import com.example.passwordvaultapp_mvvm_compose.common.utils.BottomNavigation
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.passwordvaultapp_mvvm_compose.common.utils.Screen
import com.example.passwordvaultapp_mvvm_compose.ui.theme.*

class LoggedInActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PasswordVaultApp_MVVM_ComposeTheme {
                val navController= rememberNavController()
                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(
                            items = listOf(
                            BottomNavItem(name = "Vault", route = Screen.PasswordListScreen.route, icon = Icons.Outlined.Lock, iconSelected =Icons.Default.Lock ),
                            BottomNavItem(name = "Tools", route = Screen.ToolsScreen.route, icon = Icons.Outlined.Build,iconSelected =Icons.Default.Build),
                            BottomNavItem(name = "Profile", route = Screen.ProfileScreen.route, icon = Icons.Outlined.Person,iconSelected =Icons.Default.Person),
                            BottomNavItem(name = "Settings", route = Screen.SettingsScreen.route, icon = Icons.Outlined.Settings,iconSelected =Icons.Default.Settings),
                            ),
                            navController =navController,
                            onItemClick ={
                                navController.navigate(it.route)
                            }
                        )
                    }
                ) {
                    BottomNavigation(navController = navController)
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    items:List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier=Modifier,
    onItemClick:(BottomNavItem)->Unit
){
    val backStackEntry=navController.currentBackStackEntryAsState()
  BottomNavigation(
      backgroundColor = textFieldColor,
      elevation = 5.dp
  ) {
      items.forEach {item ->
          val selected=item.route==backStackEntry.value?.destination?.route
          BottomNavigationItem(
              modifier = modifier.padding(5.dp),
              selected = selected,
              onClick = { onItemClick(item) },
              selectedContentColor = Purple500,
              unselectedContentColor = textColor,
              icon = {
                  Column(horizontalAlignment = CenterHorizontally) {
                      if(selected){
                          Icon(imageVector = item.iconSelected, contentDescription = item.name)
                      }else{
                          Icon(imageVector = item.icon, contentDescription = item.name)
                      }
                      if(selected) {
                          Text(
                              text = item.name,
                              textAlign = TextAlign.Center,
                              fontWeight = FontWeight.Bold
                              )
                      }else{
                          Text(
                              text = item.name,
                              textAlign = TextAlign.Center,
                              )
                      }
                  }
              }
          )
      }

  }
}

