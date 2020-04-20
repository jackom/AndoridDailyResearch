package com.example.testdemos.responsibility

/**
 * @author : zhengminxin
 * @date : 3/31/2020 10:39 AM
 * @desc :
 */
fun main() {
    val chain = HandelrChain()
    chain.addHandler(HandlerA())
    chain.addHandler(HandlerB())
    chain.addHandler(HandlerC())
    chain.handle()
}