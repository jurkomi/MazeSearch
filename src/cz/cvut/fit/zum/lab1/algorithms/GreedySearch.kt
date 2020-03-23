package cz.cvut.fit.zum.lab1.algorithms

import cz.cvut.fit.zum.lab1.maze.Node
import cz.cvut.fit.zum.lab1.maze.Maze
import kotlin.math.hypot

class GreedySearch(maze: Maze) : Algorithm(maze) {
    override val name: String = "Greedy Search"

    override fun next(): Node {
        return opened.minBy { node ->
            val x = maze.end.first - node.x
            val y = maze.end.second - node.y
            hypot(x.toDouble(), y.toDouble())
        }!!
    }

}