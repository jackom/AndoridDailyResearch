package com.example.testdemos.responsibility

/**
 * @author : zhengminxin
 * @date : 3/31/2020 10:30 AM
 * @desc :
 */
abstract class ResponsibilityHandler {

    private var successor: ResponsibilityHandler? = null

    fun setHandler(handler: ResponsibilityHandler?) {
        successor = handler
    }

    fun handle() {
        val isHandled = doHandle()
        if (!isHandled && null != successor) {
            successor!!.handle()
        }
    }

    abstract fun doHandle(): Boolean

}