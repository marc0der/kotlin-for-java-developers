package nicestring

fun String.isNice(): Boolean =
        listOf(!containsBuBaBe(this),
                containsAtLeast3Vowels(this),
                containsDoubleLetters(this)
        ).count { it } >= 2

fun containsBuBaBe(s: String): Boolean =
        listOf("bu", "ba", "be").filter { s.contains(it) }.isNotEmpty()

fun containsAtLeast3Vowels(s: String): Boolean {
    val vowels = listOf('a', 'e', 'i', 'o', 'u')
    return s.foldRight(0) { char: Char, acc: Int ->
        if (vowels.contains(char)) acc + 1 else acc
    } >= 3
}

fun containsDoubleLetters(s: String): Boolean =
        s.isNotBlank() &&
                s.foldRight("") { c, acc ->
                    if (acc.startsWith(c))
                        return true
                    else c + acc
                }.isBlank()