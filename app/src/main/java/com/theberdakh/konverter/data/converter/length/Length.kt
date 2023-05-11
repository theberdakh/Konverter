package com.theberdakh.konverter.data.converter.length

import com.theberdakh.konverter.data.converter.Converter
import com.theberdakh.konverter.util.Constants

class Length: Converter {
    override fun convert(from: String, to: String, value: Double): Double {
       return when(to){
            Constants.CENTIMETER -> value.toCentimeter(from)
            Constants.METER -> value.toMeter(from)
            Constants.KILOMETER -> value.toKilometer(from)
           else -> 0.0
        }
    }
}