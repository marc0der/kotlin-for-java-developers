package board

import org.junit.Assert.*
import org.junit.Ignore
import org.junit.Test

class TestGameBoard {

    operator fun <T> GameBoard<T>.get(i: Int, j: Int) = get(getCell(i, j))
    operator fun <T> GameBoard<T>.set(i: Int, j: Int, value: T) = set(getCell(i, j), value)

    @Test
    fun testGetAndSetElement() {
        val gameBoard = createGameBoard<Char>(2)
        gameBoard[1, 1] = 'a'
        assertEquals('a', gameBoard[1, 1])
    }

    @Test
    fun testFilter() {
        val gameBoard = createGameBoard<Char>(2)
        gameBoard[1, 1] = 'a'
        gameBoard[1, 2] = 'b'
        val cells = gameBoard.filter { it == 'a' }
        assertEquals(1, cells.size)
        val cell = cells.first()
        assertEquals(1, cell.i)
        assertEquals(1, cell.j)
    }

    @Ignore
    fun testAll() {
        val gameBoard = createGameBoard<Char>(2)
        gameBoard[1, 1] = 'a'
        gameBoard[1, 2] = 'a'
        assertFalse(gameBoard.all { it == 'a' })
        gameBoard[2, 1] = 'a'
        gameBoard[2, 2] = 'a'
        assertTrue(gameBoard.all { it == 'a' })
    }

    @Ignore
    fun testAny() {
        val gameBoard = createGameBoard<Char>(2)
        gameBoard[1, 1] = 'a'
        gameBoard[1, 2] = 'b'
        assertTrue(gameBoard.any { it in 'a'..'b' })
        assertTrue(gameBoard.any { it == null })
    }
}