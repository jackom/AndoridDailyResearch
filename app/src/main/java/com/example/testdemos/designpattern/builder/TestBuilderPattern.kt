package com.example.demotest.designpattern.builder

/**
 * @date：2020-02-20 11:28
 * @desc：Builder模式
 * @author：jackom
 */


class ConstructorArg private constructor(builder: Builder) {
    private val isRef = builder.isRef
    private val type: Class<*>? = builder.type
    private val arg: Any? = builder.arg

    fun test() {
        print("test......")
    }

    internal class Builder {
        internal var isRef = false
        internal var type: Class<*>? = null
        internal var arg: Any? = null

        fun setRef(isRef: Boolean): Builder {
            this.isRef = isRef
            return this
        }

        fun setType(type: Class<*>): Builder {
            if (isRef && arg == REF_BEAN_ID) {
                throw IllegalArgumentException("type cannot be set!")
            }

            this.type = type
            return this
        }

        fun setArg(arg: Any): Builder {
            this.arg = arg
            return this
        }

        fun build(): ConstructorArg {
            if (!isRef && (arg == null || type == null)) {
                throw IllegalArgumentException("arg and type variable should not be null!")
            }
            return ConstructorArg(this)
        }
    }

    companion object {
        const val REF_BEAN_ID = "refBeanId"
    }


}