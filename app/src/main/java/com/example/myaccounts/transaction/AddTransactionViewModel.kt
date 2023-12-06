package com.example.myaccounts.transaction

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myaccounts.data.TransactionRepository
import com.example.myaccounts.data.entity.TransactionEntity
import com.example.myaccounts.navigation.AppRoute.ADD_INCOME
import com.example.myaccounts.transaction.planned.AddTransactionType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddTransactionViewModel @Inject constructor(
    private val transactionRepository: TransactionRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val transactionId: Int = checkNotNull(savedStateHandle["id"])
    private val previousScreen: String = checkNotNull(savedStateHandle["previousScreen"])


    private val _title = mutableStateOf(
        AddTransactionTextFieldState(
            hint = "Title.. 輸入標題"
        )
    )
    val title: State<AddTransactionTextFieldState> = _title

    private val _tags = mutableStateOf(
        AddTransactionTextFieldState(
            hint = "Tags 輸入標籤"
        )
    )
    val tags: State<AddTransactionTextFieldState> = _tags

    private val _amount = mutableStateOf(
        AddTransactionTextFieldState(
            hint = "Amount 輸入金額"
        )
    )
    val amount: State<AddTransactionTextFieldState> = _amount

    private val _note = mutableStateOf(
        AddTransactionTextFieldState(
            hint = "Type a note.. 輸入註釋"
        )
    )
    val note: State<AddTransactionTextFieldState> = _note

    private val _transactionType = mutableStateOf(
        AddTransactionType(
          "TransactionType 交易類型"
        )
    )
    val transactionType: State<AddTransactionType> = _transactionType


    fun onEvent(event: AddTransactionEvent) {
        when (event) {
            is AddTransactionEvent.EnteredTitle -> {
                _title.value = title.value.copy(
                    text = event.value
                )
            }

            is AddTransactionEvent.EnteredAmount -> {
                _amount.value = amount.value.copy(
                    text = event.value
                )
            }

            is AddTransactionEvent.EnteredNote -> {
                _note.value = note.value.copy(
                    text = event.value
                )
            }

            is AddTransactionEvent.EnteredTags -> {
                _tags.value = tags.value.copy(
                    text = event.value
                )
            }

            is AddTransactionEvent.SaveTransaction -> {
                if (_amount.value.text != "") {
                    viewModelScope.launch {
                        if (previousScreen == ADD_INCOME) {
                            transactionRepository.update(
                                TransactionEntity(
                                    id = transactionId,
                                    title = _title.value.text,
                                    amount = _amount.value.text.toLong(),
                                    transactionType = _transactionType.value.addTransactionType,
                                    tags = _tags.value.text,
                                    date = getFormattedTime(),
                                    note = _note.value.text
                                )
                            )
                        } else {
                            transactionRepository.insert(
                                TransactionEntity(
                                    title = _title.value.text,
                                    amount = _amount.value.text.toLong(),
                                    transactionType = _transactionType.value.addTransactionType,
                                    tags = _tags.value.text,
                                    date = getFormattedTime(),
                                    note = _note.value.text
                                )
                            )
                        }
                    }

                    event.navHostController.navigateUp()

                }
            }
        }
    }
}