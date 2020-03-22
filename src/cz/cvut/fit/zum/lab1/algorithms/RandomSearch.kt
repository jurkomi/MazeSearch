package cz.cvut.fit.zum.lab1.algorithms

import cz.cvut.fit.zum.lab1.maze.Maze
import cz.cvut.fit.zum.lab1.maze.State

class RandomSearch(maze: Maze) : Algorithm(maze) {

    override fun expand() {
        if (!finished) {
            if (opened.isEmpty()) finished = true
            val exp = opened.random()
            position = exp.position
            exp.state = State.PATH
            opened.remove(exp)
        }
    }

    override fun openNodes() {
        getAllNeighbours().forEach { neighbour ->
            if (neighbour!!.state == State.END) {
                finished = true
                return
            }
            if (isFresh(neighbour)) {
                neighbour.state = State.OPENED
                opened.add(neighbour)
            }
        }
    }
}