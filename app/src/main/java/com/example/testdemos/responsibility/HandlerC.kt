package com.example.testdemos.responsibility

/**
 * @author : zhengminxin
 * @date : 3/31/2020 11:23 AM
 * @desc : 处理器 C
 */
class HandlerC : ResponsibilityHandler() {

    override fun doHandle(): Boolean {
        var success = false
        print("HandlerC execute doHandle method!\n")
        return success
    }
}