package cn.sirius.adsdk.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.Map;

import cn.sirius.adsdk.utils.ToastUtil;
import cn.sirius.nga.NGASDK;
import cn.sirius.nga.NGASDKFactory;
import cn.sirius.nga.properties.NGAWelcomeListener;
import cn.sirius.nga.properties.NGAWelcomeProperties;
import cn.sirius.nga.properties.NGAdController;

import static cn.sirius.nga.NGASDKFactory.getNGASDK;

public class WelcomeActivity extends BaseActivity {
    private static final String TAG = "WelcomeActivity";

    private ViewGroup container;
    private ImageView splashHolder;

    @Override
    protected void onBeforeRequestPermission(Bundle savedInstanceState) {
        super.onBeforeRequestPermission(savedInstanceState);
        final SplashAdTemplate splashAdTemplate = new SplashAdTemplate(getApplicationContext());
        setContentView(splashAdTemplate.getView());
        container = splashAdTemplate.getAdContainer();
        splashHolder = findViewById(R.id.game_splash_holder);
    }

    @Override
    protected void onRequestPermissionSuccess() {
        super.onRequestPermissionSuccess();
//        closeAd();
        initAdSdk();
    }

    protected void initAdSdk() {
        initSdk(this, new NGASDK.InitCallback() {
            @Override
            public void success() {
                //NGASDK init success, and try to show splash ad.
                showAd(WelcomeActivity.this);
            }

            @Override
            public void fail(Throwable throwable) {
                ToastUtil.show(TAG, throwable.getMessage());
                //NGASDK init fail, and enter home page of app.
                canCloseAd = true;
                closeAd();
            }
        });
    }

    protected void initSdk(Activity activity, final NGASDK.InitCallback initCallback) {
        // 重新初始化sdk
        Log.d(TAG, AdConfig.toStringFormat());
        NGASDK ngasdk = NGASDKFactory.getNGASDK();
        Map<String, Object> args = new HashMap<>();
        args.put(NGASDK.APP_ID, AdConfig.appId);
        ngasdk.init(activity, args, initCallback);
    }

    private NGAWelcomeProperties properties;
    private boolean canCloseAd = false;
    //注意：请在Activity成员变量保存，使用匿名内部类可能导致回收
    NGAWelcomeListener mAdListener = new NGAWelcomeListener() {

        @Override
        public void onClickAd() {
            ToastUtil.show(TAG, "onClickAd");
        }

        @Override
        public void onErrorAd(int code, String message) {
            ToastUtil.show(TAG, "onErrorAd errorCode:" + code + ", message:" + message);
            canCloseAd = true;
            closeAd();
        }

        @Override
        public void onShowAd() {
            // 广告展示后一定要把预设的开屏图片隐藏起来
            splashHolder.setVisibility(View.INVISIBLE);
            ToastUtil.show(TAG, "onShowAd");
        }

        @Override
        public void onCloseAd() {
            //无论成功展示成功或失败都回调用该接口，所以开屏结束后的操作在该回调中实现
            ToastUtil.show(TAG, "onCloseAd");
            closeAd();
        }

        @Override
        public <T extends NGAdController> void onReadyAd(T controller) {
            // 开屏广告是闪屏过程自动显示不需要NGAdController对象，所以返回controller为null；
            ToastUtil.show(TAG, "onReadyAd");
        }

        @Override
        public void onRequestAd() {
            ToastUtil.show(TAG, "onRequestAd");
        }


        /**
         * 倒计时回调，返回广告还将被展示的剩余时间。
         * 通过这个接口，开发者可以自行决定是否显示倒计时提示，或者还剩几秒的时候显示倒计时
         *
         * @param millisUntilFinished 剩余毫秒数
         */
        @Override
        public void onTimeTickAd(long millisUntilFinished) {

        }
    };

    /**
     * 开始广告，建议：闪屏Activity显示后延迟再加载广告
     *
     * @param activity
     */
    public void showAd(Activity activity) {
        properties = new NGAWelcomeProperties(activity, AdConfig.appId, AdConfig.welcomeId, container);
        // 支持开发者自定义的跳过按钮。SDK要求skipContainer一定在传入后要处于VISIBLE状态，且其宽高都不得小于3x3dp。
        // 如果需要使用SDK默认的跳过按钮，可以选择上面两个构造方法。
        //properties.setSkipView(skipView);

        properties.setListener(mAdListener);
        NGASDK ngasdk = getNGASDK();
        ngasdk.loadAd(properties);
    }

    /**
     * 关闭广告，当通知广告失败{@link NGAWelcomeListener#onErrorAd} 或关闭事件时候调用 {@link NGAWelcomeListener#onCloseAd}
     */
    protected void closeAd() {
        // 如果是因为点击广告而关闭广告，则不能finish Activity
        if (canCloseAd) {
            toggleMainActivity();
            this.finish();
        } else {
            canCloseAd = true;
        }
    }

    protected void toggleMainActivity() {
        this.startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    protected void onPause() {
        super.onPause();
        canCloseAd = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 如果是因为点击广告而返回，则finish Activity
        if (canCloseAd) {
            toggleMainActivity();
            this.finish();
        }
        canCloseAd = true;
    }

    /**
     * 开屏页一定要禁止用户对返回按钮的控制，否则将可能导致用户手动退出了App而广告无法正常曝光和计费
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
