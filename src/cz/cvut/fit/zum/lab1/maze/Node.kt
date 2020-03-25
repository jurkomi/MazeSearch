package cz.cvut.fit.zum.lab1.maze

class Node(val x: Int, val y: Int, var state: State) {
    var position = Pair(x,y)
    var prevNode: Node? = null
    var pathLength = 0

    fun finish(paintPath: Boolean): Int {
        if (paintPath) {
            if (state == State.CLOSED) state = State.PATH
            var n = prevNode
            while (n != null) {
                n = n.paintPath()
            }
        }
        return pathLength
    }

    private fun paintPath(): Node? {
        if (state == State.CLOSED) state = State.PATH
        return prevNode
    }

    fun open (node: Node) {
        if (state == State.FRESH) state = State.OPENED
        prevNode = node
        pathLength = node.pathLength + 1
    }

    fun count(): Int {
        return pathLength
    }

    fun copyNode(): Node {
        return Node(x, y, state)
    }
}