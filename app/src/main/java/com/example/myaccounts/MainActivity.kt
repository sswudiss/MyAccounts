package com.example.myaccounts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
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
import com.example.myaccounts.navigation.AppNavHost
import com.example.myaccounts.navigation.AppNavigationActions
import com.example.myaccounts.navigation.AppRoute
import com.example.myaccounts.navigation.FabM3
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
        navBackStackEntry?.destination?.route ?: AppRoute.OVERVIEW


    // 動畫狀態設置
    when (navBackStackEntry?.destination?.route) {

        "ADD_ACCOUNT" -> {
            bottomBarState.value = false
        }

        "ACCOUNT_TRANSFER" -> {
            bottomBarState.value = false
        }
        else -> {
            bottomBarState.value = true
            topBarState.value = true
            fabState.value = true
        }
    }

    Scaffold(
        floatingActionButton = {
            FabM3(
                navBackStackEntry = navBackStackEntry,
                sheetState = sheetState,
                scope = scope,
                showBottomSheet = showBottomSheet,
                fabState = fabState,
                navigationToTopLevelDestination = navigationActions::navigateTo
            )
        },

        bottomBar = {
            AppBottomNavigationBarM3(
                selectedDestination = selectedDestination,
                navigationToTopLevelDestination = navigationActions::navigateTo,
                bottomBarState = bottomBarState
            )
        },
    ) {
        AppNavHost(navController = navController, modifier = Modifier.padding(it))
    }
}

