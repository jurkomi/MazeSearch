package cz.cvut.fit.zum.lab1.algorithms

import cz.cvut.fit.zum.lab1.maze.Maze
import cz.cvut.fit.zum.lab1.maze.Node
import kotlin.math.hypot

interface Heuristics {
    val maze: Maze

    fun hypotCount(node: Node): Double {
        val x = maze.end.first - node.x
        val y = maze.end.second - node.y
        return hypot(x.toDouble(), y.toDouble())
    }
}