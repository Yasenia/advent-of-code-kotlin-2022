abstract class FileSystemNode(val name: String) {
    abstract val parent: Directory?
    abstract fun size(): Int
}

class Directory(
    name: String,
    override val parent: Directory? = null,
    private val content: MutableList<FileSystemNode> = mutableListOf()
) : FileSystemNode(name) {
    override fun size(): Int = this.content.sumOf { it.size() }

    fun addFile(name: String, size: Int) {
        if (this.content.none { it.name == name }) this.content.add(File(name, size, this))
    }

    fun addDirectory(name: String) {
        if (this.content.none { it.name == name }) this.content.add(Directory(name, this))
    }

    fun changeDirectoryTo(relativePath: List<String>): Directory = relativePath.fold(this as Directory?) { directory, path ->
        if (path == "..") directory?.parent else directory?.content?.find { it.name == path } as? Directory
    } ?: throw NoSuchElementException("No such directory")

    fun listAllDirectories(): List<Directory> = this.content.flatMap { if (it is Directory) it.listAllDirectories() else emptyList() } + this
}

class File(name: String, private val size: Int, override val parent: Directory? = null) : FileSystemNode(name) {
    override fun size(): Int = this.size
}

fun main() {

    fun buildFileSystem(input: List<String>): Directory {

        val relativeChangeDirectoryCommand = "^\\$ cd ((\\w+|..)(/(\\w+|..))*)$".toRegex()
        val absoluteChangeDirectoryCommand = "^\\$ cd (((/(\\w+|..))+)|/)$".toRegex()
        val fileLine = "^(\\d+) ((\\w+)(.\\w+)?)$".toRegex()
        val directoryLine = "^dir (\\w+)$".toRegex()

        val root = Directory("/")
        var currentDirectory = root
        for (line in input) {
            if (relativeChangeDirectoryCommand.matches(line)) {
                val path = relativeChangeDirectoryCommand.find(line)!!.groupValues[1].split("/")
                currentDirectory = currentDirectory.changeDirectoryTo(path)
            } else if (absoluteChangeDirectoryCommand.matches(line)) {
                val path = absoluteChangeDirectoryCommand.find(line)!!.groupValues[1].split("/").filter { it.isNotBlank() }
                currentDirectory = root.changeDirectoryTo(path)
            } else if (fileLine.matches(line)) {
                val matchResult = fileLine.find(line)!!
                val size = matchResult.groupValues[1].toInt()
                val name = matchResult.groupValues[2]
                currentDirectory.addFile(name, size)
            } else if (directoryLine.matches(line)) {
                val name = directoryLine.find(line)!!.groupValues[1]
                currentDirectory.addDirectory(name)
            }
        }
        return root
    }

    fun part1(input: List<String>): Int = buildFileSystem(input).listAllDirectories().filter { it.size() <= 100000 }.sumOf { it.size() }

    fun part2(input: List<String>): Int {
        val root = buildFileSystem(input)
        val needDeletedSize = root.size() + 30000000 - 70000000
        return root.listAllDirectories().filter { it.size() >= needDeletedSize }.minOf { it.size() }
    }

    val testInput = readInput("Day07_test")
    check(part1(testInput) == 95437)
    check(part2(testInput) == 24933642)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
