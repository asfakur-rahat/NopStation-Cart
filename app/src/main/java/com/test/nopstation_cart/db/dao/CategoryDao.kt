package com.test.nopstation_cart.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.nopstation_cart.models.category.Data

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: List<Data>)

    @Query("SELECT * FROM category")
    suspend fun getCategory(): List<Data>
}