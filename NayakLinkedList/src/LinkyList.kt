package list

class LinkyList<E> {
    private var size = 0
    private var head: Node<E>? = null
    private var tail: Node<E>? = null

    private inner class Node<E> constructor(internal var element: E, internal var next: Node<E>?)

    fun get(index: Int): E {
        validateElementIndex(index)
        return node(index).element
    }

    fun addV2(index: Int, element: E) {
        validatePositionIndex(index)
        if (index == 0) linkHead(element)
        else {
            var x = head
            val prevIndex = index - 1
            for (i in 0 until prevIndex) {
                x = x!!.next
            }
            val next = x!!.next
            val newNode = Node(element, next)
            x.next = newNode
            size++
        }
    }

     fun linkHead(element: E) {
        val h = head
        val newNode = Node<E>(element, h)
        head = newNode
        if (h == null) {
            tail = newNode
        }
        size++
    }

     fun linkTail(element: E) {
        val t = tail
        val newNode = Node<E>(element, null)
        tail = newNode
        if (t == null) {
            head = newNode
        } else {
            t.next = newNode
        }
        size++
    }

     fun linkBefore(element: E, succ: Node<E>) {
        val pred = getPrevious(succ)
        val newNode = Node(element, succ)
        if (pred == null) {
            head = newNode
        } else {
            pred.next = newNode
        }
        size++
    }

     fun unlink(curr: Node<E>): E {
        val element = curr.element
        val next = curr.next
        val prev = getPrevious(curr)

        if (prev == null) {
            head = next
        } else {
            prev.next = next
            curr.next = null
        }

        if (next == null) {
            prev?.next = null
            tail = prev
        } else {
            prev?.next = next
            curr.next = null
        }

        size--
        return element
    }

    private fun getPrevious(node: Node<E>): Node<E>? {
        if (head != null && node == head) return null
        var curr = head
        while (curr != null) {
            if (curr.next == node) {
                return curr
            }
            curr = curr.next
        }
        return null
    }

    private fun node(index: Int): Node<E> {
        var x = head
        for (i in 0 until index) {
            x = x!!.next
        }
        return x!!
    }

    private fun validateElementIndex(index: Int) {
        if (index < 0 || index >= size)
            throw IndexOutOfBoundsException(outOfBoundsMsg(index))
    }

    private fun validatePositionIndex(index: Int) {
        if (index < 0 || index > size)
            throw IndexOutOfBoundsException(outOfBoundsMsg(index))
    }

    private fun outOfBoundsMsg(index: Int): String {
        return "Index: $index, Size: $size"
    }

    override fun toString(): String {
        var curr = head
        if (curr == null) return "[]"
        else {
            val sb = StringBuilder()
            sb.append('[')
            while (curr != null) {
                sb.append(curr.element)
                curr = curr.next
                if (curr?.element == null) {
                    sb.append(']')
                } else {
                    sb.append(',').append(' ')
                }
            }
            return sb.toString()
        }
    }
}
