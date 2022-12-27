package com.mahendra249k.equalizer_booster.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class Preferences {
    private static final String AD_FREE_VERSION = "AdFreeVersion";
    private static final String AGC = "agc";
    private static final String AGC_BBS = "agc_bbs";
    private static final String AGC_MODE = "agc_mode";
    private static final String AGC_YELLOW_FIRST_MOVE_CHECK = "agc_yellow_first_enable_check";
    private static final String AUTO_START_BOOT = "auto_start_boot";
    private static final String BATTERY_OPTIMIZATION_BUTTON_ENABLE = "battery_optimization_button_enable";
    private static final String BATTERY_STATUS_REJECT_COUNTER = "BatteryStatusRejectCounter";
    private static final String BBSLIDER = "bbslider";
    private static final String BBSWITCH = "bbswitch";
    private static final String BBS_FIRST_ENABLE_CHECK = "bbs_first_enable_check";
    private static final String BBS_PLUGIN = "bbs_plugin";
    private static final String DARK_THEME = "dark_theme";
    private static final String DB_LABELS_ALWAYS_PRESENT = "db_labels_always_present";
    private static final String DONT_SHOW_AGAING_WARNING_APPS = "DontShowAgainWarningApps";
    private static final String EQSWITCH = "eqswitch";
    private static final String FORCE_RUN_FLAG = "ForceRunFlag";
    private static final String FORCE_RUN_FLAG_WORKS_NOTIFIED = "ForceRunWorksNotified";
    private static final String GAIN_PLUGIN = "gain_plugin";
    private static final String IS_CUSTOM_SELECTED = "is_custom_selected";
    private static final String KEEPS_SERVICE_ALWAYS_ON = "keep_service_always_on";
    private static final String KEEPS_SERVICE_ALWAYS_ON_BONIFICATION = "keep_service_always_on_bonification";
    private static final String LAST_VERSION_MESSAGE = "last_version_message";
    private static final String LOUDSLIDER = "loudslider";
    private static final String LOUDSWITCH = "loudswitch";
    private static final String MANUAL_GAIN_VALUE = "manual_gain_value";
    private static final String ON_RES_ERROR = "OnResError";
    private static final String PROTECTED = "protected";
    private static final String PROTECTED_KB = "protected_kb";
    private static final String SLIDER0 = "slider0";
    private static final String SLIDER1 = "slider1";
    private static final String SLIDER2 = "slider2";
    private static final String SLIDER3 = "slider3";
    private static final String SLIDER4 = "slider4";
    private static final String SPINNER_POS = "spinnerpos";
    private static final String SPOTIFY_CONNECTION = "spotify_connection";
    private static final String TAG = "PreferenceUtil";
    private static final String TIPS_TRICKS_BUTTON_ENABLE = "tips_tricks_button_enable";
    private static final String TS_FIRST_RUN = "TS_First_Run";
    private static final String VIRSLIDER = "virslider";
    private static final String VIRSWITCH = "virswitch";
    private static final String VIRT_PLUGIN = "virtualizer_plugin";
    private static final String WAS_ON_RES_IN_ERROR_COUNT = "WasOnResInErrorCount";
    private static final String ZOOM_EQ_VAL = "zoom_eq_val";
    private static Preferences sInstance;
    private SharedPreferences mPref;

    private Preferences(Context context) {
        this.mPref = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static Preferences getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new Preferences(context.getApplicationContext());
        }
        return sInstance;
    }

    public void setSpinnerPos(int i) {
        SharedPreferences.Editor edit = this.mPref.edit();
        edit.putInt(SPINNER_POS, i);
        edit.apply();
    }

    public final int getSpinnerPos() {
        return this.mPref.getInt(SPINNER_POS, 0);
    }

    public void setBBSlider(int i) {
        SharedPreferences.Editor edit = this.mPref.edit();
        edit.putInt(BBSLIDER, i);
        edit.apply();
    }

    public final int getBBSlider() {
        return this.mPref.getInt(BBSLIDER, 0);
    }

    public void setVirSlider(int i) {
        SharedPreferences.Editor edit = this.mPref.edit();
        edit.putInt(VIRSLIDER, i);
        edit.apply();
        Log.d("FabioVirtual", "SET setVirSlider val: " + i);
    }

    public final int getVirSlider() {
        return this.mPref.getInt(VIRSLIDER, 0);
    }

    public void setLoudSlider(float f) {
        SharedPreferences.Editor edit = this.mPref.edit();
        edit.putFloat(LOUDSLIDER, f);
        edit.apply();
    }

    public final float getLoudSlider() {
        return this.mPref.getFloat(LOUDSLIDER, 0.0f);
    }

    @SuppressLint("LongLogTag")
    public void setEqSlider(int i, int i2) {
        SharedPreferences.Editor edit = this.mPref.edit();
        Log.d("FabioeditPreferenceUtil setEqSlider:", Integer.toString(i));
        if (i2 == 0) {
            edit.putInt(SLIDER0, i);
        } else if (i2 == 1) {
            edit.putInt(SLIDER1, i);
        } else if (i2 == 2) {
            edit.putInt(SLIDER2, i);
        } else if (i2 == 3) {
            edit.putInt(SLIDER3, i);
        } else if (i2 == 4) {
            edit.putInt(SLIDER4, i);
        }
        edit.apply();
    }

    public final int getEqSlider(int i) {
        if (i == 0) {
            return this.mPref.getInt(SLIDER0, 0);
        }
        if (i == 1) {
            return this.mPref.getInt(SLIDER1, 0);
        }
        if (i == 2) {
            return this.mPref.getInt(SLIDER2, 0);
        }
        if (i == 3) {
            return this.mPref.getInt(SLIDER3, 0);
        }
        if (i != 4) {
            return 0;
        }
        return this.mPref.getInt(SLIDER4, 0);
    }

    public void setEqSwitch(boolean z) {
        SharedPreferences.Editor edit = this.mPref.edit();
        edit.putBoolean(EQSWITCH, z);
        edit.apply();
    }

    public final boolean getEqSwitch() {
        return this.mPref.getBoolean(EQSWITCH, true);
    }

    public void setBBSwitch(boolean z) {
        SharedPreferences.Editor edit = this.mPref.edit();
        edit.putBoolean(BBSWITCH, z);
        edit.apply();
    }

    public final boolean getBBSwitch() {
        return this.mPref.getBoolean(BBSWITCH, false);
    }

    public void setVirSwitch(boolean z) {
        SharedPreferences.Editor edit = this.mPref.edit();
        edit.putBoolean(VIRSWITCH, z);
        edit.apply();
    }

    public final boolean getVirSwitch() {
        return this.mPref.getBoolean(VIRSWITCH, false);
    }

    public void setLoudSwitch(boolean z) {
        SharedPreferences.Editor edit = this.mPref.edit();
        edit.putBoolean(LOUDSWITCH, z);
        edit.apply();
    }

    public final boolean getIsCustomSelected() {
        return this.mPref.getBoolean(IS_CUSTOM_SELECTED, false);
    }

    public void setIsCustomSelected(boolean z) {
        SharedPreferences.Editor edit = this.mPref.edit();
        edit.putBoolean(IS_CUSTOM_SELECTED, z);
        edit.apply();
    }

    public final boolean getLoudSwitch() {
        return this.mPref.getBoolean(LOUDSWITCH, false);
    }

    public void setDarkTheme(boolean z) {
        SharedPreferences.Editor edit = this.mPref.edit();
        edit.putBoolean(DARK_THEME, z);
        edit.apply();
    }

    public final boolean getDarkTheme() {
        return this.mPref.getBoolean(DARK_THEME, true);
    }

    public void setSpotifyConnection(boolean z) {
        SharedPreferences.Editor edit = this.mPref.edit();
        edit.putBoolean(SPOTIFY_CONNECTION, z);
        edit.apply();
    }

    public final boolean getSpotifyConnection() {
        return this.mPref.getBoolean(SPOTIFY_CONNECTION, true);
    }

    public void setBatteryStatusRejectCounter(int i) {
        SharedPreferences.Editor edit = this.mPref.edit();
        edit.putInt(BATTERY_STATUS_REJECT_COUNTER, i);
        edit.apply();
    }

    public final int getBatteryStatusRejectCounter() {
        return this.mPref.getInt(BATTERY_STATUS_REJECT_COUNTER, 0);
    }

    public void setTS_First_Run(long j) {
        SharedPreferences.Editor edit = this.mPref.edit();
        edit.putLong(TS_FIRST_RUN, j);
        edit.apply();
    }

    public final long getTS_First_Run() {
        return this.mPref.getLong(TS_FIRST_RUN, 0);
    }

    public void setOnResError(Boolean bool) {
        SharedPreferences.Editor edit = this.mPref.edit();
        edit.putBoolean(ON_RES_ERROR, bool);
        edit.apply();
    }

    public final boolean getOnResError() {
        return this.mPref.getBoolean(ON_RES_ERROR, false);
    }

    public void setWasOnResInError(int i) {
        SharedPreferences.Editor edit = this.mPref.edit();
        edit.putInt(WAS_ON_RES_IN_ERROR_COUNT, i);
        edit.apply();
    }

    public final int getWasOnResInErrorCount() {
        return this.mPref.getInt(WAS_ON_RES_IN_ERROR_COUNT, 0);
    }

    public void setForceRunFlag(Boolean bool) {
        SharedPreferences.Editor edit = this.mPref.edit();
        edit.putBoolean(FORCE_RUN_FLAG, bool);
        edit.apply();
    }

    public final boolean getForceRunFlag() {
        return this.mPref.getBoolean(FORCE_RUN_FLAG, false);
    }

    public void setForceRunWorksNotified(Boolean bool) {
        SharedPreferences.Editor edit = this.mPref.edit();
        edit.putBoolean(FORCE_RUN_FLAG_WORKS_NOTIFIED, bool);
        edit.apply();
    }

    public final boolean getForceRunWorksNotified() {
        return this.mPref.getBoolean(FORCE_RUN_FLAG_WORKS_NOTIFIED, false);
    }

    public void setAdFreeVersion(Boolean bool) {
        SharedPreferences.Editor edit = this.mPref.edit();
        edit.putBoolean(AD_FREE_VERSION, bool);
        edit.apply();
    }

    public final boolean getAdFreeVersion() {
        return this.mPref.getBoolean(AD_FREE_VERSION, false);
    }

    public void setDontShowAgainWarningApps(Boolean bool) {
        SharedPreferences.Editor edit = this.mPref.edit();
        edit.putBoolean(DONT_SHOW_AGAING_WARNING_APPS, bool);
        edit.apply();
    }

    public final boolean getDontShowAgainWarningApps() {
        return this.mPref.getBoolean(DONT_SHOW_AGAING_WARNING_APPS, false);
    }

    public void setGainPlugin(Boolean bool) {
        SharedPreferences.Editor edit = this.mPref.edit();
        edit.putBoolean(GAIN_PLUGIN, bool);
        edit.apply();
    }

    public final boolean getGainPlugin() {
        return this.mPref.getBoolean(GAIN_PLUGIN, false);
    }

    public void setVirtualizerPlugin(Boolean bool) {
        SharedPreferences.Editor edit = this.mPref.edit();
        edit.putBoolean(VIRT_PLUGIN, bool);
        edit.apply();
    }

    public final boolean getVirtualizerPlugin() {
        return this.mPref.getBoolean(VIRT_PLUGIN, false);
    }

    public void setAGC(boolean z) {
        SharedPreferences.Editor edit = this.mPref.edit();
        edit.putBoolean(AGC, z);
        edit.apply();
    }

    public final boolean getAGC() {
        return this.mPref.getBoolean(AGC, true);
    }

    public void setAGCmode(String str) {
        SharedPreferences.Editor edit = this.mPref.edit();
        edit.putString(AGC_MODE, str);
        edit.apply();
    }

    public final String getAGCmode() {
        return this.mPref.getString(AGC_MODE, "3.3");
    }

    public void setAGCbbs(boolean z) {
        SharedPreferences.Editor edit = this.mPref.edit();
        edit.putBoolean(AGC_BBS, z);
        edit.apply();
    }

    public final boolean getAGCbbs() {
        return this.mPref.getBoolean(AGC_BBS, false);
    }

    public void setManualGainValue(int i) {
        SharedPreferences.Editor edit = this.mPref.edit();
        edit.putInt(MANUAL_GAIN_VALUE, i);
        edit.apply();
    }

    public final int getManualGainValue() {
        return this.mPref.getInt(MANUAL_GAIN_VALUE, -1);
    }

    public void setProtected(boolean z) {
        SharedPreferences.Editor edit = this.mPref.edit();
        edit.putBoolean(PROTECTED, z);
        edit.apply();
    }

    public final boolean getProtected() {
        return this.mPref.getBoolean(PROTECTED, false);
    }

    public void setProtectedKB(boolean z) {
        SharedPreferences.Editor edit = this.mPref.edit();
        edit.putBoolean(PROTECTED_KB, z);
        edit.apply();
    }

    public final boolean getProtectedKB() {
        return this.mPref.getBoolean(PROTECTED_KB, false);
    }

    public void setAutoStartBoot(boolean z) {
        SharedPreferences.Editor edit = this.mPref.edit();
        edit.putBoolean(AUTO_START_BOOT, z);
        edit.apply();
    }

    public final boolean getAutoStartBoot() {
        return this.mPref.getBoolean(AUTO_START_BOOT, false);
    }

    public void setBbsFirstEnableCheck(boolean z) {
        SharedPreferences.Editor edit = this.mPref.edit();
        edit.putBoolean(BBS_FIRST_ENABLE_CHECK, z);
        edit.apply();
    }

    public final boolean getBbsFirstEnableCheck() {
        return this.mPref.getBoolean(BBS_FIRST_ENABLE_CHECK, false);
    }

    public void setBbsPlugin(boolean z) {
        SharedPreferences.Editor edit = this.mPref.edit();
        edit.putBoolean(BBS_PLUGIN, z);
        edit.apply();
    }

    public final boolean getBbsPlugin() {
        return this.mPref.getBoolean(BBS_PLUGIN, true);
    }

    public void setTipsTricksButtonEnable(boolean z) {
        SharedPreferences.Editor edit = this.mPref.edit();
        edit.putBoolean(TIPS_TRICKS_BUTTON_ENABLE, z);
        edit.apply();
    }

    public final boolean getTipsTricksButtonEnable() {
        return this.mPref.getBoolean(TIPS_TRICKS_BUTTON_ENABLE, true);
    }

    public void setBatteryOptimizationButtonEnable(boolean z) {
        SharedPreferences.Editor edit = this.mPref.edit();
        edit.putBoolean(BATTERY_OPTIMIZATION_BUTTON_ENABLE, z);
        edit.apply();
    }

    public final boolean getBatteryOptimizationButtonEnable() {
        return this.mPref.getBoolean(BATTERY_OPTIMIZATION_BUTTON_ENABLE, false);
    }

    public void setAgcYellowFirstMoveCheck(boolean z) {
        SharedPreferences.Editor edit = this.mPref.edit();
        edit.putBoolean(AGC_YELLOW_FIRST_MOVE_CHECK, z);
        edit.apply();
    }

    public final boolean getAgcYellowFirstMoveCheck() {
        return this.mPref.getBoolean(AGC_YELLOW_FIRST_MOVE_CHECK, false);
    }

    public void setKeepsServiceAlwaysOn(boolean z) {
        SharedPreferences.Editor edit = this.mPref.edit();
        edit.putBoolean(KEEPS_SERVICE_ALWAYS_ON, z);
        edit.apply();
    }

    public final boolean getKeepsServiceAlwaysOn() {
        return this.mPref.getBoolean(KEEPS_SERVICE_ALWAYS_ON, false);
    }

    public void setZoomEqVal(float f) {
        SharedPreferences.Editor edit = this.mPref.edit();
        edit.putFloat(ZOOM_EQ_VAL, f);
        edit.apply();
    }

    public final float getZoomEqVal() {
        return this.mPref.getFloat(ZOOM_EQ_VAL, 0.0f);
    }

    public void setLastVersionMessage(int i) {
        SharedPreferences.Editor edit = this.mPref.edit();
        edit.putInt(LAST_VERSION_MESSAGE, i);
        edit.apply();
    }

    public final int getLastVersionMessage() {
        return this.mPref.getInt(LAST_VERSION_MESSAGE, 0);
    }

    public void setKeepsServiceAlwaysOnBonification(boolean z) {
        SharedPreferences.Editor edit = this.mPref.edit();
        edit.putBoolean(KEEPS_SERVICE_ALWAYS_ON_BONIFICATION, z);
        edit.apply();
    }

    public final boolean getKeepsServiceAlwaysOnBonification() {
        return this.mPref.getBoolean(KEEPS_SERVICE_ALWAYS_ON_BONIFICATION, false);
    }

    public void setDbLabelsAlwaysPresent(boolean z) {
        SharedPreferences.Editor edit = this.mPref.edit();
        edit.putBoolean(DB_LABELS_ALWAYS_PRESENT, z);
        edit.apply();
    }

    public final boolean getDbLabelsAlwaysPresent() {
        return this.mPref.getBoolean(DB_LABELS_ALWAYS_PRESENT, false);
    }

    public final boolean hasKey() {
        return this.mPref.contains(SPINNER_POS);
    }
}
