package com.test.nopstation_cart.models.banner


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("IsEnabled")
    val isEnabled: Boolean,
    @SerializedName("Sliders")
    val sliders: List<Slider>
)