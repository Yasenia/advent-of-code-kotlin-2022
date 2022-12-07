import java.util.BitSet

fun main() {

    fun Char.priority(): Int = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(this) + 1

    fun part1(input: List<String>): Int {
        var totalPriority = 0
        for (rucksack in input) {
            val compartment1 = rucksack.substring(0, rucksack.length / 2)
            val compartment2 = rucksack.substring(rucksack.length / 2, rucksack.length)
            val flags = BitSet(52)
            for (item in compartment1) flags.set(item.priority() - 1, true)
            for (item in compartment2) {
                val priority = item.priority()
                if (flags.get(priority - 1)) {
                    flags.set(priority - 1, false)
                    totalPriority += priority
                }
            }
        }
        return totalPriority
    }

    fun part2(input: List<String>): Int {
        var totalPriority = 0
        for (groupIndex in 0 until (input.size / 3)) {
            val compartment1 = input[groupIndex * 3]
            val compartment2 = input[groupIndex * 3 + 1]
            val compartment3 = input[groupIndex * 3 + 2]
            val flags = IntArray(52)
            for (item in compartment1) flags[item.priority() - 1] = 1
            for (item in compartment2) {
                val priority = item.priority()
                if (flags[priority - 1] == 1) flags[priority - 1] = 2
            }
            for (item in compartment3) {
                val priority = item.priority()
                if (flags[priority - 1] == 2) {
                    flags[priority - 1] = 3
                    totalPriority += priority
                }
            }
        }
        return totalPriority
    }

    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}

