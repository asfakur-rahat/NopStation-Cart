package com.test.nopstation_cart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.test.nopstation_cart.R
import com.test.nopstation_cart.databinding.ItemProductBinding
import com.test.nopstation_cart.models.ProductItem
import com.test.nopstation_cart.models.ProductItems

class FeaturedProductAdapter(
    private val onClick: (ProductItems) -> Unit,
    private val onAddToCart: (ProductItems) -> Unit
): ListAdapter<ProductItems, FeaturedProductAdapter.ViewHolder>(DIFF_CALLBACK) {
    class ViewHolder(
        private val binding : ItemProductBinding,
        private val onClick: (ProductItems) -> Unit,
        private val onAddToCart: (ProductItems) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ProductItems){
            binding.tvProductName.text = item.productName
            binding.ivProductImage.load(item.productImage)
            binding.rbProductRating.rating = item.productRating
            binding.tvProductPrice.text = "$%.2f".format(item.productPrice)

            binding.root.setOnClickListener {
                onClick(item)
            }

            binding.ibAddToCart.setOnClickListener {
                onAddToCart(item)
            }

        }

        companion object {
            fun from(
                parent: ViewGroup,
                onClick: (ProductItems) -> Unit,
                onAddToCart: (ProductItems) -> Unit
            ): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemProductBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding, onClick, onAddToCart)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder.from(parent,onClick, onAddToCart)
    }

    override fun onBindViewHolder(holder: FeaturedProductAdapter.ViewHolder, position: Int) {
        val item = getItem(position)
        return holder.bind(item)
    }


    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ProductItems>(){
            override fun areItemsTheSame(
                oldItem: ProductItems,
                newItem: ProductItems
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ProductItems,
                newItem: ProductItems
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}