package com.example.testdemos.responsibility

/**
 * @author : zhengminxin
 * @date : 3/31/2020 10:38 AM
 * @desc : 处理器 A
 */
class HandlerB : ResponsibilityHandler() {

    override fun doHandle(): Boolean {
        var success = false
        print("HandlerB execute doHandle method!\n")
        return success
    }
}