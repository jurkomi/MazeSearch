package cz.cvut.fit.zum.lab1.helpers

import cz.cvut.fit.zum.lab1.algorithms.Algorithm

class AlgorithmExecutor(val algorithm: Algorithm, val itFreqMs: Long) {

    val maze = algorithm.maze

    private fun initMaze() {
        printMaze()
        maze.setStart()
        printMaze()
        maze.setEnd()
        printMaze()
        algorithm.checkEndtStart()
    }

    private fun printMaze() {
        println()
        Thread.sleep(itFreqMs)
        print("\u001b[H\u001b[2J")
        maze.print()
    }

    fun iterate() {
        initMaze()
        var i = 0
        while(!algorithm.finished) {
            algorithm.openNodes()
            if (!algorithm.finished) {
                printMaze()
                algorithm.expand()
                printMaze()
            }
            i++
        }
        println("\nAlgorithm finished in $i iterations.")
    }
}