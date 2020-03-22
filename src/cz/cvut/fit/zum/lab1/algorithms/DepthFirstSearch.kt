package cz.cvut.fit.zum.lab1.algorithms

import cz.cvut.fit.zum.lab1.maze.Component
import cz.cvut.fit.zum.lab1.maze.Maze

class DepthFirstSearch(maze: Maze) : Algorithm(maze) {
    override val name: String = "Depth-First Search"

    override fun next(): Component {
        return opened.last()
    }

}