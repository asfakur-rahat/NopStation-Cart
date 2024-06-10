package com.test.nopstation_cart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.test.nopstation_cart.databinding.ItemProductBinding
import com.test.nopstation_cart.models.ProductItem
import com.test.nopstation_cart.models.category.Product

class ProductListAdapter(
    private val onClick: (Product) -> Unit,
    private val onAddtoCart: (Product) -> Unit
): ListAdapter<Product, ProductListAdapter.ViewHolder>(DIFF_CALLBACK) {
    class ViewHolder(
        private val binding: ItemProductBinding,
        private val onClick: (Product) -> Unit,
        private val onAddtoCart: (Product) -> Unit
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.tvProductName.text = product.name
            binding.ivProductImage.load(product.pictureModels[0].imageUrl)
            binding.rbProductRating.rating =
                if (product.reviewOverviewModel.totalReviews == 0) 0f else (product.reviewOverviewModel.ratingSum / product.reviewOverviewModel.totalReviews).toFloat()
            binding.tvProductPrice.text = product.productPrice.price ?: "$0.00"

            binding.root.setOnClickListener {
                onClick(product)
            }
            binding.ibAddToCart.setOnClickListener {
                onAddtoCart(product)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemProductBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding, onClick, onAddtoCart)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Product>(){
            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem == newItem
            }

        }
    }

}

