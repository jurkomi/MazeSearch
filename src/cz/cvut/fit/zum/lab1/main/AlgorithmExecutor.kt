package cz.cvut.fit.zum.lab1.main

import cz.cvut.fit.zum.lab1.algorithms.*
import cz.cvut.fit.zum.lab1.maze.Maze

class AlgorithmExecutor(private val maze: Maze) {

    private fun initMaze(algorithm: Algorithm, itFreqMs: Long) {
        printMaze(itFreqMs)
        maze.setStart()
        printMaze(itFreqMs)
        maze.setEnd()
        printMaze(itFreqMs)
        algorithm.checkEndtStart()
    }

    private fun printMaze(itFreqMs: Long) {
        println()
        Thread.sleep(itFreqMs)
        print("\u001b[H\u001b[2J")
        maze.print()
    }

    fun iterate(algorithm: Algorithm, itFreqMs: Long) {
        initMaze(algorithm, itFreqMs)
        var i = 0
        while(!algorithm.finished) {
            algorithm.openNodes()
            printMaze(itFreqMs)
            if (!algorithm.finished) {
                algorithm.expand()
                printMaze(itFreqMs)
            }
            i++
        }
        println(algorithm.finishMessage)
        println("\nAlgorithm ${algorithm.name} finished in $i iterations.")
        println("Nodes expanded: ${algorithm.expandedNumber}")
    }

     fun compare() {
        val algorithms = mutableListOf<Algorithm>(RandomSearch(maze))
        initMaze(algorithms.first(), 0)
        algorithms.add(DepthFirstSearch(maze.copy()))
        algorithms.add(BreadthFirstSearch(maze.copy()))
        algorithms.add(GreedySearch(maze.copy()))
        algorithms.add(Dijkstra(maze.copy()))
        algorithms.add(AStar(maze.copy()))
        if (algorithms.first().finished) {
            println("End point is at start, no difference.")
        }
        else {
            iterateComparation(algorithms)
        }
    }

    private fun iterateComparation(algorithms: List<Algorithm>) {
        val iterationsMap = mutableMapOf<String, Int>()
        val expandedMap = mutableMapOf<String, Int>()
        val pathMap = mutableMapOf<String, Int>()
        algorithms.forEach { algorithm ->
            var i = 0
            while(!algorithm.finished) {
                algorithm.openNodes()
                if (!algorithm.finished) {
                    algorithm.expand()
                }
                i++
            }
            iterationsMap[algorithm.name] = i
            expandedMap[algorithm.name] = algorithm.expandedNumber
            pathMap[algorithm.name] = algorithm.finishMessage.split(" ").last().toIntOrNull() ?: -1
        }
        printScore(iterationsMap, "Iterations:")
        printScore(expandedMap, "Nodes expanded:")
        printScore(pathMap, "Path length:")
    }

    private fun printScore(scoreMap: Map<String, Int>, label: String) {
        println("\n$label")
        printSeparator()
        scoreMap.toSortedMap()
            .toList()
            .sortedBy { (_, value) -> value}
            .forEach { (name, i) ->
                println("$name: $i")
            }
        printSeparator()
    }

    private fun printSeparator() {
        println("--------------------")
    }
}