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
        navController = navController,
        startDestination = Home.route,
        modifier = modifier
    ) {
        // 主頁
        composable(route = Home.route) {
            HomeScreen()
        }
       // 賬號頁面
        composable(route = Account.route) {
            AccountScreen()
        }

        // 計劃金額頁面
        composable(route = PlanAmount.route) {
            PlanAmountScreen()
        }

        // 賬號轉移頁面
        composable(route = AccountTransfer.route) {
            AccountTransferScreen()
        }

        // 收入頁面
        composable(route = Income.route) {
            IncomeScreen()
        }
       // 支出頁面
        composable(route = PayFor.route) {
            PayForScreen()
        }
       // 新增賬號頁面
        composable(route = AddAccount.route) {
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


/**
 * 導航到單獨賬號
 */
fun NavHostController.navigateToSingleAccount(accountType: String) {
    this.restoreStateOnReturn("${SingleAccount.route} / $accountType")
}
