package board

fun IntProgression.applyBounds(bound: Int): IntProgression =
        if (last > bound)
            first..bound
        else if (first > bound)
            bound..last
        else this
