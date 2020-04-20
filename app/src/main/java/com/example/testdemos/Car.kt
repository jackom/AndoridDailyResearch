package com.example.testdemos

/**
 *  author : zhengminxin
 *  date : 2019/7/31 17:01
 *  desc :
 */

class Car (val model: String?, val year: Int) {
    private constructor(builder: Builder) : this(builder.model, builder.year)

    class Builder {
        var model: String? = null
        var year: Int = -1

        fun build() = Car(this)
    }

    companion object {
        inline fun build(block: Builder.() -> Unit) = Builder().apply(block).build()
    }
}