package com.amitsah.designpatterns.structural.proxy

interface Image {
    fun display()
}

class RealImage(private val fileName: String) : Image {
    init {
        loadFromDisk(fileName)
    }

    override fun display() {
        println("Displaying $fileName")
    }

    private fun loadFromDisk(fileName: String) {
        println("Loading $fileName")
    }
}

 class ProxyImage(private val fileName: String) : Image {
    private var realImage: RealImage? = null
    override fun display() {
        if (realImage == null) {
            realImage = RealImage(fileName)
        }
        realImage?.display()
    }
}

fun main() {
    val image: Image = ProxyImage("image.jpg")

    image.display()
    image.display()
}