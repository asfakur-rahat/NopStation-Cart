package com.test.nopstation_cart.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.test.nopstation_cart.databinding.CartItemBinding
import com.test.nopstation_cart.models.CartItem

class CartAdapter(
    private val onClick: (CartItem) -> Unit
): ListAdapter<CartItem, CartAdapter.ViewHolder>(DIFF_CALLBACK){
    class ViewHolder(
        private val binding: CartItemBinding,
        private val onClick: (CartItem) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CartItem) {
            binding.ivProductImage.setImageResource(item.productImage)
            binding.tvProductName.text = item.productName
            binding.tvProductActualPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
//            binding.root.setOnClickListener {
//                onClick(item)
//            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CartItemBinding.inflate(layoutInflater,parent,false)
        return ViewHolder(binding, onClick)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }


    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CartItem>(){
            override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
                return oldItem == newItem
            }

        }
    }
}