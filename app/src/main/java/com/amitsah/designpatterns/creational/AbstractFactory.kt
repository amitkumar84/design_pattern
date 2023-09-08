package com.amitsah.designpatterns.creational



interface FurnitureFactory {
    fun createDoor(): Door
    fun createWindow(): Window
}

interface Door {
    fun description()
}

class WoodenDoor : Door {
    override fun description() {
        println("I am a wooden door")
    }

}

class IronDoor : Door {
    override fun description() {
        println("I am an iron door")
    }

}

interface Window {
    fun description()
}

// Concrete Product B1
class WoodenWindow : Window {
    override fun description() {
        println("I am a wooden window")
    }

}

// Concrete Product B2
class IronWindow : Window {
    override fun description() {
        println("I am an iron window")
    }
}

class WoodenFurnitureFactory : FurnitureFactory {
    override fun createDoor(): Door {
        return WoodenDoor()
    }

    override fun createWindow(): Window {
        return WoodenWindow()
    }
}

class IronFurnitureFactory : FurnitureFactory {
    override fun createDoor(): Door {
        return IronDoor()
    }

    override fun createWindow(): Window {
        return IronWindow()
    }
}

fun main() {
    val woodenFactory: FurnitureFactory = WoodenFurnitureFactory()
    val woodenDoor = woodenFactory.createDoor()
    val woodenWindow = woodenFactory.createWindow()

    woodenDoor.description()
    woodenWindow.description()

    val ironFactory: FurnitureFactory = IronFurnitureFactory()
    val ironDoor = ironFactory.createDoor()
    val ironWindow = ironFactory.createWindow()

    ironDoor.description()
    ironWindow.description()
}
