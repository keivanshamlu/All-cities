package com.shamlou.bases.dataStructure

class RadixTree<T>(private val root: Node<T> = Node(false)) {

    /**
     * checks whether tree is empty or not
     */
    fun isEmpty(): Boolean = root.edges.isEmpty()
    
    /**
     * compare two strings and will return first index which two strings differ
     */
    private fun getFirstMismatchLetter(word: String, edgeWord: String): Int {
        for (i in 1 until word.length.coerceAtMost(edgeWord.length)) {
            if (word[i] != edgeWord[i]) return i
        }
        return NO_MISMATCH
    }

    /**
     * get list of objects and will insert them all into radix tree
     */
    fun insertAll(items : List<Item<T>>){
        items.map { insert(it) }
    }
    /**
     * get an object and will insert it to radix tree
     */
    fun insert(item: Item<T>) {

        var current = root
        var currIndex = 0

        //Iterative approach
        while (currIndex < item.label.length) {
            val transitionChar = item.label.lowercase()[currIndex]
            val curEdge = current.getTransition(transitionChar)
            //Updated version of the input word
            val currStr = item.label.lowercase().substring(currIndex)

            //There is no associated edge with the first character of the current string
            //so simply add the rest of the string and finish
            if (curEdge == null) {
                current.edges[transitionChar] = Edge(currStr, Node(true, item))
                break
            }
            var splitIndex = getFirstMismatchLetter(currStr, curEdge.label)
            if (splitIndex == NO_MISMATCH) {
                //The edge and leftover string are the same length
                //so finish and update the next node as a word node
                if (currStr.length == curEdge.label.length) {
                    curEdge.next.isLeaf = true
                    curEdge.next.item = item
                    break
                } else if (currStr.length < curEdge.label.length) {
                    //The leftover word is a prefix to the edge string, so split
                    val suffix = curEdge.label.substring(currStr.length)
                    curEdge.label = currStr
                    val newNext = Node<T>(true, item)
                    val afterNewNext = curEdge.next
                    curEdge.next = newNext
                    newNext.addEdge(suffix, afterNewNext)
                    break
                } else { 
                    //There is leftover string after a perfect match
                    splitIndex = curEdge.label.length
                }
            } else {
                //The leftover string and edge string differed, so split at point
                val suffix = curEdge.label.substring(splitIndex)
                curEdge.label = curEdge.label.substring(0, splitIndex)
                val prevNext = curEdge.next
                curEdge.next = Node(false)
                curEdge.next.addEdge(suffix, prevNext)
            }

            //Traverse the tree
            current = curEdge.next
            currIndex += splitIndex
        }
    }

    /**
     * searchs for a prefix in radix tree
     * first of all search for particular node and then calls [getAllChildren]
     * and it will get all children of that node which is found data
     */
    fun search(word: String): List<T> {
        var current = root
        var currIndex = 0
        while (currIndex < word.length) {
            val transitionChar = word.lowercase()[currIndex]
            val edge = current.getTransition(transitionChar) ?: kotlin.run {
                return listOf()
            }
            currIndex += edge.label.length
            current = edge.next
        }
        return getAllChildren(current, word)
    }

    /**
     * gets a node in radix tree and the prefix text of that node
     * recursively calls itself until it reaches leaves and then
     * it will add them to a list
     */
    private fun getAllChildren(root: Node<T>, tillNow: String): List<T> {

        val list = mutableListOf<T>()
        if (root.isLeaf) root.item?.item?.let { return listOf(it) }
        root.edges.map {
            if (it.value.next.isLeaf) list.add(it.value.next.item!!.item)
            else list.addAll(
                getAllChildren(
                    it.value.next,
                    StringBuilder()
                        .append(tillNow)
                        .append(it.value.label)
                        .toString()
                )
            )
        }
        return list
    }

    companion object {
        private const val NO_MISMATCH = -1
    }

}

class Edge<T>(var label: String, var next: Node<T>)

class Item<T>(var label: String, var item: T)

class Node<T>(var isLeaf: Boolean, var item: Item<T>? = null) {

    var edges: HashMap<Char, Edge<T>> = HashMap()

    fun getTransition(transitionChar: Char): Edge<T>? {
        return edges[transitionChar]
    }

    fun addEdge(label: String, next: Node<T>) {
        edges[label[0]] = Edge(label, next)
    }
}