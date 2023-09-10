package com.amitsah.designpatterns.low_level_design.snanke_and_ladder

import java.util.LinkedList
import java.util.Queue


fun main(){
    val dice = Dice()

    val p1 = Player("Amit", 1)
    val p2 = Player("Sumit", 2)
    val allPlayers: Queue<Player> = LinkedList()
    allPlayers.offer(p1)
    allPlayers.offer(p2)

    val snake1 = Jumper(10, 2)
    val snake2 = Jumper(99, 12)
    val snake3 = Jumper(85, 37)
    val snakes: MutableList<Jumper> = ArrayList()
    snakes.add(snake1)
    snakes.add(snake2)
    snakes.add(snake3)

    val ladder1 = Jumper(5, 25)
    val ladder2 = Jumper(40, 89)
    val ladder3 = Jumper(7, 99)
    val ladders: MutableList<Jumper> = ArrayList()
    ladders.add(ladder1)
    ladders.add(ladder2)
    ladders.add(ladder3)

    val playersCurrentPosition: MutableMap<String, Int> = HashMap()
    playersCurrentPosition["Amit"] = 0
    playersCurrentPosition["Sumit"] = 0
    val gb = Board(dice, allPlayers, snakes, ladders, playersCurrentPosition, 100)
    gb.startGame()
}