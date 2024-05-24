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

class OurCategoryAdapter(
    private val onClick: (CategoryItem) -> Unit
): ListAdapter<CategoryItem, OurCategoryAdapter.ViewHolder>(DIFF_CALLBACK) {
    class ViewHolder(
        private val binding: ItemCategoryBinding,
        private val onClick: (CategoryItem) -> Unit
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CategoryItem){
            binding.ivCategoryItem.load(item.categoryImage)
            binding.tvCategoryName.text = item.categoryName
            binding.root.setOnClickListener {
                onClick(item)
            }
        }

        companion object{
            fun from(
                parent: ViewGroup,
                onClick: (CategoryItem) -> Unit
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
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CategoryItem>(){
            override fun areItemsTheSame(
                oldItem: CategoryItem,
                newItem: CategoryItem
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: CategoryItem,
                newItem: CategoryItem
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}