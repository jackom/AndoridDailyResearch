package com.example.demotest

/**
 * @date：2019-07-02 23:22
 * @desc：
 * @author：jackom
 */

data class ListNode(var value: Int, var next: ListNode? = null)

fun findNode(head: ListNode?, value: Int): ListNode? {
    head ?: return null

    if (head.value == value) return head
    return findNode(head.next, value)
}


fun main() {
    val node = ListNode(1)
    var p = node
    val COUNT = 1000
    p.value = COUNT
    for (i in 0..COUNT) {
        p.next = ListNode(i)
        p = p.next!!
        println("for: ${p.value}")
    }

    println("p.value is ${p.value}")

    print("findNode: ${findNode(node, COUNT - 2)?.value}")

    println()
    test1()
    println()
//    test2()
    println()
    test3()
}

fun test2() {
    val sequence = sequenceOf(1, 3, 5, 7)
    val result = sequence
            .map { i ->
                println("Map $i")
                i * 2
            }
            .filter { i ->
                println("Filter $i")
                i % 3  == 0
            }
    println(result.first())
}

fun test3() {
    val list = listOf(1, 3, 5, 7)
    val result = list
            .map { i ->
                println("Map $i")
                i * 2
            }
            .filter { i ->
                println("Filter $i")
                i % 3  == 0
            }
    println(result.first())
}

fun test1() {
    val str1 = "123"
    val str2 = "123"
    println(str1 == str2)
    println(str1 === str2)
}



