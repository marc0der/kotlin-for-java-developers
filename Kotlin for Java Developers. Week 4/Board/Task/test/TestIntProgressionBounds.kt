package board

import org.junit.Assert.assertEquals
import org.junit.Test

class TestIntProgressionBounds {

    @Test
    fun testAscendingOutsideBoundary() {
        val progression = IntProgression.fromClosedRange(1, 10, 1)

        val expected = IntProgression.fromClosedRange(1, 9, 1)
        val actual = progression.applyBounds(9)

        assertEquals(expected.first, actual.first)
        assertEquals(expected.last, actual.last)
    }

    @Test
    fun testDescendingOutsideBoundary() {
        val progression = IntProgression.fromClosedRange(10, 1, 1)

        val expected = IntProgression.fromClosedRange(9, 1, 1)
        val actual = progression.applyBounds(9)

        assertEquals(expected.first, actual.first)
        assertEquals(expected.last, actual.last)
    }

    @Test
    fun testAscendingInsideBoundary() {
        val expected = IntProgression.fromClosedRange(1, 10, 1)
        val actual = expected.applyBounds(11)

        assertEquals(expected.first, actual.first)
        assertEquals(expected.last, actual.last)
    }

    @Test
    fun testDescendingInsideBoundary() {
        val expected = IntProgression.fromClosedRange(10, 1, 1)
        val actual = expected.applyBounds(11)

        assertEquals(expected.first, actual.first)
        assertEquals(expected.last, actual.last)
    }

    @Test
    fun testAscendingOnBoundary() {
        val expected = IntProgression.fromClosedRange(1, 10, 1)
        val actual = expected.applyBounds(10)

        assertEquals(expected.first, actual.first)
        assertEquals(expected.last, actual.last)
    }

    @Test
    fun testDescendingOnBoundary() {
        val expected = IntProgression.fromClosedRange(10, 1, 1)
        val actual = expected.applyBounds(10)

        assertEquals(expected.first, actual.first)
        assertEquals(expected.last, actual.last)
    }
}