package mastermind

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {

    val zipped = secret.zip(guess)
    val unmatched = zipped.filter { it.first != it.second }

    val (unmatchedSecrets, unmatchedGuesses) = unmatched.unzip()
    val remainingGuess = unmatchedSecrets.fold(unmatchedGuesses) { guesses, char ->
        guesses - char
    }

    val rightPositions = zipped.size - unmatched.size
    val wrongPositions = unmatchedGuesses.size - remainingGuess.size
    return Evaluation(rightPositions, wrongPositions)
}