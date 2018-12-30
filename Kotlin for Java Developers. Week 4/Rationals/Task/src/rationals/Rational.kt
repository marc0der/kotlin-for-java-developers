package rationals

import java.math.BigInteger

data class Rational(val numerator: BigInteger, val denominator: BigInteger) : Comparable<Rational> {

    init {
        if (denominator == BigInteger.ZERO) throw IllegalArgumentException("Denominator is 0!")
    }

    constructor(numerator: Int, denominator: Int) : this(numerator.toBigInteger(), denominator.toBigInteger())

    override fun toString(): String {
        val r = normalise(this)
        return if (r.denominator == BigInteger.ONE)
            "${r.numerator}"
        else
            "${r.numerator}/${r.denominator}"
    }

    override fun equals(other: Any?): Boolean =
            if (other == null || other !is Rational) {
                false
            } else {
                val nThis = normalise(this)
                val nThat = normalise(other)
                nThis.numerator == nThat.numerator && nThis.denominator == nThat.denominator
            }


    private fun normalise(r: Rational): Rational {
        val gcd = r.numerator.gcd(r.denominator)
        return if (r.numerator < BigInteger.ZERO && r.denominator < BigInteger.ZERO)
            Rational(r.numerator.abs() / gcd, r.denominator.abs() / gcd)
        else if (r.numerator > BigInteger.ZERO && r.denominator < BigInteger.ZERO)
            Rational(r.numerator.negate() / gcd, r.denominator.abs() / gcd)
        else
            Rational(r.numerator / gcd, r.denominator / gcd)
    }

    override fun compareTo(other: Rational): Int {
        val (left, right) = commonDenominators(this, other)
        return when {
            left.numerator > right.numerator -> 1
            left.numerator < right.numerator -> -1
            else -> 0
        }
    }

}

operator fun Rational.plus(that: Rational): Rational {
    val (left, right) = commonDenominators(this, that)
    return Rational(left.numerator + right.numerator, left.denominator)
}

operator fun Rational.minus(that: Rational): Rational {
    val (left, right) = commonDenominators(this, that)
    return Rational(left.numerator - right.numerator, left.denominator)
}

private fun commonDenominators(left: Rational, right: Rational): Pair<Rational, Rational> =
        Rational(left.numerator * right.denominator, left.denominator * right.denominator) to
                Rational(right.numerator * left.denominator, right.denominator * left.denominator)

operator fun Rational.times(that: Rational): Rational =
        Rational(this.numerator * that.numerator, this.denominator * that.denominator)

operator fun Rational.div(that: Rational): Rational =
        Rational(this.numerator * that.denominator, this.denominator * that.numerator)

operator fun Rational.unaryMinus(): Rational = Rational(-this.numerator, this.denominator)

infix fun Int.divBy(denominator: Int) = Rational(this.toBigInteger(), denominator.toBigInteger())

infix fun Long.divBy(denominator: Long) = Rational(this.toBigInteger(), denominator.toBigInteger())

infix fun BigInteger.divBy(that: BigInteger): Rational = Rational(this, that)

fun String.toRational(): Rational =
        if (!this.contains('/'))
            Rational(this.toBigInteger(), BigInteger.ONE)
        else {
            val (num: String, den: String) = this.split('/')
            Rational(num.toBigInteger(), den.toBigInteger())
        }

fun main(args: Array<String>) {
    val half = 1 divBy 2
    val third = 1 divBy 3

    val sum: Rational = half + third
    println(5 divBy 6 == sum)

    val difference: Rational = half - third
    println(1 divBy 6 == difference)

    val product: Rational = half * third
    println(1 divBy 6 == product)

    val quotient: Rational = half / third
    println(3 divBy 2 == quotient)

    val negation: Rational = -half
    println(-1 divBy 2 == negation)

    println((2 divBy 1).toString() == "2")
    println((-2 divBy 4).toString() == "-1/2")
    println("117/1098".toRational().toString() == "13/122")

    val twoThirds = 2 divBy 3
    println(half < twoThirds)

    println(half in third..twoThirds)

    println(2000000000L divBy 4000000000L == 1 divBy 2)

    println("912016490186296920119201192141970416029".toBigInteger() divBy
            "1824032980372593840238402384283940832058".toBigInteger() == 1 divBy 2)
}