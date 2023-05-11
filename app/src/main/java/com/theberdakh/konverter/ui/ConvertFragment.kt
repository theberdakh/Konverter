package com.theberdakh.konverter.ui

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.theberdakh.konverter.R
import com.theberdakh.konverter.data.converter.ConverterFactory
import com.theberdakh.konverter.data.model.MenuItem
import com.theberdakh.konverter.data.model.SignList
import com.theberdakh.konverter.databinding.FragmentConvertBinding
import com.theberdakh.konverter.util.*


class ConvertFragment : Fragment(R.layout.fragment_convert) {
    private lateinit var binding: FragmentConvertBinding
    private var _adapter: ArrayAdapter<String>? = null
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

        binding.tbConvert.title = menuItem.title

        val signs = SignList.getSignList(menuItem.id)

        _adapter = ArrayAdapter<String>(requireContext(), R.layout.item_dropdown, signs)


        val autos = listOf(binding.autoFirstSign, binding.autoSecondSign)

        autos.onEach {
            it.setAdapter(adapter)
            it.setText(adapter.getItem(0).toString(), false)

            it.setOnItemClickListener { adapterView, view, position, l ->
                requireContext().hideKeyboard(it)

                val firstName = binding.autoFirstSign.text
                val secondName = binding.autoSecondSign.text

                val answer = if (!binding.etFirstNumber.text.isNullOrEmpty()) {
                    calculate(
                        firstName.toString(),
                        secondName.toString(),
                        binding.etFirstNumber.text.toString().toDouble(),
                        menuItem
                    ).toInteger().toString()
                } else {
                    Constants.EMPTY_STRING
                }

                binding.etSecondNumber.setText(answer)
            }
        }


    }

    private fun initListeners(menuItem: MenuItem) {
        binding.etFirstNumber.doAfterTextChanged {

            val firstName = binding.autoFirstSign.text.toString()
            val secondName = binding.autoSecondSign.text.toString()
            val firstValue = it.toString().checkSign()

            val answer: String = if (checkEditable(it)) calculate(
                firstName,
                secondName,
                firstValue,
                menuItem
            ).toInteger().toString() else Constants.EMPTY_STRING

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

        val factory = ConverterFactory()
        val converter = factory.createConverter(menuItem.id)
        return converter.convert(first, second, value)
    }


}