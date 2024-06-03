package com.test.nopstation_cart.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.nopstation_cart.models.featured_product.Data as Featured


@Dao
interface FeaturedDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFeaturedProducts(featuredProducts: List<Featured>)

    @Query("SELECT * FROM Featured")
    suspend fun getFeaturedProducts(): List<Featured>
}