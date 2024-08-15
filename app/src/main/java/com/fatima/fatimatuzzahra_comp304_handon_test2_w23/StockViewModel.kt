package com.fatima.fatimatuzzahra_comp304_handon_test2_w23

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StockViewModel(private val repository: StockRepository) : ViewModel() {

    fun insertStock(stockInfos: StockInfos) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertStock(stockInfos)
        }
    }

    fun getStockBySymbol(symbol: String, callback: (StockInfos?) -> Unit) {
        viewModelScope.launch(Dispatchers.IO){
            repository.getStockBySymbol(symbol)?.collect(){

                callback(it.get(0))
            }

        }
    }
}


