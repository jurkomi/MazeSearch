package cz.cvut.fit.zum.lab1

import cz.cvut.fit.zum.lab1.algorithms.Algorithm
import cz.cvut.fit.zum.lab1.algorithms.RandomSearch
import cz.cvut.fit.zum.lab1.helpers.AlgorithmExecutor
import cz.cvut.fit.zum.lab1.helpers.FileLoader
import cz.cvut.fit.zum.lab1.maze.Maze
import java.io.FileNotFoundException

fun main() {
    val maze = checkForFile()
    //val file = "C:\\Panda\\Škola\\Mgr\\6. semestr\\BI-ZUM\\PU1_prohledavani_stavoveho_prostoru\\dataset\\4.txt"
    if (maze != null) {
        val algorithm = selectAlgorithm(maze)
        if (algorithm != null) {
            val itFreqMs: Long = selectSpeed()
            if (itFreqMs != (-1).toLong()) {
                AlgorithmExecutor(algorithm, itFreqMs).iterate()
            }
        }
    }
}

fun checkForFile(): Maze? {
    var file: String? = null
    var maze: Maze? = null
    loop@ while (file.isNullOrEmpty()) {
        println("Enter location of the maze text file:")
        file = readLine()
        when (file) {
            "exit" -> return@loop
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

fun selectAlgorithm(maze: Maze): Algorithm? {
    var selected: String? = null
    loop@ while (selected.isNullOrEmpty()) {
        println("Select algorithm (1 - Random Search):")
        selected = readLine()
        when (selected) {
            "exit" -> return@loop
            "1" -> {
                return RandomSearch(maze)
            }
            null, "" -> return RandomSearch(maze)
            else -> {
                println("Wrong input!")
                selected = null
            }
        }
    }
    return null
}

fun selectSpeed(): Long {
    var speed: Long = -1
    loop@ while (speed.toInt() == -1) {
        println("Enter iterator speed in milliseconds (0-2000):")
        val input = readLine()
        when (input) {
            "exit" -> {
                return@loop
            }
            null, "" -> return 0
        }
        try {
            speed = input!!.toLong()
            if (speed in 0..2000)  return speed
        } catch (e: NumberFormatException) {
        }
        println("Please set speed betweeen 0 and 2000 ms!")
        speed = -1
    }
    return speed
}