package com.amitsah.designpatterns.low_level_design.snanke_and_ladder

class Dice {
    fun rollDice(): Int {
        return (Math.random() * (6 - 1)).toInt() + 1
    }
}