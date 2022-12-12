fun main() {

    fun part1(input: List<String>): Int {
        val treeMatrix = input.map { line -> line.toCharArray().map { it.digitToInt() } }
        var visibleCount = 0
        for (i in 1 until treeMatrix.lastIndex) {
            for (j in 1 until treeMatrix[i].lastIndex) {
                val height = treeMatrix[i][j]
                if (
                    (0 until i).all { treeMatrix[it][j] < height } ||
                    (i + 1..treeMatrix.lastIndex).all { treeMatrix[it][j] < height } ||
                    (0 until j).all { treeMatrix[i][it] < height } ||
                    (j + 1..treeMatrix[i].lastIndex).all { treeMatrix[i][it] < height }
                ) visibleCount++
            }
        }
        return visibleCount + treeMatrix.size * 2 + treeMatrix.first().size + treeMatrix.last().size - 4
    }

    fun part2(input: List<String>): Int {
        val treeMatrix = input.map { line -> line.toCharArray().map { it.digitToInt() } }
        var maxScore = 0
        for (i in 1 until treeMatrix.lastIndex) {
            for (j in 1 until treeMatrix[i].lastIndex) {
                val height = treeMatrix[i][j]
                val upScore = ((i - 1 downTo 0).takeWhile { treeMatrix[it][j] < height }.size + 1).coerceAtMost(i)
                val downScore = ((i + 1..treeMatrix.lastIndex).takeWhile { treeMatrix[it][j] < height }.size + 1).coerceAtMost(treeMatrix.lastIndex - i)
                val leftScore = ((j - 1 downTo 0).takeWhile { treeMatrix[i][it] < height }.size + 1).coerceAtMost(j)
                val rightScore = ((j + 1..treeMatrix[i].lastIndex).takeWhile { treeMatrix[i][it] < height }.size + 1).coerceAtMost(treeMatrix[i].lastIndex - j)
                maxScore = maxScore.coerceAtLeast(upScore * downScore * leftScore * rightScore)
            }
        }
        return maxScore
    }

    val testInput = readInput("Day08_test")
    check(part1(testInput) == 21)
    check(part2(testInput) == 8)

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}
