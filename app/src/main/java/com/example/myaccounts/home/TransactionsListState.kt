package com.example.myaccounts.home

import com.example.myaccounts.data.entity.TransactionEntity

data class TransactionsListState(
    val list: List<TransactionEntity> = mutableListOf()
)
