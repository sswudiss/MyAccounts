package com.example.myaccounts.transaction.income

import android.content.Context
import androidx.navigation.NavHostController

sealed class IncomeEvent {
    data class EnteredTitle(val value: String) : IncomeEvent()
    data class EnteredAmount(val value: String) : IncomeEvent()
    data class EnteredNote(val value: String) : IncomeEvent()
    data class EnteredTags(val value: String) : IncomeEvent()
    data class SaveTransaction(val context: Context, val navController: NavHostController) :
        IncomeEvent()

    object OnExpandedChange : IncomeEvent()  //擴展變更時
    object OnDismissRequest : IncomeEvent()  //關閉請求時
    //更改選定選項
    data class ChangeSelectedOption(val value: String) : IncomeEvent()

    object OpenDialog : IncomeEvent()  //開啟對話框
    object CloseDialog : IncomeEvent() //關閉對話框

}