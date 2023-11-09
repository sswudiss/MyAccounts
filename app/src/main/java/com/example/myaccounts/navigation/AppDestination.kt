package com.example.myaccounts.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Money
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument

interface AppDestination {
    val icon: ImageVector
    val route: String
}

object Home : AppDestination {
    override val icon = Icons.Default.Home
    override val route = "Home"
}

object Add : AppDestination {
    override val icon = Icons.Default.Add
    override val route = "Add"
}

object Account : AppDestination {
    override val icon = Icons.Default.AccountBalanceWallet
    override val route = "Account"
}

object SingleAccount : AppDestination {
    override val icon = Icons.Filled.Money
    override val route = "single_account"
    const val accountTypeArg = "account_type"
    val routeWithArgs = "$route / {$accountTypeArg}"
    val argument = listOf(navArgument(accountTypeArg) { type = NavType.StringType })
}

val bottomBar = listOf(Home, Add, Account)