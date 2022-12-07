fun main() {
    fun part1(input: List<String>): Int {
        var currentCalories = 0
        var mostCalories = 0
        for ((i, line) in input.withIndex()) {
            if (line.isNotBlank()) currentCalories += line.toInt()
            if (line.isBlank() || i == input.lastIndex) {
                mostCalories = mostCalories.coerceAtLeast(currentCalories)
                currentCalories = 0
            }
        }
        return mostCalories
    }

    fun part2(input: List<String>): Int {
        var currentCalories = 0
        val mostCaloriesList = mutableListOf(0, 0, 0)
        for ((i, line) in input.withIndex()) {
            if (line.isNotBlank()) currentCalories += line.toInt()
            if (line.isBlank() || i == input.lastIndex) {
                for (j in mostCaloriesList.indices) {
                    if (currentCalories < mostCaloriesList[j]) continue
                    for (k in mostCaloriesList.lastIndex downTo j + 1) mostCaloriesList[k] = mostCaloriesList[k - 1]
                    mostCaloriesList[j] = currentCalories
                    break
                }
                currentCalories = 0
            }
        }
        return mostCaloriesList.sum()
    }

    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
