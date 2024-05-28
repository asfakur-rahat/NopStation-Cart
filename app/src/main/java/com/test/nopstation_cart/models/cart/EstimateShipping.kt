package com.test.nopstation_cart.models.cart


import com.google.gson.annotations.SerializedName

data class EstimateShipping(
    @SerializedName("AvailableCountries")
    val availableCountries: List<AvailableCountry>,
    @SerializedName("AvailableStates")
    val availableStates: List<AvailableState>,
    @SerializedName("City")
    val city: Any?,
    @SerializedName("CountryId")
    val countryId: Any?,
    @SerializedName("CustomProperties")
    val customProperties: CustomPropertiesXX,
    @SerializedName("Enabled")
    val enabled: Boolean,
    @SerializedName("RequestDelay")
    val requestDelay: Int,
    @SerializedName("StateProvinceId")
    val stateProvinceId: Any?,
    @SerializedName("UseCity")
    val useCity: Boolean,
    @SerializedName("ZipPostalCode")
    val zipPostalCode: String
)