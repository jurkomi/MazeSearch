package cz.cvut.fit.zum.lab1.algorithms

import cz.cvut.fit.zum.lab1.maze.*

class AStar(maze: Maze) : Algorithm(maze), Heuristics {
    override val name: String = "A*"

    override fun next(): Node {
        return opened.minBy {node -> node.count() - 1 + hypotCount(node)}!!
    }

}