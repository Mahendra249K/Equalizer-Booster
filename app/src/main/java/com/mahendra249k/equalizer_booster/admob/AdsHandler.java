package com.mahendra249k.equalizer_booster.admob;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;

import com.mahendra249k.equalizer_booster.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class AdsHandler {
    String a = "/6499/example/banner";
    String b = "/6499/example/interstitial";
    public Dialog loadAdsDialog;


    public AdsHandler(Activity activity) {
        MobileAds.initialize(activity, initializationStatus -> {
        });
    }

    public void BannerAd(final RelativeLayout Ad_Layout, Activity activity) {
        final AdView mAdView = new AdView(activity);
        mAdView.setAdSize(AdSize.BANNER);
        mAdView.setAdUnitId(this.a);
        mAdView.loadAd(new AdRequest.Builder().build());
        Ad_Layout.addView(mAdView);
        mAdView.setAdListener(new AdListener() {
            public void onAdLoaded() {
                Ad_Layout.setVisibility(View.VISIBLE);
                super.onAdLoaded();
                Log.e("ddddd", "dddd");
            }

            public void onAdOpened() {
                super.onAdOpened();
                Ad_Layout.setVisibility(View.GONE);
                Log.e("ddddd1", "dddd");
            }

            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                mAdView.destroy();
                Ad_Layout.setVisibility(View.GONE);
                Log.e("ddddd2", "dddd" + loadAdError.getMessage());
            }
        });
    }

    public void FullscreenAd(final Activity activity) {
        Ad_Popup(activity);
        InterstitialAd.load(activity, this.b, new AdRequest.Builder().build(), new InterstitialAdLoadCallback() {
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                interstitialAd.show(activity);
                AdsHandler.this.loadAdsDialog.dismiss();
            }

            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                AdsHandler.this.loadAdsDialog.dismiss();
            }
        });
    }

    private void Ad_Popup(Context mContext) {
        loadAdsDialog = new Dialog(mContext);
        loadAdsDialog.setContentView(R.layout.layout_load);
        loadAdsDialog.setCanceledOnTouchOutside(false);
        loadAdsDialog.setCancelable(false);
        loadAdsDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        loadAdsDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        loadAdsDialog.getWindow().getAttributes().windowAnimations = android.R.style.Animation_Dialog;
        loadAdsDialog.show();
    }
}
