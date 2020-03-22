package cz.cvut.fit.zum.lab1.maze

interface Component {
    fun getNode(): Node
    fun finish(): String
    fun getComponent(node: Node): Component
    fun count(): Int
}