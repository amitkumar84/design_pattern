package com.amitsah.designpatterns.low_level_design.vending_machine


object VendingMachine {
    var vendingMachineState: State = IdleState()
    var inventory: Inventory = Inventory(10)
    var coinList: MutableList<Coin> = ArrayList()

    fun fillUpInventory() {
        val slots = inventory.itemShelfs
        for (i in slots.indices) {
            val newItem = Item()
            if (i < 3) {
                newItem.type = ItemType.COKE
                newItem.price = 12
            } else if (i < 5) {
                newItem.type = ItemType.PEPSI
                newItem.price = 9
            } else if (i < 7) {
                newItem.type = ItemType.JUICE
                newItem.price = 13
            } else if (i < 10) {
                newItem.type = ItemType.SODA
                newItem.price = 7
            }
            slots[i]?.item = newItem
            slots[i]?.isSoldOut = false
        }
    }

    fun displayInventory() {
        val slots = inventory.itemShelfs
        for (i in slots.indices) {
            println(
                "Product Code: " + slots[i]?.code +
                        " Item Name: " + slots[i]?.item?.type?.name +
                        " Price: " + slots[i]?.item?.price +
                        " in Stock: " + if (slots[i]?.isSoldOut == false) "Yes" else "No"
            )
        }
    }
}
