package com.fatima.fatimatuzzahra_comp304_handon_test2_w23


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface StockInfoDao {
    @Insert
    fun insertStock(stockInfo: StockInfos)

    @Query("SELECT * FROM stock_table WHERE stockSymbol = :symbol")
    fun getStockBySymbol(symbol: String): Flow<List<StockInfos>>?
}
