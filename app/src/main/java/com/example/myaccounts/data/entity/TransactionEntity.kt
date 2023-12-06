package com.example.myaccounts.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 用一個頁面進行收入和支出的記錄表
 */
@Entity
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val title: String,
    val amount: Long,
    val transactionType: String,
    val date: String,
    val tags: String,
    val note: String
)
