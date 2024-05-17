package com.test.nopstation_cart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.test.nopstation_cart.databinding.ItemProductBinding
import com.test.nopstation_cart.models.ProductItem

class ProductListAdapter(
    private val onClick: (ProductItem) -> Unit
): ListAdapter<ProductItem, ProductListAdapter.ViewHolder>(DIFF_CALLBACK) {
    class ViewHolder(
        private val binding: ItemProductBinding,
        private val onClick: (ProductItem) -> Unit
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ProductItem) {
            binding.tvProductName.text = product.productName
            binding.ivProductImage.setImageResource(product.productImage)
            binding.rbProductRating.rating = product.productRating
            binding.tvProductPrice.text = "$${product.productPrice}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemProductBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding, onClick)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ProductItem>(){
            override fun areItemsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
                return oldItem == newItem
            }

        }
    }

}

