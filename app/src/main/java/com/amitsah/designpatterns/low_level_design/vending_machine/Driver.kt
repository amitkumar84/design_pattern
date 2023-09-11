package com.amitsah.designpatterns.low_level_design.vending_machine

fun main() {
    println("filling up the inventory")
    println("")
    VendingMachine.fillUpInventory()
    VendingMachine.displayInventory()

    println("")
    println("clicking on insert coin button")
    println("")

    var vendingState = VendingMachine.vendingMachineState
    vendingState.clickOnInsertCoinButton(VendingMachine)

    vendingState = VendingMachine.vendingMachineState
    vendingState.insertCoin(VendingMachine, Coin.NICKEL)
    vendingState.insertCoin(VendingMachine, Coin.QUARTER)

    println("")
    println("clicking on product selection button")
    println("")
    vendingState.clickOnStartProductSelectionButton(VendingMachine)

    vendingState = VendingMachine.vendingMachineState
    vendingState.chooseProduct(VendingMachine, 102)
    VendingMachine.displayInventory()
}