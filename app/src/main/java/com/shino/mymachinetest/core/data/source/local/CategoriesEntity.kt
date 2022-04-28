package com.shino.mymachinetest.core.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shino.mymachinetest.categories.domain.model.CategoryModel

@Entity(tableName = "category_table")
data class CategoriesEntity(
    val productsEntity : List<ProductEntity>,
    @PrimaryKey
    val title:String
){
    fun toCategory(): CategoryModel {
        return CategoryModel(
            id = title,
            products = productsEntity.map { it.toProduct() },
            title = title
        )
    }

}
