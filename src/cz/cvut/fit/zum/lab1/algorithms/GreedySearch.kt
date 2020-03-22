package cz.cvut.fit.zum.lab1.algorithms

import cz.cvut.fit.zum.lab1.maze.Component
import cz.cvut.fit.zum.lab1.maze.Maze
import kotlin.math.hypot

class GreedySearch(maze: Maze) : PathAlgorithm(maze) {
    override val name: String = "Greedy Search"

    override fun next(): Component {
        return opened.minBy { node ->
            val x = maze.end.first - node.getNode().x
            val y = maze.end.second - node.getNode().y
            hypot(x.toDouble(), y.toDouble())
        }!!
    }

}