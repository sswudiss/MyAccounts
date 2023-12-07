package com.example.myaccounts.transaction.add_transaction

import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myaccounts.data.TransactionRepository
import com.example.myaccounts.data.entity.TransactionEntity
import com.example.myaccounts.navigation.Screen
import com.example.myaccounts.utils.getFormattedTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddEditTransactionViewModel @Inject constructor(
    private val userDataRepository: TransactionRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val transactionId: Int = checkNotNull(savedStateHandle["id"])
    private val previousScreen: String = checkNotNull(savedStateHandle["previousScreen"])


    private val _title = mutableStateOf(
        AddEditTransactionTextFieldState(
            hint = "Title.. 輸入標題"
        )
    )
    val title: State<AddEditTransactionTextFieldState> = _title

    private val _tags = mutableStateOf(
        AddEditTransactionTextFieldState(
            hint = "Tags 輸入標籤"
        )
    )
    val tags: State<AddEditTransactionTextFieldState> = _tags

    private val _amount = mutableStateOf(
        AddEditTransactionTextFieldState(
            hint = "Amount 輸入金額"
        )
    )
    val amount: State<AddEditTransactionTextFieldState> = _amount

    private val _note = mutableStateOf(
        AddEditTransactionTextFieldState(
            hint = "Type a note.. 輸入註釋"
        )
    )
    val note: State<AddEditTransactionTextFieldState> = _note

    private val _transactionType = mutableStateOf(
        AddEditTransactionDropDownMenuState(
            hint = "TransactionType 交易類型"
        )
    )
    val transactionType: State<AddEditTransactionDropDownMenuState> = _transactionType

    init {

        //上一個螢幕
        if (previousScreen == Screen.TransactionDetails.route) {
            viewModelScope.launch {
                userDataRepository.getTransactionById(transactionId).collect{
                    _title.value = title.value.copy(
                        text = it.title
                    )
                    _amount.value = amount.value.copy(
                        text = it.amount.toString()
                    )
                    _note.value = note.value.copy(
                        text = it.note
                    )
                    _tags.value = tags.value.copy(
                        text = it.tags
                    )
                    _transactionType.value = transactionType.value.copy(
                        selectedOption = it.transactionType
                    )
                    _dialogState.value = dialogState.value.copy(
                        text = "Do you want to discard changes?"
                    )
                }
            }
        }
    }

    private val _dialogState = mutableStateOf(DialogState())
    val dialogState: State<DialogState> = _dialogState


    fun onEvent(event: AddEditTransactionEvent) {
        when (event) {
            is AddEditTransactionEvent.EnteredTitle -> {
                _title.value = title.value.copy(
                    text = event.value
                )

            }
            is AddEditTransactionEvent.EnteredAmount -> {
                _amount.value = amount.value.copy(
                    text = event.value
                )
            }
            is AddEditTransactionEvent.EnteredNote -> {
                _note.value = note.value.copy(
                    text = event.value
                )
            }


            is AddEditTransactionEvent.OnExpandedChange -> {
                _transactionType.value = transactionType.value.copy(
                    isExpanded = !_transactionType.value.isExpanded
                )
            }
            is AddEditTransactionEvent.OnDismissRequest -> {
                _transactionType.value = transactionType.value.copy(
                    isExpanded = false
                )
            }
            is AddEditTransactionEvent.ChangeSelectedOption -> {
                _transactionType.value = transactionType.value.copy(
                    selectedOption = event.value
                )
            }
            is AddEditTransactionEvent.EnteredTags -> {
                _tags.value = tags.value.copy(
                    text = event.value
                )
            }
            is AddEditTransactionEvent.SaveEditTransaction -> {
                if (
                    _title.value.text != "" && _tags.value.text != "" && _transactionType.value.selectedOption != "" && _note.value.text != "" && _amount.value.text != ""
                ) {
                    viewModelScope.launch {
                        if (previousScreen == Screen.TransactionDetails.route){
                            userDataRepository.update(
                                TransactionEntity(
                                    id = transactionId,
                                    title = _title.value.text,
                                    amount = _amount.value.text.toLong(),
                                    transactionType = _transactionType.value.selectedOption,
                                    tags = _tags.value.text,
                                    date = getFormattedTime(),
                                    note = _note.value.text
                                )
                            )
                        }else{
                            userDataRepository.insert(
                                TransactionEntity(
                                    title = _title.value.text,
                                    amount = _amount.value.text.toLong(),
                                    transactionType = _transactionType.value.selectedOption,
                                    tags = _tags.value.text,
                                    date = getFormattedTime(),
                                    note = _note.value.text


                                )
                            )
                        }}

                    event.navHostController.navigateUp()

                } else {
                    Toast.makeText(
                        event.context,
                        "Please fill-up all attributes.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            is AddEditTransactionEvent.OpenDialog -> {
                _dialogState.value = dialogState.value.copy(
                    state = true
                )
            }
            is AddEditTransactionEvent.CloseDialog -> {
                _dialogState.value = dialogState.value.copy(
                    state = false
                )
            }
        }
    }
}