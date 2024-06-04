package com.test.nopstation_cart.demodata

import com.test.nopstation_cart.R
import com.test.nopstation_cart.models.OurCategoryItem
import com.test.nopstation_cart.models.ProductItem

class ProvideDemoData {
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
    fun provideWomenHeel(): List<ProductItem>{
        val heelList = mutableListOf<ProductItem>()
        val heel1 = ProductItem(
            id = 3001,
            productName = "Women's Black Heels",
            productImage = R.drawable.womens_heel_1,
            productPrice = 30.00,
            productRating = 4.5f
        )
        val heel2 = ProductItem(
            id = 3002,
            productName = "Women's Black Heels",
            productImage = R.drawable.womens_heel_2,
            productPrice = 35.00,
            productRating = 4.0f
        )
        val heel3 = ProductItem(
            id = 3003,
            productName = "Women's Golden Heels",
            productImage = R.drawable.womens_heel_1,
            productPrice = 25.00,
            productRating = 3.5f
        )
        val heel4 = ProductItem(
            id = 3004,
            productName = "Women's White Heels",
            productImage = R.drawable.womens_heel_2,
            productPrice = 30.00,
            productRating = 3.0f
        )

        heelList.add(heel1)
        heelList.add(heel2)
        heelList.add(heel3)
        heelList.add(heel4)

        return heelList
    }

    fun provideSalmon(): List<ProductItem>{
        val salmonList = mutableListOf<ProductItem>()

        val s1 = ProductItem(4001, "Fresh River Catch Salmon", R.drawable.salmon_fish_0, 20.00, 4.5f)
        val s2 = ProductItem(4002, "Grilled River Catch Salmon", R.drawable.salmon_fish_01, 25.00, 4.0f)
        val s3 = ProductItem(4003, "Salmon With Garlic", R.drawable.salmon_fish_02, 30.00, 3.5f)
        val s4 = ProductItem(4004, "Premium Fresh Salmon", R.drawable.salmon_fish_03, 35.00, 4.0f)

        salmonList.add(s1)
        salmonList.add(s2)
        salmonList.add(s3)
        salmonList.add(s4)

        return salmonList
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

    fun getCategoryList(): List<OurCategoryItem> {
        val categoryList = mutableListOf<OurCategoryItem>()
        categoryList.add(
            OurCategoryItem(
                0, "Foods", categoryImage = R.drawable.food
            )
        )

        categoryList.add(
            OurCategoryItem(
                1, "Watch", categoryImage = R.drawable.item_sample
            )
        )

        categoryList.add(
            OurCategoryItem(
                2, "Phones", categoryImage = R.drawable.phone
            )
        )

        categoryList.add(
            OurCategoryItem(
                3, "Furniture", categoryImage = R.drawable.chair
            )
        )

        categoryList.add(
            OurCategoryItem(
                4, "Fashion", categoryImage = R.drawable.fashion
            )
        )

        categoryList.add(
            OurCategoryItem(
                5, "Electronics", categoryImage = R.drawable.phone
            )
        )
        return categoryList
    }
}