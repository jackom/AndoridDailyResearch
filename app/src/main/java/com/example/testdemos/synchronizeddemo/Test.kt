package com.example.testdemos.synchronizeddemo

import com.example.testmaven.LogUtils
import com.example.testremotemaven.TestRemoteMaven
import com.example.testupload2jcenter.TestUpload2Jcenter

fun main() {
//    LogUtils.d("Test.kt", "test maven constructor")
//    TestRemoteMaven.print()
    TestUpload2Jcenter().test()
    println()
    val count = Count()
    val t1 = Thread(count)
    val t2 = Thread(count)
    t1.start()
    t2.start()
    try {
        t1.join()
        t2.join()
    } catch (e: InterruptedException) {
        e.printStackTrace()
    }

    print("count.getCount()---> " + count.getCount())
}