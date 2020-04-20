package com.example.testdemos.strategyfactory

import androidx.collection.ArrayMap

/**
 * @author : zhengminxin
 * @date : 4/20/2020 2:38 PM
 * @desc :
 */
object StrategyFactory {

    //kotlin项目中，引入ArrayMap为 android.util包时，会报错：Exception in thread "main" java.lang.ExceptionInInitializerError
    private var strategyMap = ArrayMap<String, IStrategyFactory>()

    init {
        strategyMap["Compressor"] = CompressorStrategyFactory()
        strategyMap["Extract"] = ExtractStrategyFactory()
    }

    public fun getStrategyFactory(tag: String): IStrategyFactory? {
        return strategyMap[tag]
    }

}