package com.test.nopstation_cart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.test.nopstation_cart.databinding.ItemCategoryBinding
import com.test.nopstation_cart.models.category.Data as OurCategoryItem

class CategoryListAdapter(
    private val onClick: (OurCategoryItem,String) -> Unit
): ListAdapter<OurCategoryItem, CategoryListAdapter.ViewHolder>(DIFF_CHECK) {
    class ViewHolder(
        private val binding: ItemCategoryBinding,
        private val onClick: (OurCategoryItem,String) -> Unit
    ): RecyclerView.ViewHolder(binding.root) {
            fun bind(item: OurCategoryItem){
                binding.ivCategoryItem.load(item.products[0].pictureModels[0].imageUrl)
                binding.tvCategoryName.text = item.name
                binding.root.setOnClickListener {
                    onClick(item, item.name)
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