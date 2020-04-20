package com.example.testdemos.strategyfactory

/**
 * @author : zhengminxin
 * @date : 4/20/2020 3:08 PM
 * @desc :
 */

fun main() {
    val extractStrategyName = "Extract"
    val compressorStrategyName = "Compressor"

    val extractStrategyFactory = StrategyFactory.getStrategyFactory(extractStrategyName) as ExtractStrategyFactory
    val compressorStrategyFactory = StrategyFactory.getStrategyFactory(compressorStrategyName) as CompressorStrategyFactory

    val resourceFiles: List<ResourceFile> = listAllResourceFiles()
    for (element in resourceFiles) {

        val extractStrategy = extractStrategyFactory.getExtractStrategy(element::class.java)
        extractStrategy?.visit(element)

        val compressorStrategy = compressorStrategyFactory.getCompressorStrategy(element::class.java)
        compressorStrategy?.visit(element)
    }

}

fun listAllResourceFiles(): List<ResourceFile> {
    val resourceFiles = mutableListOf<ResourceFile>()
    //...根据后缀(pdf/ppt/word)由工厂方法创建不同的类对象(PdfFile/PPTFile/WordFile)
    resourceFiles.add(PdfFile("a.pdf"))
    resourceFiles.add(WordFile("b.word"))
    resourceFiles.add(ExcelFile("c.xlsx"))
    return resourceFiles
}

