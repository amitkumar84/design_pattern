package com.amitsah.designpatterns.creational

interface Shape{
    fun draw()
}

class Rectangle: Shape{
    override fun draw(){
        println("Drawing rectangle")
    }
}

class Square:Shape{
    override fun draw() {
        println("Drawing square")
    }
}

class Circle:Shape{
    override fun draw() {
        println("Drawing circle")
    }
}

class ShapeFactory{
    fun getShape(type:ShapeType):Shape{
        return when(type){
            ShapeType.RECTANGLE -> Rectangle()
            ShapeType.SQUARE -> Square()
            ShapeType.CIRCLE -> Circle()
        }
    }
}

enum class ShapeType{
    RECTANGLE, SQUARE, CIRCLE
}

fun main(){
    val shapeFactory = ShapeFactory()
    val rectangle = shapeFactory.getShape(ShapeType.RECTANGLE)
    rectangle.draw()
    val square = shapeFactory.getShape(ShapeType.SQUARE)
    square.draw()
    val circle = shapeFactory.getShape(ShapeType.CIRCLE)
    circle.draw()
}