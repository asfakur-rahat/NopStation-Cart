package com.test.nopstation_cart.db.dbmodel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Banner")
data class Banner(
    @PrimaryKey(autoGenerate = true)
    val id: Int
)