package com.example.myaccounts.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.myaccounts.R

object AppRoute {
    const val HOME = "Home"
    const val ACCOUNT = "Account"
    const val PLAN_AMOUNT = "PlanAmount"  // 計劃金額
    const val ACCOUNT_TRANSFER = "AccountTransfer"   // 賬號轉移
    const val INCOME = "Income"   // 收入
    const val PAY_FOR = "PayFor"  // 支出
    const val ADD_ACCOUNT = "AddAccount"   // 新增賬號

}

data class AppDestination(
    val route: String,
    val selectedIcon: ImageVector,
    val iconTextId: Int
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

//頂級目的地
val TOP_LEVEL_DESTINATIONS = listOf(
    AppDestination(
        route = AppRoute.HOME,
        selectedIcon = Icons.Default.Home,
        iconTextId = R.string.home
    ),
    AppDestination(
        route = AppRoute.ACCOUNT,
        selectedIcon = Icons.Default.Wallet,
        iconTextId = R.string.account
    )

)
