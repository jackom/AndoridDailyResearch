package cn.sirius.adsdk.demo;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by leo on 26/03/2018.
 */

public class SplashAdTemplate {

    private Context mContext;
    private ViewGroup mConvertView;
    private FrameLayout mAdContainer;

    public SplashAdTemplate(Context context) {
        mContext = context;
        inflate();
    }

    private void inflate() {
        if (mConvertView == null) {
            mConvertView = (ViewGroup) LayoutInflater.from(mContext).inflate(R.layout.adp_layout_splash_ad_template, null);
            mAdContainer = (FrameLayout) mConvertView.findViewById(R.id.adp_splash_container);
            ImageView ivAppIcon = (ImageView) mConvertView.findViewById(R.id.tv_game_icon);
            TextView tvAppName = (TextView) mConvertView.findViewById(R.id.tv_game_name);
            try {
                ApplicationInfo applicationInfo = mContext.getApplicationInfo();
                PackageManager packageManager = mContext.getPackageManager();
                CharSequence title = packageManager.getApplicationLabel(applicationInfo);
                Drawable icon = packageManager.getApplicationIcon(applicationInfo);
                tvAppName.setText(title);
                ivAppIcon.setBackgroundDrawable(icon);
            } catch (Throwable ignore) {
            }
        }
    }

    public ViewGroup getView() {
        return mConvertView;
    }

    public ViewGroup getAdContainer() {
        return mAdContainer;
    }
}
