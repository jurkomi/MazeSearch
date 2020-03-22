package cz.cvut.fit.zum.lab1.maze

class Node(val x: Int, val y: Int, var state: State) {
    val position = Pair(x,y)
}