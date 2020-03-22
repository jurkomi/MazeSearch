package cz.cvut.fit.zum.lab1.algorithms

import cz.cvut.fit.zum.lab1.maze.*

class Dijkstra(maze: Maze) : PathAlgorithm(maze) {
    override val name: String = "Dijkstra"

    override fun next(): Component {
        return opened.first()
    }

}