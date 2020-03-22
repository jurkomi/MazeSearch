package cz.cvut.fit.zum.lab1.algorithms

import cz.cvut.fit.zum.lab1.maze.Component
import cz.cvut.fit.zum.lab1.maze.Maze

class RandomSearch(maze: Maze) : PathAlgorithm(maze) {
    override val name: String = "Random Search"

    override fun next(): Component {
        return opened.random()
    }

}