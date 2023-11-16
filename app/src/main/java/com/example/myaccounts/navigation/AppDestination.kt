package com.example.myaccounts.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreTime
import androidx.compose.material.icons.filled.Transform
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument

interface AppDestination {
    val icon: ImageVector
    val route: String
}

object Home : AppDestination {
    override val icon = Icons.Filled.Home
    override val route = "Home"
}

object Account : AppDestination {
    override val icon = Icons.Filled.Wallet
    override val route = "Account"
}

/**
 * 單獨賬號
 */
object SingleAccount : AppDestination {
    override val icon = Icons.Filled.AccountBalance
    override val route = "single_account"
    const val accountTypeArg = "account_type"
    val routeWithArgs = "$route / {$accountTypeArg}"
    val argument = listOf(navArgument(accountTypeArg) { type = NavType.StringType })
}


/**
 * 計劃金額
 */
object PlanAmount : AppDestination {
    override val icon = Icons.Filled.MoreTime
    override val route = "PlanAmount"
}

/**
 * 賬戶轉移
 */
object AccountTransfer : AppDestination {
    override val icon = Icons.Filled.Transform
    override val route = "AccountTransfer"
}

/**
 * 收入
 */
object Income : AppDestination {
    override val icon = Icons.Filled.Download
    override val route = "Income"
}

/**
 * 支出
 */
object PayFor : AppDestination {
    override val icon = Icons.Filled.Upload
    override val route = "PayFor"
}


/**
 * 增加賬號
 */
object AddAccount : AppDestination {
    override val icon = Icons.Filled.AccountBalanceWallet
    override val route = "AddAccount"
}

// 底部導航欄要顯示的内容
val bottomBarItems = listOf(Home, Account)