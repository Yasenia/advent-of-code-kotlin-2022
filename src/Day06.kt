fun main() {

    fun getStartOfMessage(message: String, markerSize: Int): Int {
        val data = message.toCharArray()
        var startPosition = markerSize
        while (data.slice(startPosition - markerSize until startPosition).distinct().count() < markerSize) startPosition++
        return startPosition
    }

    fun part1(input: List<String>): Int = getStartOfMessage(input[0], 4)

    fun part2(input: List<String>): Int = getStartOfMessage(input[0], 14)

    val testInput1 = readInput("Day06_test_1")
    check(part1(testInput1) == 7)
    check(part2(testInput1) == 19)

    val testInput2 = readInput("Day06_test_2")
    check(part1(testInput2) == 5)
    check(part2(testInput2) == 23)

    val testInput3 = readInput("Day06_test_3")
    check(part1(testInput3) == 6)
    check(part2(testInput3) == 23)

    val testInput4 = readInput("Day06_test_4")
    check(part1(testInput4) == 10)
    check(part2(testInput4) == 29)

    val testInput5 = readInput("Day06_test_5")
    check(part1(testInput5) == 11)
    check(part2(testInput5) == 26)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
