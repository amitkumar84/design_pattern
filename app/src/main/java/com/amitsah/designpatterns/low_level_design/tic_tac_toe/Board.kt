package com.amitsah.designpatterns.low_level_design.tic_tac_toe

class Board(val size: Int) {
    var board: Array<Array<PlayingPiece?>> = Array(size) { Array<PlayingPiece?>(size) { null } }
    fun addPiece(row: Int, column: Int, playingPiece: PlayingPiece): Boolean {
        if (board[row][column] != null) {
            return false
        }
        board[row][column] = playingPiece
        return true
    }

    fun getFreeCells(): List<Pair<Int, Int>> {
        val freeCells: MutableList<Pair<Int, Int>> = mutableListOf()
        for (i in 0 until size)
            for (j in 0 until size) {
                if (board[i][j] == null) {
                    freeCells.add(Pair(i, j))
                }
            }
        return freeCells
    }

    fun printBoard() {
        for (i in 0 until size){
            for (j in 0 until size) {
                if (board[i][j] != null) {
                    print(board[i][j]?.pieceType?.value + " ")
                } else {
                    print(" ")
                }
                print(" | ")
            }
            println()
        }
    }
}