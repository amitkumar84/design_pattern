package com.amitsah.designpatterns.low_level_design.vending_machine

data class ItemShelf(
    var code: Int = 0,
    var item: Item? = null,
    var isSoldOut: Boolean = false
)