package com.test.nopstation_cart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.test.nopstation_cart.databinding.ItemProductBinding
import com.test.nopstation_cart.models.BestSellingItem

class BestSellingAdapter(
    private val onClick: (BestSellingItem) -> Unit
): ListAdapter<BestSellingItem, BestSellingAdapter.ViewHolder>(DIFF_CALLBACK) {
    class ViewHolder(
        private val binding : ItemProductBinding,
        private val onClick: (BestSellingItem) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
            fun bind(item: BestSellingItem){
                binding.ivProductImage.setImageResource(item.productImage)
                binding.tvProductName.text = item.productName
                binding.rbProductRating.rating = item.productRating
                binding.tvProductPrice.text = "$${item.productPrice}"
            }

        companion object {
            fun from(
                parent: ViewGroup,
                onClick: (BestSellingItem) -> Unit
            ): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemProductBinding.inflate(layoutInflater, parent, false)
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

    override fun onBindViewHolder(holder: BestSellingAdapter.ViewHolder, position: Int) {
        val item = getItem(position)
        return holder.bind(item)
    }


    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<BestSellingItem>(){
            override fun areItemsTheSame(
                oldItem: BestSellingItem,
                newItem: BestSellingItem
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: BestSellingItem,
                newItem: BestSellingItem
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}