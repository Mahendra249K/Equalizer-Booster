package com.mahendra249k.equalizer_booster.utils;

import android.app.Application;
import android.media.audiofx.BassBoost;
import android.media.audiofx.Equalizer;
import android.media.audiofx.LoudnessEnhancer;
import android.media.audiofx.Virtualizer;
import android.util.Log;

public class EffectInstance extends Application {
    private static final String TAG = "EffectInstance";
    private static volatile BassBoost bassBoostInstance;
    private static volatile Equalizer equalizerInstance;
    private static volatile LoudnessEnhancer loudnessEnhancerInstance;
    private static volatile Virtualizer virtualizerInstance;

    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: EFFECTINSTANCE CREATED");
    }

    public static Equalizer b() {
        if (equalizerInstance == null) {
            equalizerInstance = new Equalizer(Integer.MAX_VALUE, 0);
        }
        return equalizerInstance;
    }

    public static BassBoost a() {
        if (bassBoostInstance == null) {
            bassBoostInstance = new BassBoost(Integer.MAX_VALUE, 0);
        }
        return bassBoostInstance;
    }

    public static Virtualizer d() {
        if (virtualizerInstance == null) {
            try {
                virtualizerInstance = new Virtualizer(Integer.MAX_VALUE, 0);
            } catch (Exception e) {
            }
        }
        return virtualizerInstance;
    }

    public static LoudnessEnhancer c() {
        if (loudnessEnhancerInstance == null) {
            loudnessEnhancerInstance = new LoudnessEnhancer(0);
        }
        return loudnessEnhancerInstance;
    }
}
