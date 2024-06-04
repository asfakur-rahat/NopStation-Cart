package com.test.nopstation_cart.models.banner


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "Banner")
data class Slider(
    @SerializedName("EntityId")
    val entityId: Int,
    @SerializedName("Id")
    @PrimaryKey
    val id: Int,
    @SerializedName("ImageUrl")
    val imageUrl: String,
    @SerializedName("SliderType")
    val sliderType: Int
)