fun main() {

    fun String.toAssignments(): Pair<Pair<Int, Int>, Pair<Int, Int>> {
        val assignments = this.split(",")
        val assignment1 = assignments[0].split("-")
        val assignment2 = assignments[1].split("-")
        return Pair(assignment1[0].toInt() to assignment1[1].toInt(), assignment2[0].toInt() to assignment2[1].toInt())
    }

    fun part1(input: List<String>): Int = input
        .map { it.toAssignments() }
        .count { (assignment1, assignment2) ->
            (assignment1.first >= assignment2.first && assignment1.second <= assignment2.second)
                    || (assignment1.first <= assignment2.first && assignment1.second >= assignment2.second)
        }

    fun part2(input: List<String>): Int = input
        .map { it.toAssignments() }
        .count { (assignment1, assignment2) ->
            assignment1.first <= assignment2.second && assignment2.first <= assignment1.second
        }

    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}

