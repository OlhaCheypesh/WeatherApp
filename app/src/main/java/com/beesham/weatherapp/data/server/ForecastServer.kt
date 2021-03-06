package com.beesham.weatherapp.data.server

import com.beesham.weatherapp.data.db.ForecastDb
import com.beesham.weatherapp.domain.dataSource.ForecastDataSource
import com.beesham.weatherapp.domain.model.Forecast
import com.beesham.weatherapp.domain.model.ForecastList
import java.lang.UnsupportedOperationException

class ForecastServer(private val dataMapper: ServerDataMapper = ServerDataMapper(),
                     private val forecastDb: ForecastDb = ForecastDb()): ForecastDataSource {

    override fun requestDayForecast(id: Long) = throw UnsupportedOperationException()

    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? {
        val result = ForecastByZipCodeRequest(zipCode).execute()
        val converted = dataMapper.convertToDomain(zipCode, result)
        forecastDb.saveForecast(converted)
        return forecastDb.requestForecastByZipCode(zipCode, date)
    }

}