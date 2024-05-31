package com.test.nopstation_cart.db.dbmodel

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "featured")
data class Featured (
    @PrimaryKey
    val id: Int,
    val title: String,
    val description: String,
    val image: String
)