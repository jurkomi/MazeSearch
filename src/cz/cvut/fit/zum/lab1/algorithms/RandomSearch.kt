package cz.cvut.fit.zum.lab1.algorithms

import cz.cvut.fit.zum.lab1.maze.Node
import cz.cvut.fit.zum.lab1.maze.Maze

class RandomSearch(maze: Maze) : Algorithm(maze) {
    override val name: String = "Random Search"

    override fun next(): Node {
        return opened.random()
    }

}