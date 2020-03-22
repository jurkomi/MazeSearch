package cz.cvut.fit.zum.lab1.algorithms

import cz.cvut.fit.zum.lab1.maze.*
import kotlin.math.hypot

class AStar(maze: Maze) : PathAlgorithm(maze) {
    override val name: String = "A*"

    override fun next(): Component {
        return opened.minBy { path ->
            val x = maze.end.first - path.getNode().x
            val y = maze.end.second - path.getNode().y
            path.count() - 1 + hypot(x.toDouble(), y.toDouble())
        }!!
    }

}