package com.test.nopstation_cart.models

import com.test.nopstation_cart.models.category.Product

data class CategoryItem(
    val id: Int,
    val categoryName: String,
    val categoryImage: String,
    val categoryList: List<Product>
)
