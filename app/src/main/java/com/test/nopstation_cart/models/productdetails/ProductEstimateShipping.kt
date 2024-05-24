package com.test.nopstation_cart.models.productdetails


import com.google.gson.annotations.SerializedName

data class ProductEstimateShipping(
    @SerializedName("AvailableCountries")
    val availableCountries: List<AvailableCountry>,
    @SerializedName("AvailableStates")
    val availableStates: List<AvailableState>,
    @SerializedName("City")
    val city: Any,
    @SerializedName("CountryId")
    val countryId: Any,
    @SerializedName("CustomProperties")
    val customProperties: CustomPropertiesXXX,
    @SerializedName("Enabled")
    val enabled: Boolean,
    @SerializedName("ProductId")
    val productId: Int,
    @SerializedName("RequestDelay")
    val requestDelay: Int,
    @SerializedName("StateProvinceId")
    val stateProvinceId: Any,
    @SerializedName("UseCity")
    val useCity: Boolean,
    @SerializedName("ZipPostalCode")
    val zipPostalCode: String
)