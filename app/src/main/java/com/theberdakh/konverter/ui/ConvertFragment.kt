package com.theberdakh.konverter.ui

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.theberdakh.konverter.R
import com.theberdakh.konverter.data.model.MenuItem
import com.theberdakh.konverter.databinding.FragmentConvertBinding
import com.theberdakh.konverter.ui.util.*
import com.theberdakh.konverter.util.hideKeyboard


class ConvertFragment : Fragment(R.layout.fragment_convert) {
    private lateinit var binding: FragmentConvertBinding
    private var _adapter: ArrayAdapter<String>? =null
    private val adapter get() = _adapter!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentConvertBinding.bind(view)
        val args: ConvertFragmentArgs by navArgs()
        val menuItem = args.menuItem
        initViews(menuItem)
        initListeners(menuItem)


    }

    private fun initViews(menuItem: MenuItem) {
        val temperatureSigns = resources.getStringArray(R.array.Temperature)
        val weightSigns = resources.getStringArray(R.array.Weight)
        val lengthSigns = resources.getStringArray(R.array.Length)

        binding.tbConvert.title = menuItem.title

        val signs = when (menuItem.id) {
            1 -> temperatureSigns
            2 -> weightSigns
            else -> lengthSigns
        }

        _adapter = ArrayAdapter<String>(requireContext(), R.layout.item_dropdown, signs)


        val autos = listOf(binding.autoFirstSign, binding.autoSecondSign)

        autos.onEach {
            it.setAdapter(adapter)
            it.setText(adapter.getItem(0).toString(), false)

            it.setOnItemClickListener { adapterView, view, position, l ->
                requireContext().hideKeyboard(it)

                val firstName = binding.autoFirstSign.text
                val secondName = binding.autoSecondSign.text

              val answer =  if (!binding.etFirstNumber.text.isNullOrEmpty()){
                      calculate(
                        firstName.toString(),
                        secondName.toString(),
                        binding.etFirstNumber.text.toString().toDouble(),
                        menuItem
                    ).toInteger().toString()
                }
                 else {
                     Constants.EMPTY_STRING
              }

                binding.etSecondNumber.setText(answer)
            }
        }


    }

    private fun initListeners(
        menuItem: MenuItem
    )
    {
        binding.etFirstNumber.doAfterTextChanged {
            val firstName = binding.autoFirstSign.text
            val secondName = binding.autoSecondSign.text



            val answer: String = if (it != null && it.isNotEmpty() && it.first() != '.')
                calculate(
                    firstName.toString(),
                    secondName.toString(),
                    it.toString().checkSign(),
                    menuItem
                ).toInteger().toString()
             else
                Constants.EMPTY_STRING

            binding.etSecondNumber.setText(answer)
        }


        binding.tbConvert.setNavigationOnClickListener {
            findNavController().popBackStack()
        }



    }

    private fun calculate(
        first: String,
        second: String,
        value: Double,
        menuItem: MenuItem
    ): Double {

        return when (menuItem.id) {
            1 -> convertTemperature(first, second, value)
            2 -> convertWeight(first, second, value)
            else -> convertLength(first, second, value)
        }


    }

    private fun convertLength(first: String, second: String, value: Double): Double {
        return 0.0
    }

    private fun convertWeight(first: String, second: String, value: Double): Double {
        return 0.0
    }

    private fun convertTemperature(
        first: String,
        second: String,
        value: Double
    ): Double {


        return when {
            first == Constants.CELSIUS && second == Constants.CELSIUS -> value.toCelsius(Constants.CELSIUS)
            first == Constants.KELVIN && second == Constants.CELSIUS -> value.toCelsius(Constants.KELVIN)
            first == Constants.FAHRENHEIT && second == Constants.CELSIUS -> value.toCelsius(Constants.FAHRENHEIT)

            first == Constants.CELSIUS && second == Constants.KELVIN -> value.toKelvin(Constants.CELSIUS)
            first == Constants.KELVIN && second == Constants.KELVIN -> value.toKelvin(Constants.KELVIN)
            first == Constants.FAHRENHEIT && second == Constants.KELVIN -> value.toKelvin(Constants.FAHRENHEIT)

            first == Constants.CELSIUS && second == Constants.FAHRENHEIT -> value.toFahrenheit(Constants.CELSIUS)
            first == Constants.KELVIN && second == Constants.FAHRENHEIT -> value.toFahrenheit(Constants.KELVIN)
            first == Constants.FAHRENHEIT && second == Constants.FAHRENHEIT -> value.toFahrenheit(Constants.FAHRENHEIT)
            else -> 0.0
        }
    }

}