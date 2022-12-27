package com.mahendra249k.equalizer_booster.utils;

import android.app.Application;
import android.media.audiofx.BassBoost;
import android.media.audiofx.Equalizer;
import android.media.audiofx.LoudnessEnhancer;
import android.media.audiofx.Virtualizer;
import android.util.Log;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.mahendra249k.equalizer_booster.service.NotificationService;

public class EqualizerVM extends AndroidViewModel {
    private Integer bBSlider;
    private Boolean bBSwitch;
    private BassBoost bassBoost;
    private MutableLiveData<Boolean> darkTheme;
    private Boolean eqSwitch;
    private Equalizer equalizer;
    private Boolean isCustomSelected;
    private Float loudSlider;
    private Boolean loudSwitch;
    private LoudnessEnhancer loudnessEnhancer;
    private Preferences preferenceUtil;
    private int[] slider;
    private int spinnerPos;
    private MutableLiveData<Boolean> spotifyEqMode;
    private Integer virSlider;
    private Boolean virSwitch;
    private Virtualizer virtualizer;

    public EqualizerVM(Application application) {
        super(application);
        Preferences instance = Preferences.getInstance(application);
        this.preferenceUtil = instance;
        if (instance.getKeepsServiceAlwaysOn()) {
            Log.d("FabioService", "Services from Foreground");
            this.equalizer = NotificationService.b();
            this.bassBoost = NotificationService.a();
            this.virtualizer = NotificationService.d();
            this.loudnessEnhancer = NotificationService.c();
        } else {
            Log.d("FabioService", "Services from Effect Instance");
            this.equalizer = EffectInstance.b();
            this.bassBoost = EffectInstance.a();
            this.virtualizer = EffectInstance.d();
            this.loudnessEnhancer = EffectInstance.c();
        }
        this.darkTheme = new MutableLiveData<>();
        this.spotifyEqMode = new MutableLiveData<>();
        this.slider = new int[5];
        for (int i = 0; i < 5; i++) {
            this.slider[i] = this.preferenceUtil.getEqSlider(i);
        }
        this.virSlider = this.preferenceUtil.getVirSlider();
        this.bBSlider = this.preferenceUtil.getBBSlider();
        this.loudSlider = this.preferenceUtil.getLoudSlider();
        this.spinnerPos = this.preferenceUtil.getSpinnerPos();
        this.virSwitch = this.preferenceUtil.getVirSwitch();
        this.bBSwitch = this.preferenceUtil.getBBSwitch();
        this.loudSwitch = this.preferenceUtil.getLoudSwitch();
        this.eqSwitch = this.preferenceUtil.getEqSwitch();
        this.isCustomSelected = this.preferenceUtil.getIsCustomSelected();
        this.darkTheme.setValue(this.preferenceUtil.getDarkTheme());
        this.spotifyEqMode.setValue(this.preferenceUtil.getSpotifyConnection());
    }

    public Equalizer getEqualizer() {
        return this.equalizer;
    }

    public BassBoost getBassBoost() {
        return this.bassBoost;
    }

    public Virtualizer getVirtualizer() {
        return this.virtualizer;
    }

    public LoudnessEnhancer getLoudnessEnhancer() {
        return this.loudnessEnhancer;
    }

    public int getVirSlider() {
        return this.virSlider;
    }

    public void setVirSlider(int i) {
        this.virSlider = i;
        this.preferenceUtil.setVirSlider(i);
    }

    public int getBBSlider() {
        return this.bBSlider;
    }

    public void setBBSlider(int i) {
        this.bBSlider = i;
        this.preferenceUtil.setBBSlider(i);
    }

    public float getLoudSlider() {
        return this.loudSlider;
    }

    public void setLoudSlider(float f) {
        this.loudSlider = f;
        this.preferenceUtil.setLoudSlider(f);
    }

    public int getSlider(int i) {
        return this.slider[i];
    }

    public void setSlider(int i, int i2) {
        this.slider[i2] = i;
        this.preferenceUtil.setEqSlider(i, i2);
    }

    public int getSpinnerPos() {
        return this.spinnerPos;
    }

    public void setSpinnerPos(int i) {
        this.spinnerPos = i;
        this.preferenceUtil.setSpinnerPos(i);
    }

    public boolean getVirSwitch() {
        return this.virSwitch;
    }

    public void setVirSwitch(boolean z) {
        this.virSwitch = z;
        this.preferenceUtil.setVirSwitch(z);
    }

    public boolean getbBSwitch() {
        return this.bBSwitch;
    }

    public void setbBSwitch(boolean z) {
        this.bBSwitch = z;
        this.preferenceUtil.setBBSwitch(z);
    }

    public boolean getLoudSwitch() {
        return this.loudSwitch;
    }

    public void setLoudSwitch(boolean z) {
        this.loudSwitch = z;
        this.preferenceUtil.setLoudSwitch(z);
    }

    public boolean getEqSwitch() {
        return this.eqSwitch;
    }

    public void setEqSwitch(boolean z) {
        this.eqSwitch = z;
        this.preferenceUtil.setEqSwitch(z);
    }

    public boolean getIsCustomSelected() {
        return this.isCustomSelected;
    }

    public void setIsCustomSelected(boolean z) {
        this.isCustomSelected = z;
        this.preferenceUtil.setIsCustomSelected(z);
    }

    public MutableLiveData<Boolean> getDarkTheme() {
        return this.darkTheme;
    }

    public void setDarkTheme(boolean z) {
        this.darkTheme.setValue(z);
        this.preferenceUtil.setDarkTheme(z);
    }

    public MutableLiveData<Boolean> getSpotifyEqMode() {
        return this.spotifyEqMode;
    }

    public void setSpotifyEqMode(boolean z) {
        this.spotifyEqMode.setValue(z);
        this.preferenceUtil.setSpotifyConnection(z);
    }
}
