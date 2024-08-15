import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stock_table")
data class StockInfo(
    @PrimaryKey
    val stockSymbol: String,

    val companyName: String,
    val stockQuote: Double
)
