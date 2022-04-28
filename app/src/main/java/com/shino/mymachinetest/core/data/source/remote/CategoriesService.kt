package com.shino.mymachinetest.core.data.source.remote

import com.shino.mymachinetest.core.data.dto.CategoriesResDto
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface CategoriesService {
@GET("v2/5ec39cba300000720039c1f6")
suspend fun getCategories():ApiResponse<CategoriesResDto>
}