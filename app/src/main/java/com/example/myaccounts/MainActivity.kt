package com.example.myaccounts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.compose.MyAccountsTheme
import com.example.myaccounts.navigation.AppBottomNavigationBarM3
import com.example.myaccounts.navigation.AppHost
import com.example.myaccounts.navigation.AppNavigationActions
import com.example.myaccounts.navigation.AppRoute

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAccountsTheme {
                Surface {
                    //
                    val windowSizeClass = calculateWindowSizeClass(this)
                    MyApp(windowSizeClass)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp(windowSize: WindowSizeClass) {
    MyAccountsTheme {
        val navController = rememberNavController()
        val navigationActions = remember(navController) {
            AppNavigationActions(navController)
        }

        val bottomBarState = rememberSaveable { mutableStateOf(true) }
        val topBarState = rememberSaveable { (mutableStateOf(true)) }
        val fabState = rememberSaveable { mutableStateOf(true) }

        //添加skipPartiallyExpanded = true 后旋轉屏幕不會崩潰且位置一直
        val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        val scope = rememberCoroutineScope()
        val showBottomSheet = rememberSaveable { mutableStateOf(false) }

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val selectedDestination =
            navBackStackEntry?.destination?.route ?: AppRoute.HOME

        /*    val currentDestination = navBackStackEntry?.destination
            val bottomBarCurrentScreen =
                bottomBarItems.find { it.route == currentDestination?.route } ?: Home  //使用接口，密封類等崩潰*/


        // 動畫狀態設置
        when (navBackStackEntry?.destination?.route) {
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

        //
        when (windowSize.widthSizeClass) {
            WindowWidthSizeClass.Compact -> {
                androidx.compose.material.Scaffold(
             /*       floatingActionButton = {
                        IncomePayFor(
                            currentBackStack = navBackStackEntry,
                            sheetState = sheetState,
                            scope = scope,
                            showBottomSheet = showBottomSheet,
                            onClick = { newScreen ->
                                navController.restoreStateOnReturn(
                                    newScreen.route
                                )
                            },
                            fabState = fabState
                        )
                    },*/

                    isFloatingActionButtonDocked = true,
                    floatingActionButtonPosition = androidx.compose.material.FabPosition.Center,

                    bottomBar = {
                        AppBottomNavigationBarM3(
                            selectedDestination = selectedDestination,
                            navigationToTopLevelDestination = navigationActions::navigateTo,
                            bottomBarState = bottomBarState
                        )
                    },
                ) {
                    AppHost(navController = navController, modifier = Modifier.padding(it))
                }
            }

//            WindowWidthSizeClass.Expanded -> {

        }
    }
}
