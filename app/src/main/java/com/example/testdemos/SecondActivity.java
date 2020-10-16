package com.example.testdemos;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @date：2019-08-16 21:59
 * @desc：
 * @author：jackom
 */
public class SecondActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getAndroidId(this);


        List<String> data = new ArrayList<>();
        data.add("a");
        data.add("b");
        data.add("c");
        data.add(0, "10");
        System.out.println(data);
    }


    public static String getAndroidId(Context paramContext)
    {
        String aa = Settings.Secure.getString(paramContext.getContentResolver(), Settings.Secure.ANDROID_ID);
        Log.i("123123", aa);
        return aa;
    }
}
