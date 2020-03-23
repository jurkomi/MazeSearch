package cz.cvut.fit.zum.lab1.algorithms

import cz.cvut.fit.zum.lab1.maze.Node
import cz.cvut.fit.zum.lab1.maze.Maze

class BreadthFirstSearch(maze: Maze) : Algorithm(maze) {
    override val name: String = "Breadth-First Search"

    override fun next(): Node {
        return opened.first()
    }

}