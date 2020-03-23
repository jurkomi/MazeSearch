package cz.cvut.fit.zum.lab1.algorithms

import cz.cvut.fit.zum.lab1.maze.Maze
import cz.cvut.fit.zum.lab1.maze.Node
import cz.cvut.fit.zum.lab1.maze.State

abstract class Algorithm(val maze: Maze) {

    var finished = false
        private set
    private var position = maze.start
    protected val opened = mutableListOf<Node>()
    private var current: Node = maze.getNodeByPosition(maze.start)
    abstract val name: String
    var expandedNumber = 0
    var pathLength = -1
    var paintPath = true

    fun expand() {
        if (hasNext()) {
            val exp = next()
            position = exp.position
            current = exp
            exp.state = State.CLOSED
            opened.remove(exp)
        }
        else finished = true
    }

    fun openNodes() {
        getAllNeighbours().forEach { neighbour ->
            if (neighbour.state == State.END) {
                expandedNumber++
                finished = true
                pathLength = current.finish(paintPath)
                return
            }
            if (isFresh(neighbour)) {
                neighbour.open(current)
                expandedNumber++
                opened.add(neighbour)
            }
        }
    }

    protected abstract fun next(): Node

    private fun hasNext(): Boolean {
        return opened.isNotEmpty()
    }


    private fun getAllNeighbours(): List<Node> {
        return listOf(getUpper(), getLeft(), getRight(), getBottom())
    }

    private fun getUpper(): Node {
        return maze.getNodeByPosition(Pair(position.first-1, position.second))
    }

    private fun getLeft(): Node {
        return maze.getNodeByPosition(Pair(position.first, position.second-1))
    }

    private fun getRight(): Node {
        return maze.getNodeByPosition(Pair(position.first, position.second+1))
    }

    private fun getBottom(): Node {
        return maze.getNodeByPosition(Pair(position.first+1, position.second))
    }

    private fun isFresh(node: Node?): Boolean {
        return (node != null && node.state == State.FRESH)
    }

    fun checkEndtStart() {
        if (maze.getNodeByPosition(position).state == State.END) finished = true
    }
}