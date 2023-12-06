package com.example.myaccounts.transaction

import android.content.Context
import androidx.navigation.NavHostController

sealed class AddTransactionEvent {
    data class EnteredTitle(val value: String) : AddTransactionEvent()
    data class EnteredAmount(val value: String) : AddTransactionEvent()
    data class EnteredNote(val value: String) : AddTransactionEvent()
    data class EnteredTags(val value: String) : AddTransactionEvent()
    data class SaveTransaction(val context: Context, val navHostController: NavHostController) :
        AddTransactionEvent()
}