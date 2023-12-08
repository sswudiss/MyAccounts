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
import com.example.myaccounts.account.AddAccountScreen
import com.example.myaccounts.transaction.Transactions
import com.example.myaccounts.transaction.add_transaction.AddEditTransaction
import com.example.myaccounts.transaction_details.EditTransaction


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
            route = AppRoute.ADD_TRANSACTION
        ) {
            AddEditTransaction(
                navHostController = navController,
//                transactionId = transactionId,
//                previousScreen = previousScreen
            )
        }

        composable(
            route = AppRoute.EDIT_TRANSACTION + "/{transactionId}",
            arguments = listOf(
                navArgument(
                    name = "transactionId"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                })
        ) {
            val transactionId = it.arguments?.getInt("transactionId") ?: -1

            EditTransaction(navController = navController, transactionId = transactionId)
        }

        composable(route = AppRoute.TRANSACTION_DETAILS) {
            Transactions(navController = navController)
        }

        // 賬號頁面
        composable(route = AppRoute.ACCOUNT_SCREEN) {
            AccountScreen()
        }

        // 賬號頁面
        composable(route = AppRoute.ADD_ACCOUNT) {
            AddAccountScreen()
        }
    }
}
