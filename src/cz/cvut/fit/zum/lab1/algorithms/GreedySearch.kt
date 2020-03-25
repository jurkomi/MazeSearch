package cz.cvut.fit.zum.lab1.algorithms

import cz.cvut.fit.zum.lab1.maze.Node
import cz.cvut.fit.zum.lab1.maze.Maze

open class GreedySearch(maze: Maze) : Algorithm(maze), Heuristics {
    override val name: String = "Greedy Search"

    override fun next(): Node {
        return opened.minBy {node -> hypotCount(node)}!!
    }

}