package cn.sirius.adsdk.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import cn.sirius.adsdk.utils.ToastUtil;
import cn.sirius.nga.NGASDK;
import cn.sirius.nga.NGASDKFactory;
import cn.sirius.nga.properties.NGAVideoController;
import cn.sirius.nga.properties.NGAVideoListener;
import cn.sirius.nga.properties.NGAVideoProperties;
import cn.sirius.nga.properties.NGAdController;

public class VideoActivity extends BaseActivity {
	private static final String TAG = "VideoActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video_ad_control);
	}

	private NGAVideoController mController;
	NGAVideoListener mAdListener = new NGAVideoListener() {

        @Override
        public void onShowAd() {
			ToastUtil.show(TAG, "onShowAd");
        }

        @Override
        public void onClickAd() {
			ToastUtil.show(TAG, "onClickAd");
        }

        @Override
		public void onCloseAd() {
			mController = null;
			ToastUtil.show(TAG, "onCloseAd");
		}

		@Override
		public void onErrorAd(final int code, final String message) {
        	mController = null;
			ToastUtil.show(TAG, String.format("onErrorAd code=%s, message=%s", code, message));
		}

		@Override
		public void onRequestAd() {
			ToastUtil.show(TAG, "onRequestAd");
		}

		@Override
		public <T extends NGAdController> void onReadyAd(T controller) {
			mController = (NGAVideoController) controller;
			ToastUtil.show(TAG, "onReadyAd");
		}

		@Override
		public void onCompletedAd() {
			ToastUtil.show(TAG, "onCompletedAd");
		}
	};

	//为了提高广告的填充率以及曝光量，建议重新加载广告时候重新调用此方法，重新请求新的广告内容
	public void loadAd(Activity activity) {
		final NGAVideoProperties properties = new NGAVideoProperties(activity, AdConfig.appId, AdConfig.videoPosId);
		properties.setListener(mAdListener);
		NGASDK ngasdk = NGASDKFactory.getNGASDK();
		ngasdk.loadAd(properties);
	}

	public void onClick(View view) {
		if (view.getId() == R.id.btn_video_ad_init) {
			loadAd(this);
		} else if (view.getId() == R.id.btn_video_ad_uninit) {
			if (mController != null) {
				mController.destroyAd();
			}
		} else if (view.getId() == R.id.btn_video_ad_has_cache) {
			if (mController != null) {
				boolean hasCacheAd = mController.hasCacheAd();
				ToastUtil.show(TAG, "hasCacheAd=" + hasCacheAd);
			}
		} else if (view.getId() == R.id.btn_video_ad_show) {
			if (mController != null) {
				mController.showAd();
			}
		}
	}
}
