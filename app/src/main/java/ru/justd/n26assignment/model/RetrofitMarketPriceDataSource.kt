package ru.justd.n26assignment.model

import android.text.format.DateUtils
import rx.Single
import java.lang.UnsupportedOperationException

/**
 * Created by defuera on 27/03/2017.
 */
open class RetrofitMarketPriceDataSource constructor(private val api: RestClient) : MarketPriceDataSource {

    override fun loadPrices(period: Period): Single<ChartsResponse<MarketPrice>> {

        return when (period) {
            Period.week -> api.getMarketPrices("day", (System.currentTimeMillis() - DateUtils.WEEK_IN_MILLIS) / 1000)
            Period.month -> api.getMarketPrices("day", (System.currentTimeMillis() - DateUtils.WEEK_IN_MILLIS * 4) / 1000)
            Period.year -> api.getMarketPrices("month", (System.currentTimeMillis() - DateUtils.YEAR_IN_MILLIS) / 1000)
            else -> throw IllegalArgumentException("unknown period $period")
        }

    }

    override fun storePrices(period: Period, data: ChartsResponse<MarketPrice>) {
        throw UnsupportedOperationException()
    }


}