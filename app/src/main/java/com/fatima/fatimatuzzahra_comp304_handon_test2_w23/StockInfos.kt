package com.fatima.fatimatuzzahra_comp304_handon_test2_w23
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stock_table")
data class StockInfos(
    @PrimaryKey
    @ColumnInfo(name = "stockSymbol")
    val stockSymbol: String,
    @ColumnInfo(name = "companyName")
    val companyName: String,
    @ColumnInfo(name = "stockQuote")
    val stockQuote: Double
)