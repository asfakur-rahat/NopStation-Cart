package com.test.nopstation_cart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.test.nopstation_cart.databinding.ItemCategoryBinding
import com.test.nopstation_cart.models.OurCategoryItem

class CategoryListAdapter(
    private val onClick: (OurCategoryItem) -> Unit
): ListAdapter<OurCategoryItem, CategoryListAdapter.ViewHolder>(DIFF_CHECK) {
    class ViewHolder(
        private val binding: ItemCategoryBinding,
        private val onClick: (OurCategoryItem) -> Unit
    ): RecyclerView.ViewHolder(binding.root) {
            fun bind(item: OurCategoryItem){
                binding.ivCategoryItem.setImageResource(item.categoryImage)
                binding.tvCategoryName.text = item.categoryName
                binding.root.setOnClickListener {
                    onClick(item)
                }
            }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding  = ItemCategoryBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        return holder.bind(item)
    }

    companion object {
        val DIFF_CHECK = object : DiffUtil.ItemCallback<OurCategoryItem>() {
            override fun areItemsTheSame(
                oldItem: OurCategoryItem,
                newItem: OurCategoryItem
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: OurCategoryItem,
                newItem: OurCategoryItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}