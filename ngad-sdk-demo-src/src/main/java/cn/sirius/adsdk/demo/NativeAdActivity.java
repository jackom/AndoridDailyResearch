package cn.sirius.adsdk.demo;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import cn.sirius.adsdk.utils.LoadImageUtil;
import cn.sirius.adsdk.utils.ToastUtil;
import cn.sirius.nga.NGASDK;
import cn.sirius.nga.NGASDKFactory;
import cn.sirius.nga.properties.NGANativeAdData;
import cn.sirius.nga.properties.NGANativeController;
import cn.sirius.nga.properties.NGANativeListener;
import cn.sirius.nga.properties.NGANativeProperties;
import cn.sirius.nga.properties.NGAdController;

/**
 * Created by gz on 2018/6/7.
 */

public class NativeAdActivity extends BaseActivity {
    private static final String TAG = "NativeAdActivity";

    private NGANativeProperties mProperties;
    private NGANativeController mController;

    private NGANativeAdData mAdDataItem;

    private LinearLayout mMainContainer;
    private RelativeLayout mAdContainer;
    private ImageView mIvAppIcon;
    private TextView mTvAppTitle;
    private TextView mTvAppDesc;
    private TextView mTvAppScore;
    private ImageView mIvAppImg;
    private TextView mBtnClick;
    private ImageView mIvAdLogo;

    //注意：请在Activity成员变量保存，使用匿名内部类可能导致回收
    NGANativeListener mAdListener = new NGANativeListener() {

        @Override
        public void onAdStatusChanged(NGANativeAdData ngaNativeAd, int i, Map<String, String> map) {
            Log.i(TAG, "onAdStatusChanged " + i);
        }

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
            mController = (NGANativeController) controller;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_native);
        mMainContainer = (LinearLayout) findViewById(R.id.rl_control_container);
    }

    private void refreshAdContainer() {
        if (mAdContainer != null) {
            mMainContainer.removeView(mAdContainer);
        }
        mAdContainer = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.native_ad_contaner, null, false);
        mIvAppIcon = (ImageView) mAdContainer.findViewById(R.id.iv_app_icon);
        mTvAppTitle = (TextView) mAdContainer.findViewById(R.id.tv_app_title);
        mTvAppDesc = (TextView) mAdContainer.findViewById(R.id.tv_app_desc);
        mTvAppScore = (TextView) mAdContainer.findViewById(R.id.tv_app_score);
        mIvAppImg = (ImageView) mAdContainer.findViewById(R.id.iv_app_img);
        mBtnClick = (TextView) mAdContainer.findViewById(R.id.btn_app_click);
        mIvAdLogo = (ImageView) mAdContainer.findViewById(R.id.iv_ad_logo);
        mMainContainer.addView(mAdContainer);
    }

    private void loadAd(Activity activity) {
        //如果使用原有的容器再次请求广告，必须关闭原有的广告
        closeAd(activity);
        //每次加载广告必须使用新的广告容器view
        refreshAdContainer();
        Map<String, Object> param = new HashMap<>();
        //一次加载所提供的广告数量。不一定能够给到传入的数量，请以最终返回的广告数量为准
        param.put(NGANativeProperties.KEY_AD_COUNT, 1);
        param.put(NGANativeProperties.APP_ID, AdConfig.appId);
        param.put(NGANativeProperties.POSITION_ID, AdConfig.nativeId);
        mProperties = new NGANativeProperties(activity, param);
        mProperties.setListener(mAdListener);
        NGASDK ngasdk = NGASDKFactory.getNGASDK();
        ngasdk.loadAd(mProperties);
    }

    public void destroyAd(Activity activity) {
        if (mController != null) {
            mController.closeAd();
            mController = null;
        }
    }

    private void showAd(Activity activity) {
        if (mController != null) {
            mAdDataItem = mController.getAdList().get(0);
            new LoadImageUtil(mAdDataItem.getIconUrl()){
                @Override
                public void onReceived(Drawable result) {
                    mIvAppIcon.setImageDrawable(result);
                    //广告曝光后，一定一定要调用该方法，否则无法计算曝光数量
                    mAdDataItem.exposure(mAdContainer);
                }
            }.execute();
            new LoadImageUtil(mAdDataItem.getImgList().get(0)){
                @Override
                public void onReceived(Drawable result) {
                    mIvAppImg.setImageDrawable(result);
                }
            }.execute();
            //根据相关规定，广告必须要有广告标识，请将该广告logo放置在广告右下角
            new LoadImageUtil(mAdDataItem.getAdLogo()) {
                @Override
                public void onReceived(Drawable result) {
                    mIvAdLogo.setImageDrawable(result);
                }
            }.execute();
            mTvAppTitle.setText(mAdDataItem.getTitle());
            mTvAppDesc.setText(mAdDataItem.getDesc());
            if (mAdDataItem.getRating() > 0) {
                mTvAppScore.setText(getBaseContext().getString(R.string.ngad_sdk_demo_rating_key) + mAdDataItem.getRating());
            } else {
                mTvAppScore.setText(getBaseContext().getString(R.string.ngad_sdk_demo_no_rating));
            }
            mBtnClick.setText(mAdDataItem.getButtonText());
            mBtnClick.setBackgroundColor(Color.GRAY);
        }
    }

    private void closeAd(Activity activity) {
        if (mController != null) {
            mController.closeAd();
            mController = null;
        }
    }

    @Override
    protected void onDestroy() {
        //原生广告必须调用关闭，否则影响广告计费
        closeAd(this);
        super.onDestroy();
    }

    public void onClick(View view) {
        if (view.getId() == R.id.btn_native_create) {
            loadAd(this);
        } else if (view.getId() == R.id.btn_native_destroy) {
            destroyAd(this);
        } else if (view.getId() == R.id.btn_native_show) {
            showAd(this);
        } else if (view.getId() == R.id.btn_native_close) {
            closeAd(this);
        }
    }
}
