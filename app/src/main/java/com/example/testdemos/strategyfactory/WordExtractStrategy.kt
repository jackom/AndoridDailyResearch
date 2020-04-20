package com.example.testdemos.strategyfactory

/**
 * @author : zhengminxin
 * @date : 4/20/2020 4:13 PM
 * @desc :
 */
class WordExtractStrategy : ExtractStrategy {

    override fun visit(resourceFile: ResourceFile) {
        println("WordExtractStrategy visit: ${resourceFile is WordFile}")
    }

}