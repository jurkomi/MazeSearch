package cz.cvut.fit.zum.lab1.maze

class Maze(val start: Pair<Int, Int>, val end: Pair<Int, Int>, private val nodes: Array<Array<Node>>) {

    private fun changeNodeState(position: Pair<Int, Int>, state: State) {
        nodes[position.first][position.second].state = state
    }

    fun print() {
        nodes.forEach { row ->
            var line = ""
            row.forEach {node ->
                line += node.state.toString()
            }
            println(line)
        }
    }

    fun setStart() {
        changeNodeState(start, State.START)
    }

    fun setEnd() {
        changeNodeState(end, State.END)
    }

    fun getNodeByPosition(position: Pair<Int, Int>): Node {
        return nodes[position.first][position.second]
    }

    fun copy(): Maze {
        val nodesCopy: Array<Array<Node>> =
            Array(nodes.size) { row ->
                Array(nodes.first().size) { col ->
                    nodes[row][col].copyNode()
                }
            }
        return Maze(start, end, nodesCopy)
    }
}