package com.test.nopstation_cart.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.nopstation_cart.models.banner.Slider


@Dao
interface BannerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBannerList(bannerList: List<Slider>)

    @Query("SELECT * FROM banner")
    suspend fun getBannerList(): List<Slider>

}