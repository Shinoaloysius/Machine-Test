package com.shino.mymachinetest.categories.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.shino.mymachinetest.R
import com.shino.mymachinetest.categories.domain.model.CategoryModel
import com.shino.mymachinetest.databinding.FragmentCategoriesBinding
import com.shino.mymachinetest.products.domain.model.ProductModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoriesFragment : Fragment(R.layout.fragment_categories),
    ChildCategoryAdapter.ProductClickListener {

    private val viewModel by viewModels<CategoriesViewModel>()
    private lateinit var binding: FragmentCategoriesBinding
    private val categoryAdapter by lazy { CategoryAdapter(this, Glide.with(this)) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCategoriesBinding.bind(view)
        binding.recyclerView.adapter = categoryAdapter
        observeUI()
    }

    private fun observeUI() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.category.collectLatest {
                    when (it) {
                        is CategoriesUI.CategorieSuccess -> handleSuccess(it.categories)
                        is CategoriesUI.Error -> handleError(it.message)
                        is CategoriesUI.Idle -> Unit
                        is CategoriesUI.Loading -> handleLoading(true)
                    }
                }
            }
        }
    }


    private fun handleLoading(showLoading: Boolean) {
        with(binding) {
            progressBar.isVisible = showLoading
            recyclerView.isVisible = !showLoading
        }
    }

    private fun handleSuccess(list: List<CategoryModel>) {
        handleLoading(false)
        categoryAdapter.submitList(list)
    }

    private fun handleError(message: String) {
        handleLoading(false)
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun onClick(product: ProductModel) {
        findNavController().navigate(
            CategoriesFragmentDirections.actionCategoriesFragmentToProductFragment(product)
        )
    }
}