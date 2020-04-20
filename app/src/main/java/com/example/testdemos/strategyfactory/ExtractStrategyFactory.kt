package com.example.testdemos.strategyfactory

import androidx.collection.ArrayMap

/**
 * @author : zhengminxin
 * @date : 4/20/2020 4:14 PM
 * @desc :
 */
class ExtractStrategyFactory : IStrategyFactory {

    //kotlin项目中，引入ArrayMap为 android.util包时，会报错：Exception in thread "main" java.lang.ExceptionInInitializerError
    private var strategyMap = ArrayMap<Class<out ResourceFile>, ExtractStrategy>()

    init {
        strategyMap[PdfFile::class.java] = PdfExtractStrategy()
        strategyMap[WordFile::class.java] = WordExtractStrategy()
        strategyMap[ExcelFile::class.java] = ExcelExtractStrategy()
    }

    public fun getExtractStrategy(resourceFile: Class<out ResourceFile>): ExtractStrategy? {
        return strategyMap[resourceFile]
    }

}