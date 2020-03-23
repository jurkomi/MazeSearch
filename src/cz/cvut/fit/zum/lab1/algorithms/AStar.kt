package cz.cvut.fit.zum.lab1.algorithms

import cz.cvut.fit.zum.lab1.maze.*
import kotlin.math.hypot

class AStar(maze: Maze) : Algorithm(maze) {
    override val name: String = "A*"

    override fun next(): Node {
        return opened.minBy { node ->
            val x = maze.end.first - node.x
            val y = maze.end.second - node.y
            node.count() - 1 + hypot(x.toDouble(), y.toDouble())
        }!!
    }

}