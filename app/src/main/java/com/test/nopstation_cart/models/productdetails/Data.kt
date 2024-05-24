package com.test.nopstation_cart.models.productdetails


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("AddToCart")
    val addToCart: AddToCart,
    @SerializedName("AllowAddingOnlyExistingAttributeCombinations")
    val allowAddingOnlyExistingAttributeCombinations: Boolean,
    @SerializedName("AssociatedProducts")
    val associatedProducts: List<Any>,
    @SerializedName("AvailableEndDate")
    val availableEndDate: Any,
    @SerializedName("Breadcrumb")
    val breadcrumb: Breadcrumb,
    @SerializedName("CompareProductsEnabled")
    val compareProductsEnabled: Boolean,
    @SerializedName("CurrentStoreName")
    val currentStoreName: String,
    @SerializedName("CustomProperties")
    val customProperties: CustomPropertiesXXX,
    @SerializedName("DefaultPictureModel")
    val defaultPictureModel: DefaultPictureModel,
    @SerializedName("DefaultPictureZoomEnabled")
    val defaultPictureZoomEnabled: Boolean,
    @SerializedName("DeliveryDate")
    val deliveryDate: Any,
    @SerializedName("DisplayBackInStockSubscription")
    val displayBackInStockSubscription: Boolean,
    @SerializedName("DisplayDiscontinuedMessage")
    val displayDiscontinuedMessage: Boolean,
    @SerializedName("EmailAFriendEnabled")
    val emailAFriendEnabled: Boolean,
    @SerializedName("FreeShippingNotificationEnabled")
    val freeShippingNotificationEnabled: Boolean,
    @SerializedName("FullDescription")
    val fullDescription: String,
    @SerializedName("GiftCard")
    val giftCard: GiftCard,
    @SerializedName("Gtin")
    val gtin: Any,
    @SerializedName("HasSampleDownload")
    val hasSampleDownload: Boolean,
    @SerializedName("Id")
    val id: Int,
    @SerializedName("InStock")
    val inStock: Boolean,
    @SerializedName("IsFreeShipping")
    val isFreeShipping: Boolean,
    @SerializedName("IsRental")
    val isRental: Boolean,
    @SerializedName("IsShipEnabled")
    val isShipEnabled: Boolean,
    @SerializedName("ManageInventoryMethod")
    val manageInventoryMethod: Int,
    @SerializedName("ManufacturerPartNumber")
    val manufacturerPartNumber: Any,
    @SerializedName("MetaDescription")
    val metaDescription: String,
    @SerializedName("MetaKeywords")
    val metaKeywords: Any,
    @SerializedName("MetaTitle")
    val metaTitle: Any,
    @SerializedName("Name")
    val name: String,
    @SerializedName("PageShareCode")
    val pageShareCode: String,
    @SerializedName("PictureModels")
    val pictureModels: List<PictureModel>,
    @SerializedName("ProductAttributes")
    val productAttributes: List<Any>,
    @SerializedName("ProductEstimateShipping")
    val productEstimateShipping: ProductEstimateShipping,
    @SerializedName("ProductManufacturers")
    val productManufacturers: List<Any>,
    @SerializedName("ProductPrice")
    val productPrice: ProductPrice,
    @SerializedName("ProductReviewOverview")
    val productReviewOverview: ProductReviewOverview,
    @SerializedName("ProductSpecificationModel")
    val productSpecificationModel: ProductSpecificationModel,
    @SerializedName("ProductTags")
    val productTags: List<Any>,
    @SerializedName("ProductType")
    val productType: Int,
    @SerializedName("RentalEndDate")
    val rentalEndDate: Any,
    @SerializedName("RentalStartDate")
    val rentalStartDate: Any,
    @SerializedName("SeName")
    val seName: String,
    @SerializedName("ShortDescription")
    val shortDescription: String,
    @SerializedName("ShowGtin")
    val showGtin: Boolean,
    @SerializedName("ShowManufacturerPartNumber")
    val showManufacturerPartNumber: Boolean,
    @SerializedName("ShowSku")
    val showSku: Boolean,
    @SerializedName("ShowVendor")
    val showVendor: Boolean,
    @SerializedName("Sku")
    val sku: String,
    @SerializedName("StockAvailability")
    val stockAvailability: String,
    @SerializedName("TierPrices")
    val tierPrices: List<Any>,
    @SerializedName("VendorModel")
    val vendorModel: VendorModel,
    @SerializedName("VideoModels")
    val videoModels: List<Any>,
    @SerializedName("VisibleIndividually")
    val visibleIndividually: Boolean
)