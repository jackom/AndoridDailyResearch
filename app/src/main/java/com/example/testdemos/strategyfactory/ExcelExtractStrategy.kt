package com.example.testdemos.strategyfactory

/**
 * @author : zhengminxin
 * @date : 4/20/2020 4:13 PM
 * @desc :
 */
class ExcelExtractStrategy : ExtractStrategy {

    override fun visit(resourceFile: ResourceFile) {
        println("ExcelExtractStrategy visit: ${resourceFile is ExcelFile}")
    }

}