package com.test.nopstation_cart.db.dbmodel

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "categories")
data class Category (
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String
)