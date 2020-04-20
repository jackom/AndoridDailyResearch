package com.example.testdemos.responsibility

/**
 * @author : zhengminxin
 * @date : 3/31/2020 10:28 AM
 * @desc : 处理器链
 */
class HandelrChain {

    private var head: ResponsibilityHandler? = null
    private var tail: ResponsibilityHandler? = null

    fun addHandler(handler: ResponsibilityHandler) {
        handler.setHandler(null)

        if (null == head) {
            head = handler
            tail = handler
            return
        }

        tail?.setHandler(handler)
        tail = handler
    }

    fun handle() {
        head?.handle()
    }

}