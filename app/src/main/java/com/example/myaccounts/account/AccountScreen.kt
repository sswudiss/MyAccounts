package com.example.myaccounts.account

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


/**
 * 建立一個帳戶- Create an account
 */

/**
 * 編輯帳戶- Edit an account
 */

/**
 * 調整帳戶餘額- Adjust account's balance
 */

/**
 * 更改帳戶貨幣- Change account's currency
 */

/**
 * 刪除帳戶- Delete account
 */

/**
 * 重新排序帳戶- Reorder accounts
 */

/**
 * 賬號屏幕
 */
@Composable
fun AccountScreen() {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Account Screen 賬號屏幕")
    }
}