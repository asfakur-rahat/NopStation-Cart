package com.test.nopstation_cart.models.cart


import com.google.gson.annotations.SerializedName

data class BillingAddress(
    @SerializedName("Address1")
    val address1: Any?,
    @SerializedName("Address2")
    val address2: Any?,
    @SerializedName("AvailableCountries")
    val availableCountries: List<Any>,
    @SerializedName("AvailableStates")
    val availableStates: List<Any>,
    @SerializedName("City")
    val city: Any?,
    @SerializedName("CityEnabled")
    val cityEnabled: Boolean,
    @SerializedName("CityRequired")
    val cityRequired: Boolean,
    @SerializedName("Company")
    val company: Any?,
    @SerializedName("CompanyEnabled")
    val companyEnabled: Boolean,
    @SerializedName("CompanyRequired")
    val companyRequired: Boolean,
    @SerializedName("CountryEnabled")
    val countryEnabled: Boolean,
    @SerializedName("CountryId")
    val countryId: Any?,
    @SerializedName("CountryName")
    val countryName: Any?,
    @SerializedName("County")
    val county: Any?,
    @SerializedName("CountyEnabled")
    val countyEnabled: Boolean,
    @SerializedName("CountyRequired")
    val countyRequired: Boolean,
    @SerializedName("CustomAddressAttributes")
    val customAddressAttributes: List<Any>,
    @SerializedName("CustomProperties")
    val customProperties: CustomPropertiesXX,
    @SerializedName("Email")
    val email: Any?,
    @SerializedName("FaxEnabled")
    val faxEnabled: Boolean,
    @SerializedName("FaxNumber")
    val faxNumber: Any?,
    @SerializedName("FaxRequired")
    val faxRequired: Boolean,
    @SerializedName("FirstName")
    val firstName: Any?,
    @SerializedName("FormattedCustomAddressAttributes")
    val formattedCustomAddressAttributes: Any?,
    @SerializedName("Id")
    val id: Int,
    @SerializedName("LastName")
    val lastName: Any?,
    @SerializedName("PhoneEnabled")
    val phoneEnabled: Boolean,
    @SerializedName("PhoneNumber")
    val phoneNumber: Any?,
    @SerializedName("PhoneRequired")
    val phoneRequired: Boolean,
    @SerializedName("StateProvinceEnabled")
    val stateProvinceEnabled: Boolean,
    @SerializedName("StateProvinceId")
    val stateProvinceId: Any?,
    @SerializedName("StateProvinceName")
    val stateProvinceName: Any?,
    @SerializedName("StreetAddress2Enabled")
    val streetAddress2Enabled: Boolean,
    @SerializedName("StreetAddress2Required")
    val streetAddress2Required: Boolean,
    @SerializedName("StreetAddressEnabled")
    val streetAddressEnabled: Boolean,
    @SerializedName("StreetAddressRequired")
    val streetAddressRequired: Boolean,
    @SerializedName("ZipPostalCode")
    val zipPostalCode: Any?,
    @SerializedName("ZipPostalCodeEnabled")
    val zipPostalCodeEnabled: Boolean,
    @SerializedName("ZipPostalCodeRequired")
    val zipPostalCodeRequired: Boolean
)