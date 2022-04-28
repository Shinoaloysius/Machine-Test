package com.shino.mymachinetest.di

import com.shino.mymachinetest.core.data.source.remote.CategoriesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit


@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    fun provideCategorieService(retrofit: Retrofit): CategoriesService {
        return retrofit.create(CategoriesService::class.java)
    }

}