package com.amitsah.designpatterns.low_level_design.vending_machine

interface State {
    @Throws(Exception::class)
    fun clickOnInsertCoinButton(machine: VendingMachine)

    @Throws(Exception::class)
    fun clickOnStartProductSelectionButton(machine: VendingMachine)

    @Throws(Exception::class)
    fun insertCoin(machine: VendingMachine, coin: Coin)

    @Throws(Exception::class)
    fun chooseProduct(machine: VendingMachine, codeNumber: Int)

    @Throws(Exception::class)
    fun getChange(returnChangeMoney: Int): Int

    @Throws(Exception::class)
    fun dispenseProduct(machine: VendingMachine, codeNumber: Int): Item?

    @Throws(Exception::class)
    fun refundFullMoney(machine: VendingMachine): List<Coin?>?

    @Throws(Exception::class)
    fun updateInventory(machine: VendingMachine, item: Item, codeNumber: Int)
}

class IdleState : State {
    constructor() {
        println("Currently Vending machine is in IdleState")
    }

    constructor(machine: VendingMachine) {
        println("Currently Vending machine is in IdleState")
        machine.coinList = mutableListOf()
    }

    @Throws(java.lang.Exception::class)
    override fun clickOnInsertCoinButton(machine: VendingMachine) {
        machine.vendingMachineState = HasMoneyState()
    }

    @Throws(java.lang.Exception::class)
    override fun clickOnStartProductSelectionButton(machine: VendingMachine) {
        throw java.lang.Exception("first you need to click on insert coin button")
    }

    @Throws(java.lang.Exception::class)
    override fun insertCoin(machine: VendingMachine, coin: Coin) {
        throw java.lang.Exception("you can not insert Coin in idle state")
    }

    @Throws(java.lang.Exception::class)
    override fun chooseProduct(machine: VendingMachine, codeNumber: Int) {
        throw java.lang.Exception("you can not choose Product in idle state")
    }

    @Throws(java.lang.Exception::class)
    override fun getChange(returnChangeMoney: Int): Int {
        throw java.lang.Exception("you can not get change in idle state")
    }

    @Throws(java.lang.Exception::class)
    override fun refundFullMoney(machine: VendingMachine): List<Coin> {
        throw java.lang.Exception("you can not get refunded in idle state")
    }

    @Throws(java.lang.Exception::class)
    override fun dispenseProduct(machine: VendingMachine, codeNumber: Int): Item {
        throw java.lang.Exception("product can not be dispensed idle state")
    }

    @Throws(java.lang.Exception::class)
    override fun updateInventory(machine: VendingMachine, item: Item, codeNumber: Int) {
        machine.inventory.addItem(item, codeNumber)
    }
}

class SelectionState : State {
    init {
        println("Currently Vending machine is in SelectionState")
    }

    @Throws(java.lang.Exception::class)
    override fun clickOnInsertCoinButton(machine: VendingMachine) {
        throw java.lang.Exception("you can not click on insert coin button in Selection state")
    }

    @Throws(java.lang.Exception::class)
    override fun clickOnStartProductSelectionButton(machine: VendingMachine) {
        return
    }

    @Throws(java.lang.Exception::class)
    override fun insertCoin(machine: VendingMachine, coin: Coin) {
        throw java.lang.Exception("you can not insert Coin in selection state")
    }

    @Throws(java.lang.Exception::class)
    override fun chooseProduct(machine: VendingMachine, codeNumber: Int) {

        //1. get item of this codeNumber
        val item: Item? = machine.inventory.getItem(codeNumber)
        item ?: return

        //2. total amount paid by User
        var paidByUser = 0
        for (coin in machine.coinList) {
            paidByUser = paidByUser + coin.value
        }

        //3. compare product price and amount paid by user
        if (paidByUser < item.price) {
            System.out.println("Insufficient Amount, Product you selected is for price: " + item.price + " and you paid: " + paidByUser)
            refundFullMoney(machine)
            throw java.lang.Exception("insufficient amount")
        } else if (paidByUser >= item.price) {
            if (paidByUser > item.price) {
                getChange(paidByUser - item.price)
            }
            machine.vendingMachineState = DispenseState(machine, codeNumber)
        }
    }

    @Throws(java.lang.Exception::class)
    override fun getChange(returnExtraMoney: Int): Int {
        println("Returned the change in the Coin Dispense Tray: $returnExtraMoney")
        return returnExtraMoney
    }

    @Throws(java.lang.Exception::class)
    override fun refundFullMoney(machine: VendingMachine): List<Coin> {
        println("Returned the full amount back in the Coin Dispense Tray")
        machine.vendingMachineState = IdleState(machine)
        return machine.coinList
    }

    @Throws(java.lang.Exception::class)
    override fun dispenseProduct(machine: VendingMachine, codeNumber: Int): Item {
        throw java.lang.Exception("product can not be dispensed Selection state")
    }

    @Throws(java.lang.Exception::class)
    override fun updateInventory(machine: VendingMachine, item: Item, codeNumber: Int) {
        throw java.lang.Exception("Inventory can not be updated in Selection state")
    }
}

class HasMoneyState : State {
    init {
        println("Currently Vending machine is in HasMoneyState")
    }

    @Throws(java.lang.Exception::class)
    override fun clickOnInsertCoinButton(machine: VendingMachine) {
        return
    }

    @Throws(java.lang.Exception::class)
    override fun clickOnStartProductSelectionButton(machine: VendingMachine) {
        machine.vendingMachineState = SelectionState()
    }

    @Throws(java.lang.Exception::class)
    override fun insertCoin(machine: VendingMachine, coin: Coin) {
        println("Accepted the coin")
        machine.coinList.add(coin)
    }

    @Throws(java.lang.Exception::class)
    override fun chooseProduct(machine: VendingMachine, codeNumber: Int) {
        throw java.lang.Exception("you need to click on start product selection button first")
    }

    @Throws(java.lang.Exception::class)
    override fun getChange(returnChangeMoney: Int): Int {
        throw java.lang.Exception("you can not get change in hasMoney state")
    }

    @Throws(java.lang.Exception::class)
    override fun dispenseProduct(machine: VendingMachine, codeNumber: Int): Item {
        throw java.lang.Exception("product can not be dispensed in hasMoney state")
    }

    @Throws(java.lang.Exception::class)
    override fun refundFullMoney(machine: VendingMachine): List<Coin> {
        println("Returned the full amount back in the Coin Dispense Tray")
        machine.vendingMachineState = IdleState(machine)
        return machine.coinList
    }

    @Throws(java.lang.Exception::class)
    override fun updateInventory(machine: VendingMachine, item: Item, codeNumber: Int) {
        throw java.lang.Exception("you can not update inventory in hasMoney  state")
    }
}

class DispenseState(machine: VendingMachine, codeNumber: Int) :
    State {
    init {
        println("Currently Vending machine is in DispenseState")
        dispenseProduct(machine, codeNumber)
    }

    @Throws(java.lang.Exception::class)
    override fun clickOnInsertCoinButton(machine: VendingMachine) {
        throw java.lang.Exception("insert coin button can not clicked on Dispense state")
    }

    @Throws(java.lang.Exception::class)
    override fun clickOnStartProductSelectionButton(machine: VendingMachine) {
        throw java.lang.Exception("product selection buttion can not be clicked in Dispense state")
    }

    @Throws(java.lang.Exception::class)
    override fun insertCoin(machine: VendingMachine, coin: Coin) {
        throw java.lang.Exception("coin can not be inserted in Dispense state")
    }

    @Throws(java.lang.Exception::class)
    override fun chooseProduct(machine: VendingMachine, codeNumber: Int) {
        throw java.lang.Exception("product can not be choosen in Dispense state")
    }

    @Throws(java.lang.Exception::class)
    override fun getChange(returnChangeMoney: Int): Int {
        throw java.lang.Exception("change can not returned in Dispense state")
    }

    @Throws(java.lang.Exception::class)
    override fun refundFullMoney(machine: VendingMachine): List<Coin> {
        throw java.lang.Exception("refund can not be happen in Dispense state")
    }

    @Throws(java.lang.Exception::class)
    override fun dispenseProduct(machine: VendingMachine, codeNumber: Int): Item {
        println("Product has been dispensed")
        val item: Item? = machine.inventory.getItem(codeNumber)
        if (item == null) {
            throw Exception("Item can not be null")
        }
        machine.inventory.updateSoldOutItem(codeNumber)
        machine.vendingMachineState = IdleState(machine)
        return item
    }

    @Throws(java.lang.Exception::class)
    override fun updateInventory(machine: VendingMachine, item: Item, codeNumber: Int) {
        throw java.lang.Exception("inventory can not be updated in Dispense state")
    }
}
