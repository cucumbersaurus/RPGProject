package project.rpg.data.structures

class GraphData<T> {

    private val nodeMap = HashMap<T, Node<T>>()

    fun getNode(key: T): Node<T>? {
        return nodeMap[key]
    }

    fun getNodeOrDefault(key: T, default: Any): Any {
        return nodeMap[key] ?: default
    }

    class Node<T> {
        private val linked = ArrayList<Node<T>>()

        fun link(dist: Node<T>) {
            this.linked.add(dist)
        }

        fun linkTogether(to: Node<T>) {
            this.link(to)
            to.link(this)
        }

        fun unlink(dist: Node<T>) {
            this.linked.remove(dist)
        }

        fun unlinkTogether(to: Node<T>) {
            this.unlink(to)
            to.unlink(this)

        }
    }
}