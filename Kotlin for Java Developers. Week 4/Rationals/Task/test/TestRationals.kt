package rationals

import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class TestRationals {

    @Test(expected = IllegalArgumentException::class)
    fun testZeroDenominator() {
        Rational(4, 0)
    }

    @Test
    fun test1Sum() {
        val sum: Rational = (1 divBy 2) + (1 divBy 3)
        assertEquals("Wrong result for sum", 5 divBy 6, sum)
    }

    @Test
    fun test2Difference() {
        val difference: Rational = (1 divBy 2) - (1 divBy 3)
        assertEquals("Wrong result for difference", 1 divBy 6, difference)
    }

    @Test
    fun test3Product() {
        val product: Rational = (1 divBy 2) * (1 divBy 3)
        assertEquals("Wrong result for product", 1 divBy 6, product)
    }

    @Test
    fun test4Quotient() {
        val quotient: Rational = (1 divBy 2) / (1 divBy 3)
        assertEquals("Wrong result for quotient", 3 divBy 2, quotient)
    }

    @Test
    fun test5Negation() {
        val negation: Rational = -(1 divBy 2)
        assertEquals("Wrong result for negation", -1 divBy 2, negation)
    }

    @Test
    fun testToStringWith1Denominator() {
        assertEquals("Wrong string representation for integer number",
            (2 divBy 1).toString(), "2")
    }

    @Test
    fun testToStringWithNon1Denominator() {
        assertEquals("Wrong string representation for integer number",
            (3 divBy 2).toString(), "3/2")
    }

    @Test
    fun testEquality() {
        assertTrue("equality not working", Rational(1, 2) == Rational(1, 2))
        Assert.assertFalse("equality not working", Rational(1, 2) == Rational(2, 2))
    }

    @Test
    fun testGreaterThan() {
        assertTrue("greater than misbehaving", (1 divBy 2) > (1 divBy 3))
    }

    @Test
    fun testLessThan() {
        assertTrue("less than misbehaving", (1 divBy 4) < (1 divBy 3))
    }

    @Test
    fun testLessThanOrEqual() {
        assertTrue("less than misbehaving", (1 divBy 4) <= (1 divBy 4))
        assertTrue("less than misbehaving", (1 divBy 4) <= (1 divBy 3))
    }

    @Test
    fun testStringToRational() {
        assertEquals("1".toRational(), Rational(1, 1))
        assertEquals("2".toRational(), Rational(2, 1))
        assertEquals("1/1".toRational(), Rational(1, 1))
        assertEquals("2/1".toRational(), Rational(2, 1))
    }

    @Test
    fun test7NormalizedForm() {
        assertEquals("Wrong normalized form for '-2 divBy 4'",
            (-2 divBy 4).toString(), "-1/2")
        assertEquals("Wrong normalized form for '117/1098'",
            "117/1098".toRational().toString(), "13/122")
        assertEquals("Wrong normalized form for '6 divBy 3'",
                (6 divBy 3).toString(), "2")
        assertEquals("Wrong normalized form for '-578136305229133309744 divBy -966904753430936619984'",
                "-578136305229133309744/-966904753430936619984".toRational(), "461/771".toRational())
        assertEquals("Wrong normalized form for '31/-541'",
                "31/-541".toRational(), -31 divBy 541)
    }

    @Test
    fun test8Comparison() {
        assertTrue("Wrong result for comparison", (1 divBy 2) < (2 divBy 3))
        assertTrue("20325830850349869048604856908 > -9192901948302584358938698 should be true",
                "20325830850349869048604856908".toRational() > "-9192901948302584358938698".toRational())
    }

    @Test
    fun test9InRange() {
        Assert.assertTrue("Wrong result for checking belonging to a range",
            (1 divBy 2) in (1 divBy 3)..(2 divBy 3))
    }

    @Test
    fun test10Long() {
        assertEquals("Wrong result for normalization of '2000000000L divBy 4000000000L'",
            2000000000L divBy 4000000000L, 1 divBy 2)
    }

    @Test
    fun test11BigInteger() {
        Assert.assertEquals("Wrong result for normalization of\n" +
            "\"912016490186296920119201192141970416029\".toBigInteger() divBy\n" +
            "\"1824032980372593840238402384283940832058\".toBigInteger()",
            "912016490186296920119201192141970416029".toBigInteger() divBy
            "1824032980372593840238402384283940832058".toBigInteger(), 1 divBy 2)
    }
}