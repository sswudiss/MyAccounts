package com.example.myaccounts.transaction

import com.example.myaccounts.data.entity.TransactionEntity

data class TransactionsState(
    val list: List<TransactionEntity> = mutableListOf()
)