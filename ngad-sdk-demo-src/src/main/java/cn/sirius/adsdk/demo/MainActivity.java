

package cn.sirius.adsdk.demo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * <p>Title: Demo 主页</p>
 *
 * <p>Description: </p>
 * 包含：横幅、插屏、信息流、视频广告示例入口
 * <p/>
 * <p>Copyright (c) 2015-present, Alibaba, Inc. All rights reserved. </p>
 * @author Junkun <junkun.hjk@alibaba-inc.com>
 * @date 17-5-5 下午3:28.
 *
 */

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, AdConfig.toStringFormat());
        findViewById(R.id.banner).setOnClickListener(this);
        findViewById(R.id.insert).setOnClickListener(this);
        findViewById(R.id.video).setOnClickListener(this);
        findViewById(R.id.welcome).setOnClickListener(this);
        findViewById(R.id.template).setOnClickListener(this);
        findViewById(R.id.nativeAd).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.banner) {
            startActivity(new Intent(MainActivity.this, BannerActivity.class));
        } else if (view.getId() == R.id.insert) {
            startActivity(new Intent(MainActivity.this, InsertActivity.class));
        } else if (view.getId() == R.id.video) {
            startActivity(new Intent(MainActivity.this, VideoActivity.class));
        } else if (view.getId() == R.id.welcome) {
            startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
        } else if (view.getId() == R.id.template) {
            startActivity(new Intent(MainActivity.this, TemplateActivity.class));
        } else if (view.getId() == R.id.nativeAd) {
            startActivity(new Intent(MainActivity.this, NativeAdActivity.class));
        }
    }

}
