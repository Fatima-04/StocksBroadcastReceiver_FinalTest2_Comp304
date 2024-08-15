package com.fatima.fatimatuzzahra_comp304_handon_test2_w23

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class StockBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val stockInfo = intent?.getStringExtra("stockInfo")
        Toast.makeText(context, stockInfo, Toast.LENGTH_LONG).show()
    }
}
