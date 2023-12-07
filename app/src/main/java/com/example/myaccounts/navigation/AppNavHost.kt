package com.example.myaccounts.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.myaccounts.home.HomeScreen
import com.example.myaccounts.account.AccountScreen
import com.example.myaccounts.account.AccountTransferScreen
import com.example.myaccounts.account.AddAccount
import com.example.myaccounts.transaction.Transaction
import com.example.myaccounts.transaction.add_transaction.AddEditTransaction
import com.example.myaccounts.transaction.payfor.PayForScreen
import com.example.myaccounts.transaction.income.IncomeScreen
import com.example.myaccounts.transaction.planned.PlanAmountScreen
import com.example.myaccounts.transaction_details.TransactionDetails


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

        // 交易頁面
        composable(
            route = Screen.AddEditTransaction.route + "/{id}/{previousScreen}",
            arguments = listOf(
                navArgument(
                    name = "id"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                },
                navArgument(
                    name = "previousScreen"
                ) {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            val transactionId = it.arguments?.getInt("transactionId") ?: -1
            val previousScreen = it.arguments?.getString("previousScreen") ?: ""

            AddEditTransaction(
                navHostController = navController,
                transactionId = transactionId,
                previousScreen = previousScreen
            )
        }

        composable(route = AppRoute.TRANSACTION_SCREEN) {
            Transaction(navController = navController)
        }

        // 賬號頁面
        composable(route = AppRoute.ACCOUNT_SCREEN) {
            AccountScreen()
        }

//        -------------------------------------------------------------------
        /*
                // 收入頁面
                composable(route = AppRoute.INCOME_SCREEN) {

                }
                // 支出頁面
                composable(route = AppRoute.PAY_FOR_SCREEN) {

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

                }

                // 新增支出
                composable(route = AppRoute.ADD_PAY_FOR) {
        //            AddPayFor()
                }


                // 新增賬號
                composable(route = AppRoute.ADD_ACCOUNT) {
                    AddAccount()
                }

                // 單獨賬號詳細資訊畫面
                composable(
                    route = AppRoute.TRANSACTION_SCREEN + "/{transactionId}",
                    arguments = listOf(navArgument("transactionId") {
                        type = NavType.IntArrayType
                        defaultValue = -1
                    })
                ) {
                    val transactionId = it.arguments?.getInt("transactionId") ?: -1
                    TransactionDetails(navController = navController, transactionId = transactionId)
                }*/
    }
}
