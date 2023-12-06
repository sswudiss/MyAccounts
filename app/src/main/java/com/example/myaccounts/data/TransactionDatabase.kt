package com.example.myaccounts.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myaccounts.data.dao.TransactionDao
import com.example.myaccounts.data.entity.TransactionEntity

@Database(
    entities = [TransactionEntity::class],
    version = 1,
    exportSchema = false
)
abstract class TransactionsDB : RoomDatabase() {

//    abstract fun transactionDao(): TransactionDao
    abstract val transactionDao: TransactionDao

    companion object {
        const val DATABASE_NAME = "transactions_db"

/*  The value of a volatile variable will never be cached,
    and all writes and reads will be done to and from the main memory.
    This helps make sure the value of INSTANCE is always up-to-date and the same for all execution threads.
    It means that changes made by one thread to INSTANCE are visible to all other threads immediately.
    易失性變數的值永遠不會被緩存，所有寫入和讀取都將在主記憶體中進行。
    這有助於確保實例的值始終是最新的並且對於所有執行緒都是相同的。
    這意味著一個線程對實例所做的更改立即對所有其他線程可見
*/
        @Volatile
        private var INSTANCE: TransactionsDB? = null

        fun getInstance(context: Context): TransactionsDB {
            // only one thread of execution at a time can enter this block of code
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TransactionsDB::class.java,
                        "employee_database"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}