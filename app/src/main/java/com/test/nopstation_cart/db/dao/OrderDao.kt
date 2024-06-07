package com.test.nopstation_cart.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.nopstation_cart.db.dbmodel.OrderEntity


@Dao
interface OrderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveOrder(order: OrderEntity)

    @Query("SELECT * FROM orders WHERE token = :token")
    suspend fun getAllOrders(token: String): List<OrderEntity>
}