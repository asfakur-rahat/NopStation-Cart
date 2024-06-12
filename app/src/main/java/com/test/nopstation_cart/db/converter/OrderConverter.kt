package com.test.nopstation_cart.db.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.test.nopstation_cart.models.cart.Item

class OrderConverter {
    @TypeConverter
    fun fromItemList(itemList: List<Item>): String {
        val type = object : TypeToken<List<Item>>() {}.type
        return Gson().toJson(itemList, type)
    }
    @TypeConverter
    fun toItemList(itemListString: String): List<Item> {
        val type = object : TypeToken<List<Item>>() {}.type
        return Gson().fromJson(itemListString, type)
    }
}