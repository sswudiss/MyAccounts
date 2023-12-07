package com.example.myaccounts.transaction

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myaccounts.data.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val userDataRepository: TransactionRepository
) : ViewModel() {

    private val _transactions = mutableStateOf(TransactionsState())
    val transactions: State<TransactionsState> = _transactions

    private val _transactionType = mutableStateOf(
        TransactionsDropDownMenuState(
            selectedOption = "All"
        )
    )
    val transactionType: State<TransactionsDropDownMenuState> = _transactionType

    /**
     * 這段程式碼首先使用 userDataRepository.getAllTransactions( )  方法建立一個 Flow。
     * 這個方法會回傳一個會在時間上非同步地發出交易清單的 Flow。
     * 然後，使用 viewModelScope.launch( )  方法收集這個 Flow。
     * 這個方法會建立一個新的協程，它會收集這個 Flow 並處理資料。
     * collect() 方法用來遍歷這個 Flow 並處理每個項目。在這個例子中，項目是一個交易清單。
     * 這個清單會用來使用 _transactions.value 屬性更新 UI。
     * _transactions.value 屬性是一個 LiveData 物件。LiveData 是可以被 UI 觀察到的特殊類型資料物件。
     * 當 _transactions.value 屬性更新時，UI 會被通知並可以更新自己以顯示新的資料。
     * 這只是使用 Flow 的一個簡單例子。Flow 可以用來執行各種任務，例如從伺服器擷取資料、 處理資料或執行動畫。
     */
    init {
        viewModelScope.launch {
            userDataRepository.getAllTransactions().collect {
                _transactions.value = transactions.value.copy(
                    list = it.reversed()
                )
            }
        }
    }

    fun onEvent(event: TransactionsEvent) {
        when (event) {
            is TransactionsEvent.OnExpandedChange -> {
                _transactionType.value = transactionType.value.copy(
                    isExpanded = !_transactionType.value.isExpanded
                )
            }
            is TransactionsEvent.OnDismissRequest -> {
                _transactionType.value = transactionType.value.copy(
                    isExpanded = false
                )
            }
            is TransactionsEvent.ChangeSelectedOption -> {
                _transactionType.value = transactionType.value.copy(
                    selectedOption = event.value
                )
                viewModelScope.launch {
                    userDataRepository.getAllTransactions().collect {
                        _transactions.value = transactions.value.copy(

                            list = when (_transactionType.value.selectedOption) {
                                "Expense" -> {
                                    it.filter { transaction ->
                                        transaction.transactionType == "Expense"

                                    }.reversed()
                                }
                                "Income" -> {
                                    it.filter { transaction ->
                                        transaction.transactionType == "Income"

                                    }.reversed()
                                }
                                else -> {
                                    it.reversed()
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}