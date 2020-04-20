package com.example.testdemos.strategyfactory

/**
 * @author : zhengminxin
 * @date : 4/20/2020 4:13 PM
 * @desc :
 */
class PdfExtractStrategy : ExtractStrategy {

    override fun visit(resourceFile: ResourceFile) {
        println("PdfExtractStrategy visit: ${resourceFile is PdfFile}")
    }

}