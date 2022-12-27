package com.mahendra249k.equalizer_booster.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.mahendra249k.equalizer_booster.utils.Preferences;
import com.mahendra249k.equalizer_booster.activity.EqualizerActivity;

import java.util.Objects;

public class BootReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (Objects.equals(intent.getAction(), "android.intent.action.BOOT_COMPLETED") && Preferences.getInstance(context.getApplicationContext()).getAutoStartBoot()) {
            Intent intent2 = new Intent(context, EqualizerActivity.class);
            intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent2);
        }
    }
}
