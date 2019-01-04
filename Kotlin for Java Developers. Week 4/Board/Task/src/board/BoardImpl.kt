package board

import java.lang.IllegalArgumentException

fun createSquareBoard(width: Int): SquareBoard = object : SquareBoard {
    override val width: Int = width

    override fun getCellOrNull(i: Int, j: Int): Cell? =
            getAllCells().firstOrNull { it == Cell(i, j) }

    override fun getCell(i: Int, j: Int): Cell =
            getCellOrNull(i, j) ?: throw IllegalArgumentException("Cell not found for i:$i, j:$j")

    override fun getAllCells(): Collection<Cell> =
            (1..width).flatMap { i ->
                (1..width).map { j ->
                    Cell(i, j)
                }
            }

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> =
        jRange.applyBounds(width).map { j -> getCell(i, j) }


    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> = TODO()

    override fun Cell.getNeighbour(direction: Direction): Cell? = when (direction) {
        Direction.UP -> getCellOrNull(this.i - 1, j)
        Direction.LEFT -> getCellOrNull(this.i, j - 1)
        Direction.DOWN -> getCellOrNull(this.i + 1, j)
        Direction.RIGHT -> getCellOrNull(this.i, j + 1)
    }
}

fun <T> createGameBoard(width: Int): GameBoard<T> = TODO()

