package com.theberdakh.konverter.data.converter.temperature

import com.theberdakh.konverter.data.converter.Converter
import com.theberdakh.konverter.util.Constants

class Temperature: Converter {
    override fun convert(from: String, to: String, value: Double): Double {
        return when(to) {
            Constants.CELSIUS -> value.toCelsius(from)
            Constants.KELVIN -> value.toKelvin(from)
            Constants.FAHRENHEIT -> value.toFahrenheit(from)
            else -> 0.0
        }
    }
}