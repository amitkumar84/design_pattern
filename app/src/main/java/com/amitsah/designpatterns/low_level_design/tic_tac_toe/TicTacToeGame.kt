package com.amitsah.designpatterns.low_level_design.tic_tac_toe

import java.util.Deque
import java.util.LinkedList
import java.util.Scanner


class TicTacToeGame {
    private lateinit var gameBoard: Board
    private lateinit var players: Deque<Player>

    fun initialiseGame() {
        players = LinkedList<Player>()
        val crossPiece = PlayingPieceX()
        val player1 = Player("Player1", crossPiece)

        val noughtPiece = PlayingPieceO()
        val player2 = Player("player2", noughtPiece)

        players.add(player1)
        players.add(player2)

        gameBoard = Board(3)
    }

    fun startGame(): String {
        var noWinner = true
        while (noWinner) {

            val playerTurn = players.removeFirst()

            //get the free space from the board
            gameBoard.printBoard()
            val freeSpaces = gameBoard.getFreeCells()
            if (freeSpaces.isEmpty()) {
                noWinner = false
                continue
            }

            //read the user input
            print("Player:" + playerTurn.name + " Enter row,column: ")
            val inputScanner = Scanner(System.`in`)
            val s = inputScanner.nextLine()
            val values = s.split(",")
            val inputRow = Integer.valueOf(values[0])
            val inputColumn = Integer.valueOf(values[1])


            //place the piece
            val pieceAddedSuccessfully =
                gameBoard.addPiece(inputRow, inputColumn, playerTurn.playingPiece)
            if (!pieceAddedSuccessfully) {
                //player can not insert the piece into this cell, player has to choose another cell
                println("Incorred position chosen, try again")
                players.addFirst(playerTurn)
                continue
            }
            players.addLast(playerTurn)
            val winner: Boolean =
                isThereWinner(inputRow, inputColumn, playerTurn.playingPiece.pieceType)
            if (winner) {
                return playerTurn.name
            }
        }

        return "tie"
    }

    private fun isThereWinner(row: Int, column: Int, pieceType: PieceType): Boolean {
        var rowMatch = true
        var columnMatch = true
        var diagonalMatch = true
        var antiDiagonalMatch = true

        //need to check in row
        for (i in 0 until gameBoard.size) {
            if (gameBoard.board[row][i] == null || gameBoard.board[row][i]?.pieceType != pieceType) {
                rowMatch = false
            }
        }

        //need to check in column
        for (i in 0 until gameBoard.size) {
            if (gameBoard.board[i][column] == null || gameBoard.board[i][column]?.pieceType != pieceType) {
                columnMatch = false
            }
        }

        //need to check diagonals

        var i = 0
        var j = 0
        while (i < gameBoard.size) {
            if (gameBoard.board[i][j] == null || gameBoard.board[i][j]?.pieceType != pieceType
            ) {
                diagonalMatch = false
            }
            i++
            j++
        }


        //need to check anti-diagonals
        i = 0
        j = gameBoard.size - 1
        while (i < gameBoard.size) {
            if (gameBoard.board[i][j] == null || gameBoard.board[i][j]?.pieceType != pieceType) {
                antiDiagonalMatch = false
            }
            i++
            j--
        }
        return rowMatch || columnMatch || diagonalMatch || antiDiagonalMatch
    }

}

fun main(){
    val game = TicTacToeGame()
    game.initialiseGame()
    println("game winner is: " + game.startGame())

}