package com.amitsah.designpatterns.low_level_design.snanke_and_ladder

import java.util.Queue


class Board(
    private val dice: Dice,
    private val nextTurn: Queue<Player>,
    private val snakes: List<Jumper>,
    private val ladders: List<Jumper>,
    private val playersCurrentPosition: MutableMap<String, Int>,
    private val boardSize: Int
) {
    fun startGame() {
        while (nextTurn.size > 1) {
            val player = nextTurn.poll() ?: return
            val currentPosition = playersCurrentPosition[player.name] ?: return

            val diceValue = dice.rollDice()
            val nextCell = currentPosition.plus(diceValue)
            if (nextCell > boardSize) nextTurn.offer(player) else if (nextCell == boardSize) {
                println(player.name + " won the game")
            } else {
                var nextPosition = nextCell

                for (snake in snakes) {
                    if (snake.startPoint == nextCell) {
                        nextPosition = snake.endPoint
                        if (nextPosition != nextCell) println(player.name + " Bitten by Snake present at: " + nextCell)
                    }
                }

                for (ladder in ladders) {
                    if (ladder.startPoint == nextCell) {
                        nextPosition = ladder.endPoint
                        if (nextPosition != nextCell) println(player.name + " Got ladder present at: " + nextCell)
                    }
                }

                if (nextPosition == boardSize) {
                    println(player.name + " won the game")
                } else {
                    playersCurrentPosition[player.name] = nextPosition
                    println(player.name + " is at position " + nextPosition)
                    nextTurn.offer(player)
                }
            }
        }
    }
}