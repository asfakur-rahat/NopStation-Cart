package com.test.nopstation_cart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.test.nopstation_cart.databinding.CartItemBinding
import com.test.nopstation_cart.models.CartItem
import com.test.nopstation_cart.models.cart.Item

class CartAdapter(
    private val onClick: (Item) -> Unit,
    private val onUpdate: (Item, quantity: Int) -> Unit
): ListAdapter<Item, CartAdapter.ViewHolder>(DIFF_CALLBACK){
    class ViewHolder(
        private val binding: CartItemBinding,
        private val onClick: (Item) -> Unit,
        private val onUpdate: (Item, quantity: Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.ivProductImage.load(item.picture.imageUrl)
            binding.tvProductName.text = item.productName
            binding.tvProductDiscountedPrice.text = item.unitPrice
            binding.tvProductQuantity.text = item.quantity.toString()

            binding.ivCancleOrder.setOnClickListener {
                onClick(item)
            }

            binding.tvQuantityPlus.setOnClickListener {
                binding.tvProductQuantity.text = (binding.tvProductQuantity.text.toString().toInt() + 1).toString()

                onUpdate(item, binding.tvProductQuantity.text.toString().toInt())
            }
            binding.tvQuantityMinus.setOnClickListener {
                binding.tvProductQuantity.text = (binding.tvProductQuantity.text.toString().toInt() - 1).toString()

                if(binding.tvProductQuantity.text.toString().toInt() == 0){
                    onClick(item)
                }else{
                    onUpdate(item, binding.tvProductQuantity.text.toString().toInt())
                }

            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CartItemBinding.inflate(layoutInflater,parent,false)
        return ViewHolder(binding, onClick, onUpdate)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }


    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Item>(){
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }

        }
    }
}