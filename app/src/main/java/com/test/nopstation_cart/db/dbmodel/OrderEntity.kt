package com.test.nopstation_cart.db.dbmodel

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.test.nopstation_cart.models.cart.Item

@Entity(tableName = "orders")
data class OrderEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val token: String = "",
    val email: String,
    val orderId: String,
    val date: String,
    val status: String,
    val item: List<Item>,
    val totalPrice: String = "$0.00"
)
