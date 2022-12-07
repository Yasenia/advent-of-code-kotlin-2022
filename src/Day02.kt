fun main() {
    fun part1(input: List<String>): Int {
        var totalScore = 0
        for (line in input) {
            val columns = line.split(" ")
            val opponentValue = "ABC".indexOf(columns[0])
            val myValue = "XYZ".indexOf(columns[1])
            val shapeScore = listOf(1, 2, 3)[myValue]
            val outcomeScore = listOf(
                listOf(3, 0, 6),
                listOf(6, 3, 0),
                listOf(0, 6, 3),
            )[myValue][opponentValue]
            totalScore += shapeScore + outcomeScore
        }
        return totalScore
    }

    fun part2(input: List<String>): Int {
        var totalScore = 0
        for (line in input) {
            val columns = line.split(" ")
            val opponentValue = "ABC".indexOf(columns[0])
            val outcome = "XYZ".indexOf(columns[1])
            val shapeScore = listOf(
                listOf(3, 1, 2),
                listOf(1, 2, 3),
                listOf(2, 3, 1),
            )[opponentValue][outcome]
            val outcomeScore = listOf(0, 3, 6)[outcome]
            totalScore += shapeScore + outcomeScore
        }
        return totalScore
    }

    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
