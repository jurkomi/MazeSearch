package cz.cvut.fit.zum.lab1.algorithms

import cz.cvut.fit.zum.lab1.maze.Maze
import cz.cvut.fit.zum.lab1.maze.Node
import cz.cvut.fit.zum.lab1.maze.State

private val upperPosition = Pair(-1, 0)
private val leftPosition = Pair(0, -1)
private val bottomPosition = Pair(1, 0)
private val rightPosition = Pair(0, 1)

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
            current = next()
            position = current.position
            if (current.state == State.END) {
                finished = true
                pathLength = current.finish(paintPath)
            } else {
                current.state = State.CLOSED
                opened.remove(current)
            }
        }
        else finished = true
    }

    fun openNodes() {
        getAllNeighbours().forEach { neighbour ->
            if (isFreshOrEnd(neighbour)) {
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
        val positions = listOf(upperPosition, leftPosition, rightPosition, bottomPosition)
        return positions.map(this::getNode)
    }

    private fun getNode(pos: Pair<Int, Int>): Node {
        return maze.getNodeByPosition(Pair(position.first + pos.first, position.second + pos.second))
    }

    private fun isFreshOrEnd(node: Node?): Boolean {
        return (node != null && (node.state == State.FRESH || node.state == State.END))
    }

    fun checkEndtStart() {
        if (maze.getNodeByPosition(position).state == State.END) finished = true
    }
}