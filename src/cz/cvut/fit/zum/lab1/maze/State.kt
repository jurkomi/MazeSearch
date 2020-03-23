package cz.cvut.fit.zum.lab1.maze

enum class State(private val char: Char) {
    START('S'), END('E'), WALL('X'), OPENED('#'), CLOSED('o'), FRESH(' '), PATH('♥');

    override fun toString() = char.toString()
}