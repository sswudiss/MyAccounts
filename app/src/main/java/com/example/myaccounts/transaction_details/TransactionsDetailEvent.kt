package com.example.myaccounts.transaction_details

import android.content.Context
import androidx.navigation.NavHostController
import com.example.myaccounts.data.entity.TransactionEntity

sealed class TransactionsDetailEvent {
    data class Share(val context: Context) : TransactionsDetailEvent()
    data class Edit(val transaction:TransactionEntity) : TransactionsDetailEvent()
    data class Delete(val navHostController: NavHostController, val id: Int) : TransactionsDetailEvent()
}