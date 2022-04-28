package com.shino.mymachinetest.categories.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shino.mymachinetest.common.Resource
import com.shino.mymachinetest.core.domain.CategoriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val categoriesRepository: CategoriesRepository
) : ViewModel() {

    private val _category = MutableStateFlow<CategoriesUI>(CategoriesUI.Idle)
    val category = _category.asStateFlow()

    init {
        getCategories()
    }

    private fun getCategories() = viewModelScope.launch {
        categoriesRepository.getCategories().collect{
            when (it) {
                is Resource.Loading -> _category.emit(CategoriesUI.Loading)
                is Resource.Success -> _category.emit(CategoriesUI.CategorieSuccess(it.value))
                is Resource.Error -> _category.emit(CategoriesUI.Error(it.error))
            }
        }
    }
}