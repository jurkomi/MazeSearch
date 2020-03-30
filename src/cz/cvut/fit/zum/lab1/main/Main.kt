package cz.cvut.fit.zum.lab1.main

import cz.cvut.fit.zum.lab1.algorithms.*
import cz.cvut.fit.zum.lab1.maze.Maze
import java.io.FileNotFoundException as FileNotFoundException

fun main() {
    val maze = checkForFile()
    if (maze != null) {
        val algorithm = selectAlgorithm(maze)
        if (algorithm != null) {
            val itFreqMs: Long = selectSpeed()
            if (itFreqMs != (-1).toLong()) {
                AlgorithmExecutor(maze).iterate(algorithm, itFreqMs)
            }
        }
    }
}

private fun checkForFile(): Maze? {
    var file: String? = null
    var maze: Maze? = null
    loop@ while (file.isNullOrEmpty()) {
        println("Enter location of the maze text file:")
        file = readLine()
        when (file) {
            "exit" -> break@loop
            null, "" -> {}
            else -> {
                try {
                    maze = FileLoader(file).getMaze()
                    return maze
                } catch (e: FileNotFoundException) {
                    println("File not found!")
                    file = null
                }
            }
        }
    }
    return maze
}

private fun selectAlgorithm(maze: Maze): Algorithm? {
    var selected: String? = null
    loop@ while (selected.isNullOrEmpty()) {
        println("Select algorithm (1 - Random Search, 2 - DFS, 3 - BFS, " +
                    "4 - Greedy Search, 5 - Dijkstra, 6 - A*, 0 - compare all):")
        selected = readLine()
        when (selected) {
            "exit" -> break@loop
            "0" -> {
                AlgorithmExecutor(maze).compare()
                break@loop
            }
            "1" -> {
                return RandomSearch(maze)
            }
            "2" -> {
                return DepthFirstSearch(maze)
            }
            "3" -> {
                return BreadthFirstSearch(maze)
            }
            "4" -> {
                return GreedySearch(maze)
            }
            "5" -> {
                return Dijkstra(maze)
            }
            "6" -> {
                return AStar(maze)
            }
            null, "" -> {
                AlgorithmExecutor(maze).compare()
                break@loop
            }
            else -> {
                println("Wrong input!")
                selected = null
            }
        }
    }
    return null
}

private fun selectSpeed(): Long {
    var speed: Long = -1
    loop@ while (speed.toInt() == -1) {
        println("Enter iterator speed in milliseconds (0-2000):")
        val input = readLine()
        when (input) {
            "exit" -> break@loop
            null, "" -> return 0
        }
        try {
            speed = input!!.toLong()
            if (speed in 0..2000) return speed
        } catch (e: NumberFormatException) {
        }
        println("Please set speed betweeen 0 and 2000 ms!")
        speed = -1
    }
    return speed
}