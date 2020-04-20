package com.example.testdemos.strategyfactory

/**
 * @author : zhengminxin
 * @date : 4/20/2020 3:00 PM
 * @desc :
 */
class ExtractVisitor : Visitor {

    override fun visit(resourceFile: ResourceFile) {
        println("ExtractVisitor visit finished!")
    }

}