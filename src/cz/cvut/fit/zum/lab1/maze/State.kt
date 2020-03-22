package cz.cvut.fit.zum.lab1.maze

enum class State(private val char: Char) {
    START('S'), END('E'), WALL('X'), OPENED('#'), PATH('o'), FRESH(' ');

    override fun toString() = char.toString()
}