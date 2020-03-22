package cz.cvut.fit.zum.lab1.algorithms

import cz.cvut.fit.zum.lab1.maze.Maze
import cz.cvut.fit.zum.lab1.maze.Path

abstract class PathAlgorithm(maze: Maze) : Algorithm(maze) {
    init {
        current = Path(mutableListOf(maze.getNodeByPosition(maze.start)))
    }
}