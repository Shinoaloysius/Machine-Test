package com.shino.mymachinetest.categories.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.shino.mymachinetest.categories.domain.model.CategoryModel
import com.shino.mymachinetest.databinding.ItemCatagoryBinding

class CategoryAdapter(
    private val productClickListener: ChildCategoryAdapter.ProductClickListener,
    private val glide: RequestManager
) :
    ListAdapter<CategoryModel, RecyclerView.ViewHolder>(CategoryDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemCatagoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CategoryViewHolder).bind(getItem(position))
    }

    inner class CategoryViewHolder(private val binding: ItemCatagoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: CategoryModel) {
            binding.apply {
                val childCategoryAdapter = ChildCategoryAdapter(productClickListener, glide)
                childRecyclerView.adapter = childCategoryAdapter
                childCategoryAdapter.submitList(category.products)
                titleText.text = category.title
                root.setOnClickListener {
                    childRecyclerView.isVisible = !childRecyclerView.isVisible
                }
            }

        }
    }
}

object CategoryDiff : DiffUtil.ItemCallback<CategoryModel>() {
    override fun areItemsTheSame(oldItem: CategoryModel, newItem: CategoryModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CategoryModel, newItem: CategoryModel): Boolean {
        return oldItem == newItem
    }
}