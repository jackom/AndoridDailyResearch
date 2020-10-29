package cn.sirius.adsdk.demo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import cn.sirius.adsdk.utils.ToastUtil;
import cn.sirius.nga.NGASDK;
import cn.sirius.nga.NGASDKFactory;
import cn.sirius.nga.properties.NGABannerController;
import cn.sirius.nga.properties.NGABannerListener;
import cn.sirius.nga.properties.NGABannerProperties;
import cn.sirius.nga.properties.NGAdController;

public class BannerActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "BannerActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_control);

        //全屏
        //View decorView = getWindow().getDecorView();
        //int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
        //decorView.setSystemUiVisibility(uiOptions);
    }

    private NGABannerController mController;
    private NGABannerProperties mProperties;
    private ViewManager mWindowManager;
    private RelativeLayout mBannerView;

    //注意：请在Activity成员变量保存，使用匿名内部类可能导致回收
    NGABannerListener mAdListener = new NGABannerListener() {
        @Override
        public void onRequestAd() {
            ToastUtil.show(TAG, "onRequestAd");
        }

        @Override
        public <T extends NGAdController> void onReadyAd(T controller) {
            mController = (NGABannerController) controller;
            ToastUtil.show(TAG, "onReadyAd");
            showAd(BannerActivity.this);
        }

        @Override
        public void onShowAd() {
            ToastUtil.show(TAG, "onShowAd");
        }

        @Override
        public void onCloseAd() {
            //广告关闭之后mController置null，鼓励加载广告重新调用loadAd，提高广告填充率
            mController = null;
            mBannerView.setVisibility(View.GONE);
            ToastUtil.show(TAG, "onCloseAd");
        }

        @Override
        public void onErrorAd(int code, String message) {
            ToastUtil.show(TAG, "onErrorAd errorCode:" + code + ", message:" + message);
        }

        @Override
        public void onClickAd() {
            ToastUtil.show(TAG, "onClickAd");
        }
    };

    //为了提高广告的填充率以及曝光量，建议重新加载广告时候重新调用此方法，重新请求新的广告内容
    private void loadAd(Activity activity) {
        if (mBannerView != null && mBannerView.getParent() != null) {
            mWindowManager.removeView(mBannerView);
        }
        mBannerView = new RelativeLayout(activity);

        //此代码是为了显示广告区域，游戏请根据游戏主题背景决定是否要添加
        mBannerView.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));

        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.BOTTOM | Gravity.CENTER;
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        //如果你的应用是竖屏，且隐藏了虚拟按键，请设置为下面的flag
        //params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | LayoutParams.FLAG_TRANSLUCENT_NAVIGATION
        mWindowManager = (WindowManager) activity.getSystemService(WINDOW_SERVICE);
        mWindowManager.addView(mBannerView, params);

        mProperties = new NGABannerProperties(activity, AdConfig.appId, AdConfig.bannerPosId, mBannerView);
        mProperties.setListener(mAdListener);
        NGASDK ngasdk = NGASDKFactory.getNGASDK();
        ngasdk.loadAd(mProperties);

        // 若需要默认横幅广告不展示
        mBannerView.setVisibility(View.GONE);
    }

    private void destroyAd(Activity activity) {
        if (mWindowManager != null) {
            mWindowManager.removeView(mBannerView);
            mWindowManager = null;
        }
        if (mController != null) {
            mController.closeAd();
            mController = null;
        }
    }

    private void showAd(Activity activity) {
        if (mController != null) {
            mController.showAd();
            mBannerView.setVisibility(View.VISIBLE);
        }
    }

    private void closeAd(Activity activity) {
        if (mController != null) {
            mBannerView.setVisibility(View.GONE);
            mController.closeAd();
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_banner_create) {
            loadAd(this);
        } else if (view.getId() == R.id.btn_banner_destroy) {
            destroyAd(this);
        } else if (view.getId() == R.id.btn_banner_show) {
            showAd(this);
        } else if (view.getId() == R.id.btn_banner_close) {
            closeAd(this);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        destroyAd(this);
    }
}
