package nicestring

import org.junit.Assert
import org.junit.Test

class TestNiceStrings {

    private fun testNiceString(string: String, expected: Boolean) {
        Assert.assertEquals("Wrong result for \"$string\".isNice()", expected, string.isNice())
    }

    @Test
    fun testExample1() = testNiceString("bac", false)

    @Test
    fun testExample2() = testNiceString("aza", false)

    @Test
    fun testExample3() = testNiceString("abaca", false)

    @Test
    fun testExample4() = testNiceString("baaa", true)

    @Test
    fun testExample5() = testNiceString("aaab", true)

    @Test
    fun testContainsBuBaBe() = listOf("bu", "ba", "be").forEach { assert(containsBuBaBe(it)) }

    @Test
    fun testDoesNotContainBuBaBe() = listOf("ub", "a", "").forEach { assert(!containsBuBaBe(it)) }

    @Test
    fun testContainsAtLeast3Vowels() = listOf("aei", "eio", "iou", "aiu", "aai", "aaa").forEach {
        assert(containsAtLeast3Vowels(it))
    }

    @Test
    fun testDownNotContain3Vowels() = listOf("", "ae", "bea", "bcai", "bbc", "bba", "bae").forEach {
        assert(!containsAtLeast3Vowels(it))
    }

    @Test
    fun testContainsDoubleLetters() = listOf("aa", "bb", "aabb", "abba", "aaab").forEach {
        assert(containsDoubleLetters(it))
    }

    @Test
    fun testDoesNotContainDoubleLetters() = listOf("", "ab", "ba", "abab", "baba", "ababab").forEach {
        assert(!containsDoubleLetters(it))
    }
}