package com.example.myaccounts.transaction

sealed class TransactionsEvent {
 /*   object OnExpandedChange : TransactionsEvent()
    object OnDismissRequest : TransactionsEvent()*/
    data class IncomeOrExpense(val value: String) : TransactionsEvent()
}

/**
 * TransactionsEvent 是一个密封类，它表示在交易列表中可能发生的各种事件。 这三个事件是：
 * OnExpandedChange：当交易项的展开状态发生更改时， 会触发此事件。
 * OnDismissRequest：当用户请求取消交易项时， 会触发此事件。
 * ChangeSelectedOption： 当用户从可用选项列表中选择不同的选项时， 会触发此事件。
 * ChangeSelectedOption 事件包含一个 value 属性，该属性表示选定的选项。value 属性可以是任何字符串值。
 */