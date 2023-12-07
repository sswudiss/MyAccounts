package com.example.myaccounts.data

import com.example.myaccounts.data.dao.TransactionDao
import com.example.myaccounts.data.entity.TransactionEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 * 使用協程儲存庫將存取 Dao 以在表上觸發查詢
 */
interface TransactionRepository {
    suspend fun insert(transaction: TransactionEntity)
    suspend fun update(transaction: TransactionEntity)
    suspend fun getAllTransactions(): Flow<List<TransactionEntity>>
    suspend fun getTransactionById(id:Int): Flow<TransactionEntity>
    suspend fun deleteTransactionById(id:Int)

}