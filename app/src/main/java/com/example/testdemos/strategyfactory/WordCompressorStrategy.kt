package com.example.testdemos.strategyfactory

/**
 * @author : zhengminxin
 * @date : 4/20/2020 4:20 PM
 * @desc :
 */
class WordCompressorStrategy : CompressorStrategy {

    override fun visit(resourceFile: ResourceFile) {
        println("WordCompressorStrategy visit: ${resourceFile is WordFile}")
    }
}