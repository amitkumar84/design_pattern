package com.amitsah.designpatterns.low_level_design.parking_lot

data class Address(
    var street: String? = null,
    var block: String? = null,
    var city: String? = null,
    var state: String? = null,
    var country: String? = null
)