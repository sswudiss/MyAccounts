package com.example.myaccounts.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myaccounts.data.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 儀表板
 */
@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val userDataRepository: TransactionRepository
) : ViewModel() {
    private val _overviewCardState = mutableStateOf(OverviewCardState())
    val overviewCardState: State<OverviewCardState> = _overviewCardState

    private val _recentTransactions = mutableStateOf(TransactionsListState())
    val recentTransactions: State<TransactionsListState> = _recentTransactions

    init {
        viewModelScope.launch {
            userDataRepository.getAllTransactions().collect { transactions ->
                _overviewCardState.value = overviewCardState.value.copy(
                    totalBalance = transactions.filter { transaction -> transaction.transactionType == "Income" }
                        .sumOf { it.amount } - transactions.filter { transaction -> transaction.transactionType == "Expense" }
                        .sumOf { it.amount },
                    income = transactions.filter { it.transactionType == "Income" }.takeLast(2)
                        .sumOf { it.amount },
                    expense = transactions.filter { it.transactionType == "Expense" }.takeLast(2)
                        .sumOf { it.amount }
                )
                _recentTransactions.value = recentTransactions.value.copy(
                    list = transactions.takeLast(4).reversed()
                )
            }
        }
    }
}