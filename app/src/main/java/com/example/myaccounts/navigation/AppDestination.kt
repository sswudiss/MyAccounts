package com.example.myaccounts.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreTime
import androidx.compose.material.icons.filled.Transform
import androidx.compose.material.icons.filled.Upload
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.myaccounts.R

object AppRoute {
    const val OVERVIEW = "OVERVIEW"
    const val INCOME_SCREEN = "INCOME_SCREEN"     //收入屏幕
    const val PAY_FOR_SCREEN = "PAY_FOR_SCREEN"   //支出屏幕
    const val ACCOUNT_SCREEN = "ACCOUNT_SCREEN"   //賬號屏幕
    const val PLAN_AMOUNT = "PLAN_AMOUNT"         //計劃金額
    const val ACCOUNT_TRANSFER = "ACCOUNT_TRANSFER"   //賬號轉移
    const val ADD_INCOME = "ADD_INCOME"     //新增收入
    const val ADD_PAY_FOR = "ADD_PAY_FOR"   //新增支付
    const val ADD_ACCOUNT = "ADD_ACCOUNT"   //新增賬號

}

data class AppDestination(
    val route: String,
    val selectedIcon: ImageVector,
    val iconTextId: Int
)

//頂級目的地
val TOP_LEVEL_DESTINATIONS = listOf(
    AppDestination(
        route = AppRoute.OVERVIEW,
        selectedIcon = Icons.Default.Home,
        iconTextId = R.string.overview
    ),
    AppDestination(
        route = AppRoute.INCOME_SCREEN,
        selectedIcon = Icons.Default.Download,
        iconTextId = R.string.income
    ),
    AppDestination(
        route = AppRoute.PAY_FOR_SCREEN,
        selectedIcon = Icons.Default.Upload,
        iconTextId = R.string.pay_for
    )
)


// 計劃金額
val PLAN_AMOUNT = AppDestination(
    route = AppRoute.PLAN_AMOUNT,
    selectedIcon = Icons.Default.MoreTime,
    iconTextId = R.string.plan_amount
)

// 賬戶轉移
val ACCOUNT_TRANSFER = AppDestination(
    route = AppRoute.ACCOUNT_TRANSFER,
    selectedIcon = Icons.Default.Transform,
    iconTextId = R.string.account_transfer
)

// 收入
val ADD_INCOME = AppDestination(
    route = AppRoute.ADD_INCOME,
    selectedIcon = Icons.Default.Download,
    iconTextId = R.string.income
)

// 支出
val ADD_PAY_FOR = AppDestination(
    route = AppRoute.ADD_PAY_FOR,
    selectedIcon = Icons.Default.Upload,
    iconTextId = R.string.plan_amount
)

// 新增賬號
val ADD_ACCOUNT = AppDestination(
    route = AppRoute.ADD_ACCOUNT,
    selectedIcon = Icons.Default.AccountBalanceWallet,
    iconTextId = R.string.add_account
)


/**
 * 導航操作
 */
class AppNavigationActions(private val navController: NavHostController) {
    fun navigateTo(destination: AppDestination) {
        navController.navigate(destination.route) {
            // 避免當使用者選擇項目時在返回堆疊上建立一大堆目的地
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when reselecting the same item
            // 避免同一目的地的多個副本
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            // 重新選擇先前選擇的項目時恢復狀態
            restoreState = true
        }
    }
}

