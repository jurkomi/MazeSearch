package cz.cvut.fit.zum.lab1.algorithms

import cz.cvut.fit.zum.lab1.maze.Maze
import cz.cvut.fit.zum.lab1.maze.Node
import cz.cvut.fit.zum.lab1.maze.State

abstract class Algorithm(val maze: Maze) {

    var finished = false
    var position = maze.start
    val opened = mutableListOf<Node>()

    abstract fun expand()

    abstract fun openNodes()

    fun getAllNeighbours(): List<Node?> {
        return listOf(getUpper(), getLeft(), getRight(), getBottom())
    }

    fun getUpper(): Node? {
        return maze.getNodeByPosition(Pair(position.first-1, position.second))
    }
    fun getLeft(): Node? {
        return maze.getNodeByPosition(Pair(position.first, position.second-1))
    }
    fun getRight(): Node? {
        return maze.getNodeByPosition(Pair(position.first, position.second+1))
    }
    fun getBottom(): Node? {
        return maze.getNodeByPosition(Pair(position.first+1, position.second))
    }

    fun isFresh(node: Node?): Boolean {
        return (node != null && node.state == State.FRESH)
    }

    fun checkEndtStart() {
        if (maze.getNodeByPosition(position).state == State.END) finished = true
    }
}