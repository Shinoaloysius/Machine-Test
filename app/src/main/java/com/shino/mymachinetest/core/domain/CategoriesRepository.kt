package com.shino.mymachinetest.core.domain

import com.shino.mymachinetest.categories.domain.model.CategoryModel
import com.shino.mymachinetest.common.Resource
import kotlinx.coroutines.flow.Flow

interface CategoriesRepository {
 fun getCategories():Flow<Resource<List<CategoryModel>>>
}