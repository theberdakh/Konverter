package com.theberdakh.konverter.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.theberdakh.konverter.R
import com.theberdakh.konverter.data.model.MenuItem
import com.theberdakh.konverter.databinding.ItemRecyclerMenuBinding

class MenuItemAdapter: ListAdapter<MenuItem, MenuItemAdapter.MenuItemViewHolder>(MenuItemCallback) {

    inner class MenuItemViewHolder(private val binding: ItemRecyclerMenuBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(){
            val menuItem = getItem(adapterPosition)
            binding.root.setOnClickListener {
                onMenuItemClick.invoke(menuItem)
            }
            binding.ivIcon.setImageResource(menuItem.image)
            binding.tvTitle.text = menuItem.title

        }
    }

    private object MenuItemCallback: DiffUtil.ItemCallback<MenuItem>(){
        override fun areItemsTheSame(oldItem: MenuItem, newItem: MenuItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: MenuItem, newItem: MenuItem): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemViewHolder {
        return MenuItemViewHolder(ItemRecyclerMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MenuItemViewHolder, position: Int) = holder.bind()

    private lateinit var onMenuItemClick: (MenuItem) -> Unit
    fun setOnMenuItemClickListener(menuItem: (MenuItem) -> Unit){
        this.onMenuItemClick = menuItem
    }
}