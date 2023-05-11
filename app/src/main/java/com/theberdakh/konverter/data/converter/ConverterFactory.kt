package com.theberdakh.konverter.data.converter

import com.theberdakh.konverter.data.converter.length.Length
import com.theberdakh.konverter.data.converter.temperature.Temperature
import com.theberdakh.konverter.data.converter.weight.Weight

class ConverterFactory() {

    fun createConverter(id: Int): Converter {
        return when(id){
            1 -> Temperature()
            2 -> Weight()
            3 -> Length()
            else -> throw IllegalArgumentException("Invalid converter type")
        }
    }
}