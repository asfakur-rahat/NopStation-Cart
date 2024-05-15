package com.test.nopstation_cart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.test.nopstation_cart.databinding.ItemProductBinding
import com.test.nopstation_cart.models.ProductItem

class FeaturedProductAdapter(
    private val onClick: (ProductItem) -> Unit
): ListAdapter<ProductItem, FeaturedProductAdapter.ViewHolder>(DIFF_CALLBACK) {
    class ViewHolder(
        private val binding : ItemProductBinding,
        private val onClick: (ProductItem) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ProductItem){
            binding.ivProductImage.setImageResource(item.productImage)
            binding.tvProductName.text = item.productName
            binding.rbProductRating.rating = item.productRating
            binding.tvProductPrice.text = "$${item.productPrice}"
        }

        companion object {
            fun from(
                parent: ViewGroup,
                onClick: (ProductItem) -> Unit
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

    override fun onBindViewHolder(holder: FeaturedProductAdapter.ViewHolder, position: Int) {
        val item = getItem(position)
        return holder.bind(item)
    }


    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ProductItem>(){
            override fun areItemsTheSame(
                oldItem: ProductItem,
                newItem: ProductItem
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ProductItem,
                newItem: ProductItem
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}