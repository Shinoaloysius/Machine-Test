package com.shino.mymachinetest.products.pesentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.shino.mymachinetest.R
import com.shino.mymachinetest.databinding.FragmentProductBinding
import com.shino.mymachinetest.products.domain.model.ProductModel

class ProductFragment : Fragment(R.layout.fragment_product) {

    private lateinit var binding: FragmentProductBinding
    private val viewModel by viewModels<ProductViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductBinding.bind(view)
        binding.toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        observeProduct()
    }

    private fun observeProduct() {
        viewModel.product.observe(viewLifecycleOwner) { product ->
            setProductInfo(product)
        }
    }

    private fun setProductInfo(product: ProductModel) = with(binding) {
        Glide.with(this@ProductFragment).load(product.imageUrl).into(productPic)
        productTitle.text = product.title
        productPrice.text = getString(R.string.rupee, product.price)
        productDescription.text = product.description
    }
}