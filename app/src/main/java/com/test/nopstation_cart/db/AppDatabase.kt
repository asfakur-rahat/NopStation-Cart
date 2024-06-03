package com.test.nopstation_cart.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.test.nopstation_cart.db.converter.Converter
import com.test.nopstation_cart.db.converter.FeaturedProductConverter
import com.test.nopstation_cart.db.dao.BannerDao
import com.test.nopstation_cart.db.dao.CategoryDao
import com.test.nopstation_cart.db.dao.FeaturedDao
import com.test.nopstation_cart.models.banner.Slider
import com.test.nopstation_cart.models.category.Data as Category
import com.test.nopstation_cart.models.featured_product.Data as Featured

@Database(
    entities = [Slider::class, Category::class,Featured::class],
    version = 1, exportSchema = false
)
@TypeConverters(Converter::class, FeaturedProductConverter::class)
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