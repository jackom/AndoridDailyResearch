package com.example.testdemos

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

/**
 *  author : zhengminxin
 *  date : 2019/7/25 9:59
 *  desc :
 */

fun main() {
    println("test coroutines")
//    main1()
//    println("\n")
//    main2()
//    println("\n")
//    main3()
//    println("\n")
//    main4()
//    println("\n")
//    main5()
//    println("\n")
//    main6()
//    println("\n")
//    main7()
//    println("\n")
//    main8()
//    println("\n")
//    main9()

    test1()

    main10()
    println("AAA")

    main11()
    println("BBB")
    Thread.sleep(2000L) // 阻塞主线程 2 秒钟来保证 JVM 存活

    val car = Car.build {
        model = "bmw"
        year = 50
    }
    println(car)
}

fun test1() {
    val arrys = listOf<String>()
    println("测试--->${arrys.any()}")
//    val a = null
//    println("测试--->${a.any()}")

    val tmpArrys = mutableListOf("a", "b", "c")
    tmpArrys.addAll(arrys)
    println("测试--tmpArrys->${tmpArrys.size}")

    for (i in 4 downTo 1) println(i)
}

fun main1() = runBlocking {
    launch {
        repeat(5) {i ->
            println("I'm sleeping $i ...")
            delay(200L)
        }
//        delay(200L)
//        println("Task from runBlocking")
    }

    coroutineScope { //创建一个协程作用域
        launch {
            delay(500L)
            println("Task from nested launch")
        }

        delay(100L)
        println("Task from coroutine scope") //这一行会在内嵌 launch之前输出
    }
    println("Coroutine scope is over") // 这一行在内嵌 launch 执行完毕后才输出
}

fun main2() = runBlocking {
    launch {
        println("AAA")
        doWorld()
    }
    println("BBB")
    delay(1500L)
    println("Hello,")
}

suspend fun doWorld() {
    delay(1000L)
    println("World!")
}

fun main3() = runBlocking {
    GlobalScope.launch {
        repeat(1000) { i ->
            println("I'm sleeping $i ...")
            delay(500L)
        }
    }
    delay(1300L) // 在延迟后退出
    println("main3 Coroutine scope is over")
}

fun main4() = runBlocking {
    val job = launch {
        try {
            repeat(1000) {
                i: Int ->  println("I'm sleeping $i ...")
                delay(500L)
            }
        } finally {
            println("job: I'm running finally")
        }
    }
    delay(1300L)
    println("main: I'm tired of waiting!")
    job.cancelAndJoin()
    println("main: Now I can quit.")
}

fun main5() = runBlocking {
    val time = measureTimeMillis {
        val one = doSomethingUsefulOne()
        val two = doSomethingUsefulTwo()
        println("The answer is ${one + two}")
    }
    println("Completed in $time ms")
}

suspend fun doSomethingUsefulOne(): Int {
    delay(1000L) // 假设我们在这里做了些有用的事
    return 13
}

suspend fun doSomethingUsefulTwo(): Int {
    delay(1000L) // 假设我们在这里也做了一些有用的事
    return 29
}

fun main6() = runBlocking<Unit> {
    val time = measureTimeMillis {
        val one = async { doSomethingUsefulOne() }
        val two = async { doSomethingUsefulTwo() }
        println("The answer is ${one.await() + two.await()}")
    }
    println("Completed in $time ms")
}

fun main7() = runBlocking<Unit> {
    val time = measureTimeMillis {
        val one = async(start = CoroutineStart.LAZY) { doSomethingUsefulOne() }
        val two = async(start = CoroutineStart.LAZY) { doSomethingUsefulTwo() }
        // 执行一些计算
//        one.start() // 启动第一个
//        two.start() // 启动第二个
        println("The answer is ${one.await() + two.await()}")
    }
    println("Completed in $time ms")
}

fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")

fun main8() = runBlocking<Unit> {
    val a = async {
        delay(1500L)
        log("I'm computing a piece of the answer")
        6
    }
    val b = async {
        delay(1000L)
        log("I'm computing another piece of the answer")
        7
    }

    log("The answer is ${a.await() * b.await()}")
}

fun main9() = runBlocking<Unit> {
    launch { // 默认继承 parent coroutine 的 CoroutineDispatcher，指定运行在 main 线程
        println("main runBlocking: I'm working in thread ${Thread.currentThread().name}")
        delay(100)
        println("main runBlocking: After delay in thread ${Thread.currentThread().name}")
    }
    launch(Dispatchers.Unconfined) {
        println("Unconfined      : I'm working in thread ${Thread.currentThread().name}")
        delay(100)
        println("Unconfined      : After delay in thread ${Thread.currentThread().name}")
    }
}

fun main10() = runBlocking(Dispatchers.IO) {
    launch {
        delay(10000L)
        println("main10 test blocking")
    }
//    delay(8000L)
    println("main10 test blocking delay")
}

fun main11() = GlobalScope.launch {
    delay(1000L)
    println("main11 test blocking")

    measureTimeMillis {
        1
    }
    test("q") { a: Int ->  println("inline: ${a * 2}")
    }
}

private inline fun test(a: String, block: (Int) -> Unit) {
    println("inline test: $a")
    block(1)
}
