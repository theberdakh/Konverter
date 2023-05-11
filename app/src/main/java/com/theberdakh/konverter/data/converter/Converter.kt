package com.theberdakh.konverter.data.converter

interface Converter {
    fun convert(from: String, to: String, value: Double): Double
}