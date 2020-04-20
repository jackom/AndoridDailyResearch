package com.example.testdemos

/**
 *  author : zhengminxin
 *  date : 2019/8/1 14:09
 *  desc :
 */
class EventsConst{

    companion object {

        open class BaseEvent<T> @JvmOverloads constructor(data: T? = null) {
            var data: T? = null

            init{
                this.data = data
            }
        }

        class StringEvent @JvmOverloads constructor(data : String? = null): BaseEvent<String>(data)
        class IntEvent @JvmOverloads constructor(data : Int? = null): BaseEvent<Int>(data)


        fun test(){
            StringEvent().data = "test"
            IntEvent().data = 0
        }
    }
}