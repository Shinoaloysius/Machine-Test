package com.shino.mymachinetest.categories.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.shino.mymachinetest.R
import com.shino.mymachinetest.databinding.ItemProductBinding
import com.shino.mymachinetest.products.domain.model.ProductModel

class ChildCategoryAdapter  (private val productClickListener: ProductClickListener,private val glide :RequestManager) :
    ListAdapter<ProductModel, RecyclerView.ViewHolder>(ProductDiff) {

    interface ProductClickListener {
        fun onClick(product: ProductModel)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ProductViewHolder).bind(getItem(position))
    }

    inner class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ProductModel) {
            binding.apply {
                glide.load(product.imageUrl).into(productImage)
                productName.text = product.title
                productPrice.text = itemView.context.getString(R.string.rupee, product.price)
                root.setOnClickListener {
                    productClickListener.onClick(product)
                }
            }
        }
    }
}

object ProductDiff : DiffUtil.ItemCallback<ProductModel>() {
    override fun areItemsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean {
        return oldItem == newItem
    }
}