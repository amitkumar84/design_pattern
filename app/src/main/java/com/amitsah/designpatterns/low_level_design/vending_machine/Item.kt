package com.amitsah.designpatterns.low_level_design.vending_machine

data class Item(
    var type: ItemType? = null,
    var price: Int = 0
)
