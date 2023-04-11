package com.theberdakh.konverter.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.theberdakh.konverter.R
import com.theberdakh.konverter.data.model.MenuItemList
import com.theberdakh.konverter.databinding.FragmentMenuBinding
import com.theberdakh.konverter.ui.adapter.MenuItemAdapter

class MenuFragment: Fragment(R.layout.fragment_menu) {
    private lateinit var binding: FragmentMenuBinding
    private var _adapter: MenuItemAdapter? = null
    private val adapter get() = _adapter!!
    private var _navController: NavController? = null
    private val navController get() = _navController!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMenuBinding.bind(view)

        initViews()
        initListeners()



    }

    private fun initListeners() {
        adapter.setOnMenuItemClickListener {
            navController.navigate(MenuFragmentDirections.actionMenuFragmentToConvertFragment(it))
        }
    }

    private fun initViews() {
        _navController = findNavController()
        _adapter = MenuItemAdapter()
        binding.rvMenu.adapter = adapter
        adapter.submitList(
            MenuItemList.getAllItems()
        )
    }
}