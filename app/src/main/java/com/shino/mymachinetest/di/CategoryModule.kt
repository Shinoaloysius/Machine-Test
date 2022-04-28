package com.shino.mymachinetest.di

import com.shino.mymachinetest.core.data.repository.CategorisRepositoryImpl
import com.shino.mymachinetest.core.data.source.local.CategoriesDao
import com.shino.mymachinetest.core.data.source.remote.CategoriesService
import com.shino.mymachinetest.core.domain.CategoriesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object CategoryModule {

    @Provides
    fun provideCategoryRepository(categoriesService: CategoriesService,categoriesDao: CategoriesDao): CategoriesRepository {
        return CategorisRepositoryImpl(categoriesService,categoriesDao)
    }
}