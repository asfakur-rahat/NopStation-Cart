package com.test.nopstation_cart.models.account


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("AllowCustomersToRemoveAssociations")
    val allowCustomersToRemoveAssociations: Boolean,
    @SerializedName("AllowCustomersToSetTimeZone")
    val allowCustomersToSetTimeZone: Boolean,
    @SerializedName("AllowUsersToChangeUsernames")
    val allowUsersToChangeUsernames: Boolean,
    @SerializedName("AssociatedExternalAuthRecords")
    val associatedExternalAuthRecords: List<Any>,
    @SerializedName("AvailableCountries")
    val availableCountries: List<Any>,
    @SerializedName("AvailableStates")
    val availableStates: List<Any>,
    @SerializedName("AvailableTimeZones")
    val availableTimeZones: List<AvailableTimeZone>,
    @SerializedName("CheckUsernameAvailabilityEnabled")
    val checkUsernameAvailabilityEnabled: Boolean,
    @SerializedName("City")
    val city: Any?,
    @SerializedName("CityEnabled")
    val cityEnabled: Boolean,
    @SerializedName("CityRequired")
    val cityRequired: Boolean,
    @SerializedName("Company")
    val company: String,
    @SerializedName("CompanyEnabled")
    val companyEnabled: Boolean,
    @SerializedName("CompanyRequired")
    val companyRequired: Boolean,
    @SerializedName("CountryEnabled")
    val countryEnabled: Boolean,
    @SerializedName("CountryId")
    val countryId: Int,
    @SerializedName("CountryRequired")
    val countryRequired: Boolean,
    @SerializedName("County")
    val county: Any?,
    @SerializedName("CountyEnabled")
    val countyEnabled: Boolean,
    @SerializedName("CountyRequired")
    val countyRequired: Boolean,
    @SerializedName("CustomProperties")
    val customProperties: CustomProperties,
    @SerializedName("CustomerAttributes")
    val customerAttributes: List<Any>,
    @SerializedName("DateOfBirthDay")
    val dateOfBirthDay: Int,
    @SerializedName("DateOfBirthEnabled")
    val dateOfBirthEnabled: Boolean,
    @SerializedName("DateOfBirthMonth")
    val dateOfBirthMonth: Int,
    @SerializedName("DateOfBirthRequired")
    val dateOfBirthRequired: Boolean,
    @SerializedName("DateOfBirthYear")
    val dateOfBirthYear: Int,
    @SerializedName("DisplayVatNumber")
    val displayVatNumber: Boolean,
    @SerializedName("Email")
    val email: String,
    @SerializedName("EmailToRevalidate")
    val emailToRevalidate: Any?,
    @SerializedName("Fax")
    val fax: Any?,
    @SerializedName("FaxEnabled")
    val faxEnabled: Boolean,
    @SerializedName("FaxRequired")
    val faxRequired: Boolean,
    @SerializedName("FirstName")
    val firstName: String,
    @SerializedName("FirstNameEnabled")
    val firstNameEnabled: Boolean,
    @SerializedName("FirstNameRequired")
    val firstNameRequired: Boolean,
    @SerializedName("GdprConsents")
    val gdprConsents: List<Any>,
    @SerializedName("Gender")
    val gender: String,
    @SerializedName("GenderEnabled")
    val genderEnabled: Boolean,
    @SerializedName("LastName")
    val lastName: String,
    @SerializedName("LastNameEnabled")
    val lastNameEnabled: Boolean,
    @SerializedName("LastNameRequired")
    val lastNameRequired: Boolean,
    @SerializedName("Newsletter")
    val newsletter: Boolean,
    @SerializedName("NewsletterEnabled")
    val newsletterEnabled: Boolean,
    @SerializedName("NumberOfExternalAuthenticationProviders")
    val numberOfExternalAuthenticationProviders: Int,
    @SerializedName("Phone")
    val phone: Any?,
    @SerializedName("PhoneEnabled")
    val phoneEnabled: Boolean,
    @SerializedName("PhoneRequired")
    val phoneRequired: Boolean,
    @SerializedName("Signature")
    val signature: Any?,
    @SerializedName("SignatureEnabled")
    val signatureEnabled: Boolean,
    @SerializedName("StateProvinceEnabled")
    val stateProvinceEnabled: Boolean,
    @SerializedName("StateProvinceId")
    val stateProvinceId: Int,
    @SerializedName("StateProvinceRequired")
    val stateProvinceRequired: Boolean,
    @SerializedName("StreetAddress")
    val streetAddress: Any?,
    @SerializedName("StreetAddress2")
    val streetAddress2: Any?,
    @SerializedName("StreetAddress2Enabled")
    val streetAddress2Enabled: Boolean,
    @SerializedName("StreetAddress2Required")
    val streetAddress2Required: Boolean,
    @SerializedName("StreetAddressEnabled")
    val streetAddressEnabled: Boolean,
    @SerializedName("StreetAddressRequired")
    val streetAddressRequired: Boolean,
    @SerializedName("TimeZoneId")
    val timeZoneId: Any?,
    @SerializedName("Username")
    val username: String,
    @SerializedName("UsernamesEnabled")
    val usernamesEnabled: Boolean,
    @SerializedName("VatNumber")
    val vatNumber: Any?,
    @SerializedName("VatNumberStatusNote")
    val vatNumberStatusNote: String,
    @SerializedName("ZipPostalCode")
    val zipPostalCode: Any?,
    @SerializedName("ZipPostalCodeEnabled")
    val zipPostalCodeEnabled: Boolean,
    @SerializedName("ZipPostalCodeRequired")
    val zipPostalCodeRequired: Boolean
)