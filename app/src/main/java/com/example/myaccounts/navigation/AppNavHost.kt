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
import com.example.myaccounts.account.AddAccount
import com.example.myaccounts.transaction.payfor.PayForScreen
import com.example.myaccounts.transaction.income.IncomeScreen
import com.example.myaccounts.account.PlanAmountScreen
import com.example.myaccounts.transaction.income.AddIncome
import com.example.myaccounts.transaction.payfor.AddPayFor

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = AppRoute.OVERVIEW
    ) {
        // 主頁
        composable(route = AppRoute.OVERVIEW) {
            HomeScreen()
        }

        // 收入頁面
        composable(route = AppRoute.INCOME_SCREEN) {
            IncomeScreen()
        }
        // 支出頁面
        composable(route = AppRoute.PAY_FOR_SCREEN) {
            PayForScreen()
        }


        // 計劃金額頁面
        composable(route = AppRoute.PLAN_AMOUNT) {
            PlanAmountScreen()
        }

        // 賬號轉移頁面
        composable(route = AppRoute.ACCOUNT_TRANSFER) {
            AccountTransferScreen()
        }

        // 新增收入
        composable(route = AppRoute.ADD_INCOME) {
            AddIncome()
        }

        // 新增支出
        composable(route = AppRoute.ADD_PAY_FOR) {
            AddPayFor()
        }

        // 新增賬號
        composable(route = AppRoute.ADD_ACCOUNT) {
            AddAccount()
        }

        // 賬號頁面
        composable(route = AppRoute.ACCOUNT_SCREEN) {
            AccountScreen()
        }

    }
}
