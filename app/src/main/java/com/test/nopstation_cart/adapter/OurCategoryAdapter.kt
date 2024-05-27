package com.test.nopstation_cart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.test.nopstation_cart.databinding.ItemCategoryBinding
import com.test.nopstation_cart.models.CategoryItem
import com.test.nopstation_cart.models.OurCategoryItem
import com.test.nopstation_cart.models.category.Data

class OurCategoryAdapter(
    private val onClick: (Data, String) -> Unit
): ListAdapter<Data, OurCategoryAdapter.ViewHolder>(DIFF_CALLBACK) {
    class ViewHolder(
        private val binding: ItemCategoryBinding,
        private val onClick: (Data,String) -> Unit
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Data){
            binding.ivCategoryItem.load(item.products[0].pictureModels[0].imageUrl)
            binding.tvCategoryName.text = item.name
            binding.root.setOnClickListener {
                onClick(item,item.name)
            }
        }

        companion object{
            fun from(
                parent: ViewGroup,
                onClick: (Data,String) -> Unit
            ): ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemCategoryBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding, onClick)
            }
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder.from(parent,onClick)
    }

    override fun onBindViewHolder(holder: OurCategoryAdapter.ViewHolder, position: Int) {
        val item = getItem(position)
        return holder.bind(item)
    }

    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Data>(){
            override fun areItemsTheSame(
                oldItem: Data,
                newItem: Data
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Data,
                newItem: Data
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}