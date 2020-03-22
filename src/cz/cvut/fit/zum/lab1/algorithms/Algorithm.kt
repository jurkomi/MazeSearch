package cz.cvut.fit.zum.lab1.algorithms

import cz.cvut.fit.zum.lab1.maze.Component
import cz.cvut.fit.zum.lab1.maze.Maze
import cz.cvut.fit.zum.lab1.maze.Node
import cz.cvut.fit.zum.lab1.maze.State

abstract class Algorithm(val maze: Maze) {

    var finished = false
        private set
    private var position = maze.start
    protected val opened = mutableListOf<Component>()
    protected var current: Component = maze.getNodeByPosition(maze.start)
    abstract val name: String
    var finishMessage: String? = null

    fun expand() {
        if (hasNext()) {
            val exp = next()
            position = exp.getNode().position
            current = exp
            exp.getNode().state = State.PATH
            opened.remove(exp)
        }
        else finished = true
    }

    fun openNodes() {
        getAllNeighbours().forEach { neighbour ->
            if (neighbour.state == State.END) {
                finished = true
                finishMessage = current.finish()
                return
            }
            if (isFresh(neighbour)) {
                neighbour.state = State.OPENED
                opened.add(current.getComponent(neighbour))
            }
        }
    }

    protected abstract fun next(): Component

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