package com.test.nopstation_cart.demodata

import com.test.nopstation_cart.R
import com.test.nopstation_cart.models.OurCategoryItem
import com.test.nopstation_cart.models.ProductItem

class ProvideDemoData {

    fun provideCategoryitems():List<OurCategoryItem>{
        val categoryList = mutableListOf<OurCategoryItem>()
        categoryList.add(
            OurCategoryItem(
                0,
                "Foods",
                R.drawable.food
            )
        )
        categoryList.add(
            OurCategoryItem(
                1,
                "Watch",
                R.drawable.item_sample
            )
        )
        categoryList.add(
            OurCategoryItem(
                2,
                "Phones",
                R.drawable.phone
            )
        )
        categoryList.add(
            OurCategoryItem(
                3,
                "Furniture",
                R.drawable.chair
            )
        )
        categoryList.add(
            OurCategoryItem(
                4,
                "Fashion",
                R.drawable.fashion
            )
        )
        return categoryList
    }
    fun provideBestSellingItems(): List<ProductItem> {

        val productList = mutableListOf<ProductItem>()
        val p1 = ProductItem(id = 10001, productName = "California Oranges 8 Pcs", productImage = R.drawable.example_item2, productPrice = 15.00, productRating = 3.0f)
        val p2 = ProductItem(10002, productName = "Mens Wrist Watch", productImage = R.drawable.item_sample, productPrice = 25.00, productRating = 3.5f)
        val p3 = ProductItem(10003, productName = "Beef Sirloin Stake", productImage = R.drawable.example_item, productPrice = 35.00, productRating = 4.0f)
        val p4 = ProductItem(10004, productName = "Fresh River Catch Salmon", productImage = R.drawable.salmon, productPrice = 20.00, productRating = 4.5f)

        productList.add(p1)
        productList.add(p2)
        productList.add(p3)
        productList.add(p4)

        return productList
    }
    fun provideFeaturedItems(): List<ProductItem> {
        val productList = mutableListOf<ProductItem>()
        val p1 = ProductItem(0, "Black Sofa", R.drawable.furniture2, 20.50, 4.5f)
        val p2 = ProductItem(1, "Blue Sofa", R.drawable.furniture3, 25.50, 3.0f)
        val p3 = ProductItem(2, "Brown Sofa", R.drawable.furniture1, 30.50, 5.0f)
        val p4 = ProductItem(3, "California Orange", R.drawable.example_item2, 15.00, 3.0f)
        val p5 = ProductItem(4, "Black Sofa", R.drawable.furniture2, 20.50, 4.0f)

        productList.add(p1)
        productList.add(p2)
        productList.add(p3)
        productList.add(p4)
        productList.add(p5)

        return productList
    }

    fun provideFurnitureCollection(): List<ProductItem> {
        val furnitureList = mutableListOf<ProductItem>()

        val f1 = ProductItem(5001, "White Working Desk", R.drawable.furniture_collection_1, 50.50, 4.0f)
        val f2 = ProductItem(5002, "Black Working Desk", R.drawable.furniture_collection_2, 45.25, 3.0f)
        val f3 = ProductItem(5003, "Brown Luxurious Couch", R.drawable.furniture_collection_3, 30.50, 4.5f)
        val f4 = ProductItem(5004, "White Sofa", R.drawable.furniture_collection_4, 25.50, 4.5f)
        val f5 = ProductItem(5005, "Lorem ipsum Opam epee", R.drawable.furniture_collection_5, 25.50, 2.5f)

        furnitureList.add(f1)
        furnitureList.add(f2)
        furnitureList.add(f3)
        furnitureList.add(f4)
        furnitureList.add(f5)

        return furnitureList

    }

}