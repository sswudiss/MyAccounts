package com.example.myaccounts.transaction

import com.example.myaccounts.data.entity.TransactionEntity

data class TransactionSate(
    val list: List<TransactionEntity> = mutableListOf(),
    val noAmount: Boolean = false,  //沒有輸入金額
    val chooseType: String = ""
)
