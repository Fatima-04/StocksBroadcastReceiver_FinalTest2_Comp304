package com.fatima.fatimatuzzahra_comp304_handon_test2_w23

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: StockViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Initialize ViewModel and Repository
        val dao = StockDatabase.getDatabase(application).stockInfoDao()
        val repository = StockRepository(dao)
        viewModel = ViewModelProvider(this, StockViewModelFactory(repository))
            .get(StockViewModel::class.java)

        findViewById<Button>(R.id.button_insert_stocks).setOnClickListener {
            val stock1 = StockInfos("GOOGL", "Google Inc.", 2800.0)
            val stock2 = StockInfos("AMZN", "Amazon Inc.", 150.0)
            val stock3 = StockInfos("SSNLF", "Ssnlf Inc.", 150.0)

            viewModel.insertStock(stock1)
            viewModel.insertStock(stock2)
            viewModel.insertStock(stock3)

        }
        findViewById<Button>(R.id.btn_display_stock_info).setOnClickListener {
            val selectedSymbol = when (findViewById<RadioGroup>(R.id.radioGroup).checkedRadioButtonId) {
                R.id.rad_googl -> "GOOGL"
                R.id.rad_amzn -> "AMZN"
                R.id.rad_ssnlf -> "SSNLF"
                else -> null

            }
            selectedSymbol?.let { symbol ->
                viewModel.getStockBySymbol(symbol) { stockInfos ->
                    stockInfos?.let {
                        findViewById<TextView>(R.id.txt_view_stock_info).text =
                            "Company Name: ${it.companyName}\nStock Quote: ${it.stockQuote}"
                        CoroutineScope(Dispatchers.Main).launch {
                            sendBroadcastMessage(stockInfos)
                        }
                    }
                }
            }
        }
    }
    private fun sendBroadcastMessage(stockInfos: StockInfos) {
        val intent = Intent(this, StockBroadcastReceiver::class.java)
        intent.putExtra("stockInfo", "${stockInfos.stockSymbol}: ${stockInfos.stockQuote}")
        sendBroadcast(intent)
    }

}

