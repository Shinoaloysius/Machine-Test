package com.shino.mymachinetest.core.data.repository

import com.shino.mymachinetest.categories.domain.model.CategoryModel
import com.shino.mymachinetest.common.Resource
import com.shino.mymachinetest.core.data.dto.CategoriesResDto
import com.shino.mymachinetest.core.data.source.local.CategoriesDao
import com.shino.mymachinetest.core.data.source.remote.CategoriesService
import com.shino.mymachinetest.core.domain.CategoriesRepository
import com.skydoves.sandwich.StatusCode
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategorisRepositoryImpl @Inject constructor(
    private val categoriesService: CategoriesService,
    private val categoriesDao: CategoriesDao
) : CategoriesRepository {
    override fun getCategories(): Flow<Resource<List<CategoryModel>>> = flow {
        emit(Resource.Loading)
        categoriesDao.getCategories().collect { categoryEntities ->
            if (categoryEntities.isEmpty()) {
                categoriesService.getCategories().suspendOnSuccess {
                    saveToDb(this.data)
                }.suspendOnError {
                    when (statusCode) {
                        StatusCode.InternalServerError -> emit(Resource.Error(SERVER_ERROR))
                        else -> emit(Resource.Error(INTERNET_ERROR))
                    }
                }.suspendOnException {
                    emit(Resource.Error(this.message ?: UNKNOWN_ERROR))
                }
            } else {
                val categories = categoryEntities.map { categoriesEntity ->
                    categoriesEntity.toCategory()
                }
                emit(Resource.Success(categories))

            }
        }

    }

    private suspend fun saveToDb(categoryResponseDto: CategoriesResDto){
        categoriesDao.insertCategories(categoryResponseDto.categories?.map { it.toCategoriesEntity() } ?: emptyList())
    }
}
const val SERVER_ERROR = "Internal server error"
const val UNKNOWN_ERROR = "Unknown error"
const val INTERNET_ERROR = "Error. Please check your internet connection and try again"
