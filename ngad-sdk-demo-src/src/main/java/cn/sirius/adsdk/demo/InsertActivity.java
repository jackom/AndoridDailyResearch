package cn.sirius.adsdk.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import cn.sirius.adsdk.utils.ToastUtil;
import cn.sirius.nga.NGASDK;
import cn.sirius.nga.NGASDKFactory;
import cn.sirius.nga.properties.NGAInsertController;
import cn.sirius.nga.properties.NGAInsertListener;
import cn.sirius.nga.properties.NGAInsertProperties;
import cn.sirius.nga.properties.NGAdController;


public class InsertActivity extends BaseActivity {
    private static final String TAG = "InsertActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_control);

    }

    private NGAInsertProperties mProperties;
    private NGAInsertController mController;

    //注意：请在Activity成员变量保存，使用匿名内部类可能导致回收
    NGAInsertListener mAdListener = new NGAInsertListener() {

        @Override
        public void onShowAd() {
            ToastUtil.show(TAG, "onShowAd");
        }


        @Override
        public void onRequestAd() {
            ToastUtil.show(TAG, "onRequestAd");
        }

        @Override
        public <T extends NGAdController> void onReadyAd(T controller) {
            mController = (NGAInsertController) controller;
            ToastUtil.show(TAG, "onReadyAd");
        }

        @Override
        public void onCloseAd() {
            ToastUtil.show(TAG, "onCloseAd");
            mController = null;
        }

        @Override
        public void onClickAd() {
            ToastUtil.show(TAG, "onClickAd");
        }

        @Override
        public void onErrorAd(int code, String message) {
            ToastUtil.show(TAG, "onErrorAd errorCode:" + code + ", message:" + message);
        }

    };

    //为了提高广告的填充率以及曝光量，建议重新加载广告时候重新调用此方法，重新请求新的广告内容
    private void loadAd(Activity activity) {
        mProperties = new NGAInsertProperties(activity, AdConfig.appId, AdConfig.insertPosId, null);
        mProperties.setListener(mAdListener);
        NGASDK ngasdk = NGASDKFactory.getNGASDK();
        ngasdk.loadAd(mProperties);
    }

    public void destroyAd(Activity activity) {
        if (mController != null) {
            mController.cancelAd();
            mController.closeAd();
            mController = null;
        }
    }

    private void showAd(Activity activity) {
        if (mController != null) {
            mController.showAd();
        }
    }

    private void closeAd(Activity activity) {
        if (mController != null) {
            //mController.show(false);
            mController.cancelAd();
            mController.closeAd();
        }
    }

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
}
