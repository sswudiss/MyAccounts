package com.example.myaccounts.data

import com.example.myaccounts.data.dao.TransactionDao
import com.example.myaccounts.data.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow

class TransactionRepositoryImpl(
    private val dao: TransactionDao,
) : TransactionRepository {

    override suspend fun insert(transaction: TransactionEntity) {
        dao.insertTransaction(transaction)
    }

    override suspend fun update(transaction: TransactionEntity) {
        dao.updateTransaction(transaction)
    }

    override suspend fun getAllTransactions(): Flow<List<TransactionEntity>> {
        return dao.getAllTransactions()
    }

    override suspend fun getTransactionById(id: Int): Flow<TransactionEntity> {
        return dao.getTransactionById(id)
    }

    override suspend fun deleteTransactionById(id: Int) {
        dao.deleteTransactionById(id)
    }
}