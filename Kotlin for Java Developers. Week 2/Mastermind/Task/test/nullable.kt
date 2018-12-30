import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue

fun main(args: Array<String>) {
    val s1: String? = null
    val s2: String? = ""
    assertTrue(s1.isEmptyOrNull())
    assertTrue(s2.isEmptyOrNull())

    val s3 = "   "
    assertFalse(s3.isEmptyOrNull())
}

fun String?.isEmptyOrNull(): Boolean {
    return (this == null || this.isEmpty())
}