package cz.cvut.fit.zum.lab1.algorithms

import cz.cvut.fit.zum.lab1.maze.*

class Dijkstra(maze: Maze) : Algorithm(maze) {
    override val name: String = "Dijkstra"

    override fun next(): Node {
        return opened.first()
    }

}