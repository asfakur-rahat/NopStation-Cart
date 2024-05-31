package com.test.nopstation_cart.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.test.nopstation_cart.db.dao.BannerDao
import com.test.nopstation_cart.db.dao.CategoryDao
import com.test.nopstation_cart.db.dao.FeaturedDao
import com.test.nopstation_cart.db.dbmodel.Banner
import com.test.nopstation_cart.db.dbmodel.Category
import com.test.nopstation_cart.db.dbmodel.Featured

@Database(
    entities = [Banner::class, Category::class, Featured::class],
    version = 1, exportSchema = false
)
@TypeConverters()
abstract class AppDatabase: RoomDatabase(){
    abstract fun bannerDao(): BannerDao
    abstract fun categoryDao(): CategoryDao
    abstract fun featuredDao(): FeaturedDao

    companion object{
        operator fun invoke(context: Context) = buildDatabase(context)
        private fun buildDatabase(context: Context) : AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "Nopstation.db"
            ).build()
        }
    }
}