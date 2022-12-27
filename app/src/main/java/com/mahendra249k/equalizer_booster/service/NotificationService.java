package com.mahendra249k.equalizer_booster.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.audiofx.BassBoost;
import android.media.audiofx.Equalizer;
import android.media.audiofx.LoudnessEnhancer;
import android.media.audiofx.Virtualizer;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import androidx.core.app.NotificationCompat;

import com.mahendra249k.equalizer_booster.utils.Constants;
import com.mahendra249k.equalizer_booster.R;
import com.mahendra249k.equalizer_booster.activity.EqualizerActivity;

import java.util.Objects;

public class NotificationService extends Service {
    private static volatile BassBoost bassBoostInstance;
    private static volatile Equalizer equalizerInstance;
    private static volatile LoudnessEnhancer loudnessEnhancerInstance;
    private static volatile Virtualizer virtualizerInstance;
    String FOREGROUND_SERVICE_CHANNEL = "FOREGROUNDSERVICE_CHANNEL";

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        if (!(intent == null || intent.getAction() == null)) {
            Log.e("intent.getAction()", "" + intent.getAction());
            if (Objects.equals(intent.getAction(), Constants.ACTION.STARTFOREGROUND_ACTION)) {
                Intent intent2 = new Intent(getApplicationContext(), EqualizerActivity.class);
                intent2.setAction("android.intent.action.MAIN");
                intent2.addCategory("android.intent.category.LAUNCHER");
                startForeground(101, new NotificationCompat.Builder((Context) this, this.FOREGROUND_SERVICE_CHANNEL).setSmallIcon((int) R.mipmap.ic_launcher).setContentTitle(getString(R.string.app_name)).setContentText(getString(R.string.TapToOpenSettings)).setContentIntent(PendingIntent.getActivity(getApplicationContext(), 0, intent2, PendingIntent.FLAG_IMMUTABLE)).setPriority(1).setOngoing(true).build());
                Log.i("WOW", "BUILDED NOTIFICATION ");
            } else if (Objects.equals(intent.getAction(), Constants.ACTION.STOPFOREGROUND_ACTION)) {
                Log.i("Service", "Received Stop Foreground Intent");
                stopForeground(true);
                stopSelf();
            }
        }
        return Service.START_STICKY;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel(this.FOREGROUND_SERVICE_CHANNEL, "Equilizer", NotificationManager.IMPORTANCE_LOW);
            notificationChannel.setDescription("Equilizer is enabled");
            ((NotificationManager) getSystemService(NotificationManager.class)).createNotificationChannel(notificationChannel);
        }
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

    public void onDestroy() {
        super.onDestroy();
        Log.i("Service", "In onDestroy");
    }
}
