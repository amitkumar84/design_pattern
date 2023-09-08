package com.amitsah.designpatterns.structural.decorator

interface Coffee {
    fun cost(): Double
    fun description(): String
}

class SimpleCoffee : Coffee {
    override fun cost(): Double {
        return 5.0
    }

    override fun description(): String {
        return "Simple Coffee"
    }
}

abstract class CoffeeDecorator(private var decoratedCoffee: Coffee) : Coffee {
    override fun cost(): Double {
        return decoratedCoffee.cost()
    }

    override fun description(): String {
        return decoratedCoffee.description()
    }
}

class MilkDecorator(decoratedCoffee: Coffee?) : CoffeeDecorator(decoratedCoffee!!) {
    override fun cost(): Double {
        return super.cost() + 2
    }

    override fun description(): String {
        return super.description() + ", with Milk"
    }
}

class SugarDecorator(decoratedCoffee: Coffee?) : CoffeeDecorator(decoratedCoffee!!) {
    override fun cost(): Double {
        return super.cost() + 1
    }

    override fun description(): String {
        return super.description() + ", with Sugar"
    }
}

fun main() {
    val simpleCoffee: Coffee = SimpleCoffee()
    println("Cost: " + simpleCoffee.cost() + ", Description: " + simpleCoffee.description())

    val milkCoffee: Coffee = MilkDecorator(simpleCoffee)
    println("Cost: " + milkCoffee.cost() + ", Description: " + milkCoffee.description())

    val sugarMilkCoffee: Coffee = SugarDecorator(milkCoffee)
    println("Cost: " + sugarMilkCoffee.cost() + ", Description: " + sugarMilkCoffee.description())

    val sugarMilkCoffee2: Coffee = SugarDecorator(MilkDecorator(SimpleCoffee()))
    println("Cost: " + sugarMilkCoffee2.cost() + ", Description: " + sugarMilkCoffee2.description())
}