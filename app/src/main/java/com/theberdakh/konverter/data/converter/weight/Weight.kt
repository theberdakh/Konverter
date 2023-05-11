package com.theberdakh.konverter.data.converter.weight

import com.theberdakh.konverter.data.converter.Converter
import com.theberdakh.konverter.util.Constants

class Weight: Converter {

    override fun convert(from: String, to: String, value: Double): Double {
        return when(to){
            Constants.GRAM -> value.toGram(from)
            Constants.KILOGRAM -> value.toKilogram(from)
            Constants.TON -> value.toTon(from)
            else -> 0.0
        }
    }
}