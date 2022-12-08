import java.util.Stack

fun main() {

    val stackLinePattern = "^(\\[[A-Z]]| {3})( \\[[A-Z]]| {4})*$".toRegex()
    val commandLinePattern = "^move (\\d+) from (\\d+) to (\\d+)$".toRegex()

    fun part1(input: List<String>): String {
        val stacks = mutableListOf<Stack<Char>>()
        for (line in input) {
            if (line.matches(stackLinePattern)) {
                for (i in 0..line.length / 4) {
                    while (stacks.size <= i) stacks.add(Stack())
                    val crate = line[i * 4 + 1]
                    if (crate != ' ') stacks[i].add(0, line[i * 4 + 1])
                }
            } else {
                val groupValues = commandLinePattern.find(line)?.groupValues ?: continue
                val count = groupValues[1].toInt()
                val fromStack = stacks[groupValues[2].toInt() - 1]
                val toStack = stacks[groupValues[3].toInt() - 1]
                repeat(count) { toStack.push(fromStack.pop()) }
            }
        }
        return stacks.map { it.peek() }.joinToString("")
    }

    fun part2(input: List<String>): String {
        val stacks = mutableListOf<Stack<Char>>()
        val bufferStack = Stack<Char>()
        for (line in input) {
            if (line.matches(stackLinePattern)) {
                for (i in 0..line.length / 4) {
                    while (stacks.size <= i) stacks.add(Stack())
                    val crate = line[i * 4 + 1]
                    if (crate != ' ') stacks[i].add(0, line[i * 4 + 1])
                }
            } else {
                val groupValues = commandLinePattern.find(line)?.groupValues ?: continue
                val count = groupValues[1].toInt()
                val fromStack = stacks[groupValues[2].toInt() - 1]
                val toStack = stacks[groupValues[3].toInt() - 1]
                repeat(count) { bufferStack.push(fromStack.pop()) }
                repeat(count) { toStack.push(bufferStack.pop()) }
            }
        }
        return stacks.map { it.peek() }.joinToString("")
    }

    val testInput = readInput("Day05_test")
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
