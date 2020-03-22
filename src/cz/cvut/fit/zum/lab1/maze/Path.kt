package cz.cvut.fit.zum.lab1.maze

class Path(private val path: MutableList<Node> = mutableListOf()) : Component {

    override fun getNode(): Node {
        return path.last()
    }

    override fun finish(): String {
        path.filter { it.state == State.PATH }.forEach { it.state = State.FINALPATH }
        return "Length of the path is ${path.size}."
    }

    override fun getComponent(node: Node): Component {
        val list = path.toMutableList()
        list.add(node)
        return Path(list)
    }

    override fun count(): Int {
        return path.size
    }
}