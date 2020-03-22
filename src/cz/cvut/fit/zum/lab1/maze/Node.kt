package cz.cvut.fit.zum.lab1.maze

class Node(val x: Int, val y: Int, var state: State) : Component {
    var position = Pair(x,y)

    override fun getNode(): Node {
        return this
    }

    override fun finish(): String {
        return "End node found."
    }

    override fun getComponent(node: Node): Component {
        return node
    }

    override fun count(): Int {
        return 1
    }

    fun copyNode(): Node {
        return Node(x, y, state)
    }
}