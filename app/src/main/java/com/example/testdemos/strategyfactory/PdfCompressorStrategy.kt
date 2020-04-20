package com.example.testdemos.strategyfactory

/**
 * @author : zhengminxin
 * @date : 4/20/2020 4:20 PM
 * @desc :
 */
class PdfCompressorStrategy : CompressorStrategy {

    override fun visit(resourceFile: ResourceFile) {
        println("PdfCompressorStrategy visit: ${resourceFile is PdfFile}")
    }
}