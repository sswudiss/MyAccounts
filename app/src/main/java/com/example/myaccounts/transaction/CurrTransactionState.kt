package com.example.myaccounts.transaction

import com.example.myaccounts.data.entity.TransactionEntity

data class CurrTransactionState(
    val transaction: TransactionEntity? = TransactionEntity(
        id = 1,
        transactionType = "",
        title = "",
        amount = 0,
        tags = "",
        date = "",
        note = ""
    )
)