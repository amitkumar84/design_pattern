package com.amitsah.designpatterns.low_level_design.vending_machine

class Inventory internal constructor(itemCount: Int) {
    var itemShelfs: Array<ItemShelf?>

    init {
        itemShelfs = kotlin.arrayOfNulls<ItemShelf>(itemCount)
        initialiseShelf()
    }

    private fun initialiseShelf() {
        var startCode = 101
        for (i in itemShelfs.indices) {
            val space = ItemShelf()
            space.code = startCode
            space.isSoldOut = true
            itemShelfs[i] = space
            startCode++
        }
    }

    @Throws(Exception::class)
    fun addItem(item: Item, codeNumber: Int) {
        for (itemShelf in itemShelfs) {
            if (itemShelf?.code == codeNumber) {
                if (itemShelf.isSoldOut) {
                    itemShelf.item = item
                    itemShelf.isSoldOut = false
                } else {
                    throw Exception("already item is present, you can not add item here")
                }
            }
        }
    }

    @Throws(Exception::class)
    fun getItem(codeNumber: Int): Item? {
        for (itemShelf in itemShelfs) {
            if (itemShelf?.code == codeNumber) {
                return if (itemShelf.isSoldOut) {
                    throw Exception("item already sold out")
                } else {
                    itemShelf.item
                }
            }
        }
        throw Exception("Invalid Code")
    }

    fun updateSoldOutItem(codeNumber: Int) {
        for (itemShelf in itemShelfs) {
            if (itemShelf?.code == codeNumber) {
                itemShelf.isSoldOut = true
            }
        }
    }
}
