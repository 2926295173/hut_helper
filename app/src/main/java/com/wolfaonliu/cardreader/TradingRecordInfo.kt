package com.wolfaonliu.cardreader

import java.io.Serializable
import java.util.*

//TODO 单个交易记录类
class TradingRecordInfo : Serializable, Comparable<TradingRecordInfo> {
    var tradingDateTime: String? = null
        private set
    var tradingMoney: String? = null
        private set
    var tradingType = 0
    override fun compareTo(other: TradingRecordInfo): Int {
        return other.tradingDateTime!!.compareTo(tradingDateTime!!)
    }

    fun setTradingDateTime(str: String) {
        tradingDateTime = str.substring(0, 4) + "." + str.substring(4, 6) + "." + str.substring(
            6,
            8
        ) + " " + str.substring(8, 10) + ":" + str.substring(10, 12)
    }

    fun setTradingMoney(j: Long) {
        tradingMoney = String.format(Locale.getDefault(), "%.2f", j / 100.0)
    }

    companion object {
        private const val serialVersionUID = -6533154177080647539L
    }
}