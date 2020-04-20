package com.example.testdemos.synchronizeddemo

class Count : Runnable {
    private var sCount = 0

    fun getCount(): Int = sCount

    override fun run() {
        for (i in 0..9999) {
            sCount++
        }
    }

}