package com.example.testdemos.responsibility

/**
 * @author : zhengminxin
 * @date : 3/31/2020 10:36 AM
 * @desc : 处理器 A
 */
class HandlerA : ResponsibilityHandler() {

    override fun doHandle(): Boolean {
        var success = false
        print("HandlerA execute doHandle method!\n")
        return success
    }
}