package com.example.demotest.collectionsdemo

import java.util.*

/**
 * @date：2020-02-04 22:07
 * @desc：
 * @author：jackom
 */
fun main() {
    test1()
}

fun test1() {
    val tmpLists = listOf(1, 3, 5, 2, 9, 7, 6, 4, 10, 8)
    Collections.sort(tmpLists, object : Comparator<Int> {
        override fun compare(o1: Int, o2: Int): Int {
            val gap = o1 - o2
//            return when {
//                gap < 0 -> -1
//                gap > 0 -> 1
//                else -> 0
//            }
            return gap
        }

    })
    print("排序后的list数据为：$tmpLists")
    val calendar = Calendar.getInstance()
//    calendar.add()

}
