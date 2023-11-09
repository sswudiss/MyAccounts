package com.example.myaccounts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.compose.MyAccountsTheme
import com.example.myaccounts.navigation.AppHost
import com.example.myaccounts.navigation.BottomBarTab
import com.example.myaccounts.navigation.Home
import com.example.myaccounts.navigation.bottomBar
import com.example.myaccounts.navigation.navigateSingleTopTo
import com.example.myaccounts.search.SearchBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAccountsApp()
        }
    }
}


@Composable
fun MyAccountsApp() {
    MyAccountsTheme {
        //如要讓程式碼可測試及重複使用，建議不要將整個navController直接傳遞至可組合項
        val navController = rememberNavController()
        //導航控制器當前返回堆棧條目作為狀態,然後擷取目前的destination
        val currentBackStack by navController.currentBackStackEntryAsState()
        //獲取您當前的目的地
        //須決定「目前」顯示的內容，才能將這項資訊傳遞至RallyTabRow
        val currentDestination = currentBackStack?.destination
        //將變量更改為此並使用概述作為備份屏幕（如果返回 null）
        //如要更新currentScreen
        //您必須疊代rallyTabRowScreens清單才能找出相符的路徑，
        //然後傳回對應的RallyDestination
        val currentScreen =
            bottomBar.find { it.route == currentDestination?.route } ?: Home

        Surface(tonalElevation = 5.dp) {
            Scaffold(
                topBar = { SearchBar() },
                bottomBar = {
                    BottomBarTab(
                        allScreens = bottomBar,
                        onTabSelected = { newScreen -> navController.navigateSingleTopTo(newScreen.route) },
                        currentScreen = currentScreen
                    )
                }
            ) {
                AppHost(navController = navController, modifier = Modifier.padding(it))
            }
        }
    }
}

