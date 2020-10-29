package cn.sirius.adsdk.demo;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import cn.sirius.adsdk.utils.ToastUtil;
import cn.sirius.nga.NGASDK;
import cn.sirius.nga.NGASDKFactory;
import cn.sirius.nga.properties.NGAGeneralController;
import cn.sirius.nga.properties.NGAGeneralListener;
import cn.sirius.nga.properties.NGAGeneralProperties;
import cn.sirius.nga.properties.NGAdController;

public class TemplateActivity extends BaseActivity {

    private static final String TAG = "TemplateActivity";
    Map<String, String> mShowParam = new HashMap<>();
    RadioGroup mTemplateSelector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template);
        mTemplateSelector = (RadioGroup)findViewById(R.id.template_choice);
        //UI参数准备，具体参数名称请查阅文档
        mShowParam = new HashMap<>();
        //bgRes：弹窗背景框图片资源（模板1、模板2支持）
        mShowParam.put("bgRes", "drawable/bg");
        //dialogTextColor：弹窗文案文字颜色（仅模板2支持）
        mShowParam.put("dialogTextColor", "#edeef2");
        //front：弹窗文字字体资源（仅模板2支持，仅支持assets资源）
        mShowParam.put("front", "assets/heiti.ttf");
        //btnText：弹窗操作按钮文案（仅模板2支持）
        mShowParam.put("btnText", getResources().getString(R.string.ngad_sdk_demo_receive));
        //btnTextColor：弹窗操作按钮文案文字颜色（仅模板2支持）
        mShowParam.put("btnTextColor", "#36561f");
        //btnRes：弹窗操作按钮背景图片（仅模板2支持）
        mShowParam.put("btnRes", "drawable/button_bg");
    }

    private NGAGeneralProperties mProperties;
    private NGAGeneralController mController;

    //注意：请在Activity成员变量保存，使用匿名内部类可能导致回收
    NGAGeneralListener mAdListener = new NGAGeneralListener() {

        @Override
        public void onEvent(NGAdEvent event) {
            //具体eventId代表含义请查阅文档
            if (event.eventId == 1) {
                ToastUtil.show(TAG, "onGameBtnClick");
                //再次调用showAd(Map<String,String> param)，可以动态更换素材参数
                mShowParam.put("dialogText", getResources().getString(R.string.ngad_sdk_demo_receive));
                mController.showAd(mShowParam);
            }
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
            mController = (NGAGeneralController) controller;
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
        Map<String, Object> param = new HashMap<>();
        param.put(NGAGeneralProperties.APP_ID, AdConfig.appId);
        //不同广告位可以配置不同的模板效果，可以与我们联系进行选择
        param.put(NGAGeneralProperties.POSITION_ID, getSelectedPosId());
        param.put(NGAGeneralProperties.AD_TYPE, 20);
        mProperties = new NGAGeneralProperties(activity, null, param);
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
            mShowParam.put("dialogText", activity.getResources().getString(R.string.ngad_sdk_demo_gift_and_receive));
            mController.showAd(mShowParam);
        }
    }

    private void closeAd(Activity activity) {
        if (mController != null) {
            //mController.show(false);
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

    private String getSelectedPosId() {
        switch (mTemplateSelector.getCheckedRadioButtonId()) {
            case R.id.rb_temp_1st:
                return AdConfig.templateId;
            case R.id.rb_temp_2nd:
                return AdConfig.templateId_2;
            default:
                return "";
        }
    }
}
