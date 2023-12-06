package com.example.myaccounts.transaction

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myaccounts.data.TransactionRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class TransactionsViewModel @Inject constructor(
    private val transactionRepository: TransactionRepository
) : ViewModel() {
    private val _transactions = mutableStateOf(TransactionSate())
    val transactions: State<TransactionSate> = _transactions

    var enterAmount by mutableIntStateOf(0)
        private set


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
            transactionRepository.getAllTransactions().collect {
                _transactions.value = transactions.value.copy(
                    list = it.reversed() //傳回一個列表，其中元素的順序相反
                )
            }
        }
    }


    fun onEvent(event: TransactionsEvent) {
        viewModelScope.launch {
            transactionRepository.getAllTransactions().collect {
                _transactions.value = transactions.value.copy(

                    list = when (_transactions.value.chooseType) {
                        "Expense" -> {
                            it.filter { transaction ->
                                transaction.transactionType == "Expense"

                            }.reversed() //傳回一個列表，其中元素的順序相反
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

/*
    fun checkEnterAmount() {
        if (enterAmount.equals(null)) {
            _transactions.update {
                it.copy(noAmount = true)
            }
        }
    }

*/

