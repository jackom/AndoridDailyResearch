package com.example.testdemos.strategyfactory

import androidx.collection.ArrayMap

/**
 * @author : zhengminxin
 * @date : 4/20/2020 4:14 PM
 * @desc :
 */
class CompressorStrategyFactory : IStrategyFactory {

    //kotlin项目中，引入ArrayMap为 android.util包时，会报错：Exception in thread "main" java.lang.ExceptionInInitializerError
    private val strategyMap = ArrayMap<Class<out ResourceFile>, CompressorStrategy>()

    init {
        strategyMap[PdfFile::class.java] = PdfCompressorStrategy()
        strategyMap[WordFile::class.java] = WordCompressorStrategy()
        strategyMap[ExcelFile::class.java] = ExcelCompressorStrategy()
    }

    public fun getCompressorStrategy(resourceFile: Class<out ResourceFile>): CompressorStrategy? {
        return strategyMap[resourceFile]
    }

}