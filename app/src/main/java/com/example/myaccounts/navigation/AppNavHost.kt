package com.example.myaccounts.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myaccounts.home.HomeScreen
import com.example.myaccounts.account.AccountScreen
import com.example.myaccounts.account.AccountTransferScreen
import com.example.myaccounts.account.AddAccountScreen
import com.example.myaccounts.account.PayForScreen
import com.example.myaccounts.account.IncomeScreen
import com.example.myaccounts.account.PlanAmountScreen

@Composable
fun AppHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = AppRoute.HOME
    ) {
        // 主頁
        composable(route = AppRoute.HOME) {
            HomeScreen()
        }
       // 賬號頁面
        composable(route = AppRoute.ACCOUNT) {
            AccountScreen()
        }

        // 計劃金額頁面
        composable(route = AppRoute.PLAN_AMOUNT) {
            PlanAmountScreen()
        }

        // 賬號轉移頁面
        composable(route = AppRoute.ACCOUNT_TRANSFER) {
            AccountTransferScreen()
        }

        // 收入頁面
        composable(route = AppRoute.INCOME) {
            IncomeScreen()
        }
       // 支出頁面
        composable(route = AppRoute.PAY_FOR) {
            PayForScreen()
        }
       // 新增賬號頁面
        composable(route = AppRoute.ADD_ACCOUNT) {
            AddAccountScreen()
        }
    }
}

/**
 * 返回時回到主頁以及保存捲動位置
 */
fun NavHostController.restoreStateOnReturn(route: String) = this.navigate(route) {
    // 在導航之前彈出到給定的目的地。這將從返回堆疊中彈出所有不匹配的目標，直到找到該目標
    popUpTo(this@restoreStateOnReturn.graph.findStartDestination().id) { saveState = true }
    launchSingleTop = true  //作為單頂啟動（即，返回堆疊頂部最多有一個給定目的地的副本

    // 恢復先前由PopUpToBuilder.saveState或popUpToSaveState屬性儲存的任何狀態。
    // 如果先前沒有儲存導覽到的目標 ID 的狀態，則此操作無效
    restoreState = true
}
