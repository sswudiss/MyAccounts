package com.example.myaccounts.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.compose.MyAccountsTheme
import com.example.myaccounts.home.HomeScreen
/*

@Composable
fun MyAppLandscape() {
    val navController = rememberNavController()

    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination
    val bottomBarCurrentScreen =
        bottomBarItems.find { it.route == currentDestination?.route } ?: Home  //使用接口，密封類等崩潰

    Row {
        AppNavigationRail(
            bottomBarItems = bottomBarItems,
            onClick = { newScreen ->
                navController.restoreStateOnReturn(newScreen.route)
            },
            bottomBarCurrentScreen = bottomBarCurrentScreen
        )
        HomeScreen()
    }
}


@Composable
fun AppNavigationRail(
    bottomBarItems: List<AppDestination>,
    onClick: (AppDestination) -> Unit,
    bottomBarCurrentScreen: AppDestination,
    modifier: Modifier = Modifier
) {
    NavigationRail(
        modifier = modifier.padding(end = 8.dp),
    ) {
        Column(
            modifier = modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            bottomBarItems.forEach {
                NavigationRailItem(
                    icon = { Icon(imageVector = it.icon, contentDescription = null) },
                    label = { Text(text = it.route) },
                    selected = bottomBarCurrentScreen == it, //selected: Boolean
                    onClick = { onClick(it) }
                )
                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }
}
*/
