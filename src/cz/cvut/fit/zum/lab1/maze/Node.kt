package cz.cvut.fit.zum.lab1.maze

class Node(val x: Int, val y: Int, var state: State) {
    var position = Pair(x,y)
    var prevNode: Node? = null
    var pathLength = 1

    fun finish(paintPath: Boolean): Int {
        if (paintPath) paintPath()
        return pathLength
    }

    private fun paintPath() {
        if (state == State.CLOSED) state = State.PATH
        if (prevNode != null) prevNode!!.paintPath()
    }

    fun open (node: Node) {
        state = State.OPENED
        prevNode = node
        pathLength += node.pathLength
    }

    fun count(): Int {
        return pathLength
    }

    fun copyNode(): Node {
        return Node(x, y, state)
    }
}