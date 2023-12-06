package com.example.myaccounts.transaction.income


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.myaccounts.navigation.AppRoute.TRANSACTION_DETAILS
import com.example.myaccounts.transaction.TransactionCard
import com.example.myaccounts.transaction.TransactionsEvent
import com.example.myaccounts.transaction.TransactionsViewModel

/**
 * 收入屏幕
 */
@Composable
fun IncomeScreen(
    navController:NavHostController,
    viewModel: TransactionsViewModel = hiltViewModel(),
    transactionType:String
) {
    val transactionList by viewModel.transactions

    viewModel.onEvent(
        TransactionsEvent.IncomeOrExpense(
            transactionType
        )
    )

    if (transactionList.list.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "最近沒有交易.."
            )
        }
    } else {
        LazyColumn(
            contentPadding = PaddingValues(
                8.dp, 0.dp, 8.dp, 64.dp
            ),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(transactionList.list) {
                TransactionCard(transaction = it) {
                    navController.navigate(TRANSACTION_DETAILS+ { it.id.toString() })
                }
            }
        }
    }
}