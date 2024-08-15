package com.fatima.fatimatuzzahra_comp304_handon_test2_w23

import kotlinx.coroutines.flow.Flow

class StockRepository(private val stockInfoDao: StockInfoDao) {

    fun insertStock(stockInfos: StockInfos) {
        return stockInfoDao.insertStock(stockInfos)
    }

    fun getStockBySymbol(symbol: String): Flow<List<StockInfos>>
    ? {
        return stockInfoDao.getStockBySymbol(symbol)
    }
}
