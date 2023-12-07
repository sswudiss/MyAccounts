package com.example.myaccounts.transaction

//交易下拉選單狀態
data class TransactionsDropDownMenuState(
    val isExpanded: Boolean = false,
    val selectedOption: String = "",
)
