package com.example.myaccounts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.compose.MyAccountsTheme
import com.example.myaccounts.navigation.IncomePayFor
import com.example.myaccounts.navigation.AppBottomBarM2
import com.example.myaccounts.navigation.AppBottomBarM3
import com.example.myaccounts.navigation.AppHost
import com.example.myaccounts.navigation.Home
import com.example.myaccounts.navigation.bottomBarItems
import com.example.myaccounts.navigation.restoreStateOnReturn

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAccountsTheme {
                Surface {
                    MyApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {
    MyAccountsTheme {
        val navController = rememberNavController()
        val bottomBarState = rememberSaveable { mutableStateOf(true) }
        val topBarState = rememberSaveable { (mutableStateOf(true)) }

        val fabState = rememberSaveable { mutableStateOf(true) }

        //添加skipPartiallyExpanded = true 后旋轉屏幕不會崩潰且位置一直
        val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        val scope = rememberCoroutineScope()
        val showBottomSheet = rememberSaveable { mutableStateOf(false) }

        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStack?.destination
        val bottomBarCurrentScreen =
            bottomBarItems.find { it.route == currentDestination?.route } ?: Home  //使用接口，密封類等崩潰

        // 動畫狀態設置
        when (currentBackStack?.destination?.route) {
            "PlanAmount" -> {
                bottomBarState.value = false
                topBarState.value = true
                fabState.value = false
            }

            "AccountTransfer" -> {
                bottomBarState.value = false
                topBarState.value = true
                fabState.value = false
            }

            "Income" -> {
                bottomBarState.value = false
                topBarState.value = true
                fabState.value = false
            }

            "PayFor" -> {
                bottomBarState.value = false
                topBarState.value = true
                fabState.value = false
            }

            "AddAccount" -> {
                bottomBarState.value = false
                topBarState.value = true
                fabState.value = false
            }
            else -> {
                bottomBarState.value = true
                topBarState.value = true
                fabState.value = true
            }
        }

        Surface(tonalElevation = 5.dp) {
            androidx.compose.material.Scaffold(
                floatingActionButton = {
                    IncomePayFor(
                        currentBackStack = currentBackStack,
                        sheetState = sheetState,
                        scope = scope,
                        showBottomSheet = showBottomSheet,
                        onClick = { newScreen -> navController.restoreStateOnReturn(newScreen.route) },
                        fabState = fabState
                    )
                },

                isFloatingActionButtonDocked = true,
                floatingActionButtonPosition = androidx.compose.material.FabPosition.Center,

                bottomBar = {
                    AppBottomBarM3(
                        bottomBarItems = bottomBarItems,
                        onClick = { newScreen -> navController.restoreStateOnReturn(newScreen.route) },
                        bottomBarCurrentScreen = bottomBarCurrentScreen,
                        bottomBarState = bottomBarState
                    )
                },
            ) {
                AppHost(navController = navController, modifier = Modifier.padding(it))
            }
        }
    }
}

@Preview
@Composable
private fun MyAccountsAppPrev() {
    MyAccountsTheme {
        Surface(tonalElevation = 5.dp) {
            MyApp()
        }
    }
}