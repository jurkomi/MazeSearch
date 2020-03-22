package cz.cvut.fit.zum.lab1.main

import cz.cvut.fit.zum.lab1.maze.Maze
import cz.cvut.fit.zum.lab1.maze.Node
import cz.cvut.fit.zum.lab1.maze.State
import java.io.BufferedReader
import java.io.File

class FileLoader(private val location: String) {

    private fun load(): String {
        val bufferedReader: BufferedReader = File(location).bufferedReader()
        return bufferedReader.use { it.readText() }
    }

    fun getMaze(): Maze {
        val text = load()
        println("File:\n$text")
        val lines = text.lines()
        val sizeX = lines.first().count{lines.first().contains("X", true)}
        val sizeY = lines.filter{ it.isNotEmpty() && it[0].equals('X', true)}.count()
        println("Size: $sizeX x $sizeY\n")
        val array: Array<Array<Node>> =
            Array(sizeY) { row ->
                Array(sizeX) { col ->
                    val state = if (lines[row].get(col).equals('X', true)) State.WALL else State.FRESH
                    Node(row, col, state)
                }
        }
        val start = getNodePosition(lines[sizeY])
        val end = getNodePosition(lines[sizeY+1])
        return Maze(start, end, array)
    }

    private fun getNodePosition(line: String): Pair<Int, Int> {
        val parts = line.split(" ")
        val x = parts.last().toInt()
        val y = parts[1].substring(0,parts[1].lastIndex).toInt()
        return Pair(x, y)
    }
}