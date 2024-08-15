package com.fatima.fatimatuzzahra_comp304_handon_test2_w23

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [StockInfos::class], version = 1)
abstract class StockDatabase : RoomDatabase() {
    abstract fun stockInfoDao(): StockInfoDao

    companion object {
        @Volatile
        private var INSTANCE: StockDatabase? = null

        fun getDatabase(context: Context): StockDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StockDatabase::class.java,
                    "stock_database1"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}


