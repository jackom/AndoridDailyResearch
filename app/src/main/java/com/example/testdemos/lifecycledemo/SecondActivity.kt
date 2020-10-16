package com.example.testdemos.lifecycledemo

import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.domob.sdk.common.util.AdError
import com.domob.sdk.unionads.splash.UnionSplashAD
import com.domob.sdk.unionads.splash.UnionSplashAdListener
import com.example.testdemos.R

class SecondActivity : AppCompatActivity(), UnionSplashAdListener {

    private val TAG = javaClass.simpleName

    private var splashAD: UnionSplashAD? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        Log.d(TAG, "onCreate---------------------------")

        initSplashAd()
    }

    private fun initSplashAd() {
        val contentView = findViewById<FrameLayout>(R.id.splash_container)
        ////        "96AgVrTA0XHyooOBVu",
////                "A0200221003",
        //1109907379
//       3090383835744098
        ////        "96AgVrTA0XHyooOBVu",
////                "A0200221003",
        //1109907379
//       3090383835744098
        splashAD = UnionSplashAD(this, "96AgVrTA0XHyooOBVu", "A0200221003", this, 5000)
        splashAD!!.fetchAndShowIn(contentView)
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


    override fun onAdDismissed() {
        Log.d("unions_ads", "页面消失了")
    }

    override fun onNoAd(error: AdError) {
        Log.d("unions_ads", "code : " + error.errorCode + "  msg : " + error.errorMsg)
    }

    override fun onAdPresent() {
        Log.d("unions_ads", "展现了")
    }

    override fun onAdClicked() {
        Log.d("unions_ads", "点击了页面内容")
    }


}