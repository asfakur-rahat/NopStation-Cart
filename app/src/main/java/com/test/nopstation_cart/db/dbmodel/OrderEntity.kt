package com.test.nopstation_cart.db.dbmodel

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.test.nopstation_cart.models.cart.Item

@Entity(tableName = "orders")
data class OrderEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val orderId: String,
    val item: List<Item>
)
