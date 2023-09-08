package com.amitsah.designpatterns.low_level_design.custom_hash_map

class CustomHashMap<K, V> {
    private var hashTable: Array<Entry<K, V>?>

    constructor() {
        hashTable = arrayOfNulls(16)
    }

    constructor(capacity: Int) {
        hashTable = arrayOfNulls(capacity)
    }


    inner class Entry<K, V>(var key: K, var value: V) {
        var next: Entry<K, V>? = null
    }

    fun put(key: K, value: V) {
        val hashCode = key.hashCode() % hashTable.size
        var node: Entry<K, V>? = hashTable[hashCode]
        if (node == null) {
            val newNode: Entry<K, V> = Entry<K, V>(key, value)
            hashTable[hashCode] = newNode
        } else {
            var previousNode: Entry<K, V> = node
            while (node != null) {
                if (node.key === key) {
                    node.value = value
                    return
                }
                previousNode = node
                node = node.next
            }
            val newNode: Entry<K, V> = Entry<K, V>(key, value)
            previousNode.next = newNode
        }
    }

    operator fun get(key: K): V? {
        val hashCode = key.hashCode() % hashTable.size
        var node: Entry<K, V>? = hashTable[hashCode]
        while (node != null) {
            if (node.key == key) {
                return node.value
            }
            node = node.next
        }
        return null
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            val map: CustomHashMap<Int, String> = CustomHashMap<Int, String>(7)
            map.put(1, "hi")
            map.put(2, "my")
            map.put(3, "name")
            map.put(4, "is")
            map.put(5, "Shrayansh")
            map.put(6, "how")
            map.put(7, "are")
            map.put(8, "you")
            map.put(9, "friends")
            map.put(10, "?")
            val value = map[8]
            println(value)
        }
    }
}
