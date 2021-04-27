package com.example.testdemos.futuretask

import java.util.concurrent.Callable
import java.util.concurrent.FutureTask


/**
 * @author：jackom
 * @date：4/24/21 on 6:49 AM
 * @desc：新增FutureTask，实现多个线程同步执行
 */

fun main() {
    val callable1 = MyCallable1()
    val futureTask1 = FutureTask(callable1)
    val thread1: Thread = Thread(futureTask1)
    thread1.start()
    println("打印了futureTask 1...")
    val result1 = futureTask1.get()
    println("打印了futureTask $result1")

    val callable2 = MyCallable2()
    val futureTask2 = FutureTask(callable2)
    val thread2: Thread = Thread(futureTask2)
    thread2.start()
    println("打印了futureTask 2...")
    val result2 = futureTask2.get()
    println("打印了futureTask $result2")
}

class MyCallable1: Callable<String> {

    override fun call(): String {
        Thread.sleep(5000)
        return "#1"
    }

}

class MyCallable2: Callable<String> {

    override fun call(): String {
        return "#2"
    }

}