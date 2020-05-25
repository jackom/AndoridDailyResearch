package com.example.testdemos

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import java.util.concurrent.Executors

/**
 *  author : zhengminxin
 *  date : 2019/7/11 11:59
 *  desc :
 */
class ThirdActivity : AppCompatActivity() {

    private lateinit var btn: Button

    private val name: String by lazy {
//        "executed only first time"
        "Double Thunder"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll)

        println(name)
        println(name)
//        testCoroutine()

        btn = findViewById(R.id.btn)
        btn.setOnClickListener {
//            onTestClick()
//            test()
//            testDeepLink()
        }

        var a: String? = null
        a?.let {
            a = "2".plus(4567)
        }?:let {
            a = "1"
        }

        println("onCreate----$a")

//        for (i in 0 until 20) {
//            val threadFactory = Executors.defaultThreadFactory()
//            threadFactory.toString()
//        }

        testStringFormat()
    }

    private fun testStringFormat() {
        val appName: String = resources.getString(R.string.app_name)
        val str = String.format(resources.getString(R.string.txt_user_trust_content), null)
        println("字符串format测试： \n$str")
    }

    private fun testDeepLink() {
        val url = "novel://quyuansu.com:/main"
        val intent = Intent()
        intent.action = "android.intent.action.VIEW"
        val uri = Uri.parse(url)
        intent.data = uri
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    private fun test() {
        var a: String? = null
        runBlocking {
            println("test AAA: ")
            GlobalScope.launch(Dispatchers.Main) {
                a = GlobalScope.async(Dispatchers.IO) {
                    delay(20000L)
                    return@async "123"
                }.await()
            }
        }
        println("test: $a")
    }

    private fun onTestClick() = runBlocking {

//        val data = GlobalScope.async(Dispatchers.IO) {
//            return@async getTime()
//        }
//        println("Dispatchers.Main: ${data.await()}")
        GlobalScope.launch(Dispatchers.Main) {
            btn.text = GlobalScope.async(Dispatchers.IO) {
                return@async getTime()
            }.await()
            println("Dispatchers.Main: ")
        }
    }

    private suspend fun getTime(): String {
        delay(20000L)
        return "abc"
    }

    private fun testCoroutine() {
        main10()
        println("AAA")

        main11()
        println("BBB")
    }
}