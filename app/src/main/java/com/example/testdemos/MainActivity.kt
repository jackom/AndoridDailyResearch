package com.example.testdemos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.example.testdemos.lifecycledemo.MyObserver
import com.example.testdemos.lifecycledemo.SecondActivity
import com.example.testdemos.synchronizeddemo.Count
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.Executors


class MainActivity : AppCompatActivity() {

    private val TAG = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv = findViewById<TextView>(R.id.tv)
        tv.setOnClickListener { jump() }

        val a = 2
        Log.d(TAG, "onCreate---------$a------------------")

        lifecycle.addObserver(MyObserver())

    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause---------------------------")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy---------------------------")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart---------------------------")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume---------------------------")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop---------------------------")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart---------------------------")
    }

    private fun jump() {
        val intent = Intent(this, SecondActivity().javaClass)
        startActivity(intent)
    }


}
