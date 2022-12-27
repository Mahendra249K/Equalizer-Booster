package com.mahendra249k.equalizer_booster.activity;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.audiofx.AudioEffect;
import android.media.audiofx.BassBoost;
import android.media.audiofx.Equalizer;
import android.media.audiofx.LoudnessEnhancer;
import android.media.audiofx.Virtualizer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.vectordrawable.graphics.drawable.PathInterpolatorCompat;

import com.mahendra249k.equalizer_booster.R;
import com.mahendra249k.equalizer_booster.admob.AdsHandler;
import com.mahendra249k.equalizer_booster.service.NotificationService;
import com.mahendra249k.equalizer_booster.utils.Constants;
import com.mahendra249k.equalizer_booster.utils.DatabaseHelper;
import com.mahendra249k.equalizer_booster.utils.EqualizerVM;
import com.mahendra249k.equalizer_booster.utils.ObservableObject;
import com.mahendra249k.equalizer_booster.utils.Preferences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class EqualizerActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, CompoundButton.OnCheckedChangeListener, SharedPreferences.OnSharedPreferenceChangeListener, Observer {

    public DatabaseHelper myDBHelper;
    int intD;
    ArrayList<Integer> integerArrayList, getIntegerArrayList;
    ArrayList<String> arrayList, stringArrayList;
    String stringC, W, v0, w0, H, K, X;
    Long M;
    Integer N, O;
    ColorStateList colorStateList;
    View ViewBBSCard, ViewCardEq, S, T, k;
    int V;
    BassBoost bassBoost;
    SeekBar[] SeekBart0;
    SeekBar bassSeekBar, VirtualSeekBar, myGainBar;
    Switch bassSwitch, switchEnable, VirtualSwitch;
    Equalizer h0;
    EqualizerVM i0;
    boolean c0, e0, h, i, j0, l, m, t, o, u, aBoolean, F, G, J, I, U, a0, w;
    LoudnessEnhancer loudnessEnhancer;
    Integer n = 0;
    int n0, o0, p, q, q0, r, s;
    short p0;
    TextView[] TextViews0, TextViewr0;
    TextView v, k0;
    Spinner spinner;
    ImageButton imageButton, x, y, z;
    Virtualizer virtualizer;

    public EqualizerActivity() {
        boolean bool = true;
        boolean bool2 = false;
        this.o = bool2;
        this.p = -1;
        this.q = -1;
        this.t = bool2;
        this.u = bool2;
        this.w = bool2;
        this.aBoolean = bool2;
        this.stringC = null;
        this.intD = -1;
        this.F = bool2;
        this.G = bool2;
        this.K = "";
        this.M = 0L;
        this.N = 0;
        this.O = 0;
        this.bassBoost = null;
        this.a0 = bool2;
        this.c0 = bool2;
        this.switchEnable = null;
        this.e0 = bool2;
        this.h0 = null;
        this.j0 = bool;
        this.loudnessEnhancer = null;
        this.n0 = 100;
        this.o0 = 0;
        this.q0 = 0;
        this.TextViewr0 = new TextView[5];
        this.TextViews0 = new TextView[5];
        this.SeekBart0 = new SeekBar[5];
        this.virtualizer = null;
    }

    public static double round(double d, int i2) {
        double pow = (double) ((int) Math.pow(10.0d, (double) i2));
        Double.isNaN(pow);
        double round = (double) Math.round(d * pow);
        Double.isNaN(round);
        Double.isNaN(pow);
        return round / pow;
    }

    @Override
    @SuppressLint("RestrictedApi")
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.aBoolean = Preferences.getInstance(getApplicationContext()).getKeepsServiceAlwaysOn();
        if ("OnePlus".equalsIgnoreCase(Build.MANUFACTURER)) {
            boolean bool = true;
            this.G = bool;
            boolean bool2 = false;
            this.U = bool2;
            Preferences.getInstance(getApplicationContext()).setVirtualizerPlugin(bool2);
            Preferences.getInstance(getApplicationContext()).setKeepsServiceAlwaysOn(bool);
            this.aBoolean = bool;
        }
        if (this.aBoolean) {
            StartForegroundService();
        }
        boolean bool3 = false;
        this.o = bool3;
        this.M = Preferences.getInstance(this).getTS_First_Run();
        this.t = Preferences.getInstance(getApplicationContext()).getForceRunFlag();
        this.u = Preferences.getInstance(getApplicationContext()).getForceRunWorksNotified();
        boolean bool4 = true;
        this.h = Preferences.getInstance(getApplicationContext()).getAGC();
        this.i = Preferences.getInstance(getApplicationContext()).getAGCbbs();
        this.I = Preferences.getInstance(getApplicationContext()).getProtected();
        this.J = Preferences.getInstance(getApplicationContext()).getProtectedKB();
        this.l = Preferences.getInstance(getApplicationContext()).getBbsFirstEnableCheck();
        this.m = Preferences.getInstance(getApplicationContext()).getBbsPlugin();
        Preferences.getInstance(getApplicationContext()).getTipsTricksButtonEnable();
        Preferences.getInstance(getApplicationContext()).getBatteryOptimizationButtonEnable();
        this.U = Preferences.getInstance(getApplicationContext()).getVirtualizerPlugin();
        this.a0 = Preferences.getInstance(getApplicationContext()).getDbLabelsAlwaysPresent();
        if (Preferences.getInstance(this).getGainPlugin()) {
            this.w = bool4;
        }
        if (this.M == 0) {
            Check_Request_Battery_Optimization();
            this.M = System.currentTimeMillis();
            Preferences.getInstance(getApplicationContext()).setTS_First_Run(this.M);
            BadReviewKiller();
        } else {
            if (!Preferences.getInstance(getApplicationContext()).getKeepsServiceAlwaysOnBonification() && this.aBoolean && !this.G) {
                Preferences.getInstance(getApplicationContext()).setKeepsServiceAlwaysOnBonification(bool4);
                Preferences.getInstance(getApplicationContext()).setKeepsServiceAlwaysOn(bool3);
                this.aBoolean = bool3;
                Toast.makeText(this, "Stability optimizations...Done!", Toast.LENGTH_SHORT).show();
            }
            this.n = Preferences.getInstance(this).getBatteryStatusRejectCounter();
            Check_Request_Battery_Optimization();
        }
        Huawei_Check_Protected();
        try {
            unbindSystemEqualizer();
        } catch (Exception e) {
            new Bundle().putString("unbindSysEq", "Failed");
        }
        this.myDBHelper = new DatabaseHelper(this);
        try {
            this.i0 = (EqualizerVM) ViewModelProviders.of((FragmentActivity) this).get(EqualizerVM.class);
        } catch (Exception e2) {
            this.j0 = false;
            AudioEffectNotWorking();
        }
        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this);
        setContentView((int) R.layout.activity_equalizer);
        final SeekBar volSeekBar = (SeekBar) findViewById(R.id.volSeekBar);
        ((Switch) findViewById(R.id.VolumSwitch)).setOnCheckedChangeListener((buttonView, isChecked) -> volSeekBar.setEnabled(isChecked));
        volSeekBar.setEnabled(true);
        final AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        volSeekBar.setMax(audioManager.getStreamMaxVolume(3));
        volSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int newVolume, boolean b) {
                audioManager.setStreamVolume(3, newVolume, 0);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        this.ViewCardEq = findViewById(R.id.ViewCardEq);
        this.T = findViewById(R.id.ViewPresetCard);
        this.ViewBBSCard = findViewById(R.id.ViewBBSCard);
        this.k = findViewById(R.id.bassCardView);
        this.S = findViewById(R.id.ViewCardGain);
        TextView textView2 = (TextView) findViewById(R.id.GainNameLabel);
        this.v = textView2;
        this.colorStateList = textView2.getTextColors();
        this.x = (ImageButton) findViewById(R.id.ImgBtnAddCustomEq);
        this.y = (ImageButton) findViewById(R.id.ImgBtnDelCustomEq);
        if (Preferences.getInstance(this).getSpotifyConnection()) {
            Preferences.getInstance(this).setSpotifyConnection(true);
        }
        if (this.j0) {
            if (Preferences.getInstance(this).getAGC()) {
                Preferences.getInstance(this).setAGC(true);
                this.v.setText("AGC");
                this.h = true;
            } else {
                this.v.setText("Gain");
                this.h = false;
            }
            if (Preferences.getInstance(this).getAGCbbs()) {
                Preferences.getInstance(this).setAGCbbs(true);
            }
            if (Preferences.getInstance(this).getEqSwitch()) {
                Preferences.getInstance(this).setEqSwitch(true);
            }
            if (Preferences.getInstance(this).getBbsPlugin()) {
                Preferences.getInstance(this).setBbsPlugin(true);
            } else {
                Preferences instance = Preferences.getInstance(this);
                boolean bool5 = false;
                instance.setBbsPlugin(bool5);
                Preferences.getInstance(this).setBBSwitch(bool5);
            }
            if (!this.m) {
                this.k.setVisibility(View.GONE);
            }
            if (Preferences.getInstance(this).getVirtualizerPlugin()) {
                Preferences.getInstance(this).setVirtualizerPlugin(true);
            }
            if (Preferences.getInstance(this).getKeepsServiceAlwaysOn()) {
                Preferences.getInstance(this).setKeepsServiceAlwaysOn(true);
            }
        }
        this.x.setOnClickListener(this::Dialog_Add_Preset);
        this.y.setOnClickListener(this::Dialog_Del_Preset);
        this.switchEnable = (Switch) findViewById(R.id.switchEnable);
        this.spinner = (Spinner) findViewById(R.id.spinner);
        this.SeekBart0[0] = (SeekBar) findViewById(R.id.mySeekBar0);
        this.TextViewr0[0] = (TextView) findViewById(R.id.centerFreq0);
        this.TextViews0[0] = (TextView) findViewById(R.id.centerFreq0fix);
        this.SeekBart0[1] = (SeekBar) findViewById(R.id.mySeekBar1);
        this.TextViewr0[1] = (TextView) findViewById(R.id.centerFreq1);
        this.TextViews0[1] = (TextView) findViewById(R.id.centerFreq1fix);
        this.SeekBart0[2] = (SeekBar) findViewById(R.id.mySeekBar2);
        this.TextViewr0[2] = (TextView) findViewById(R.id.centerFreq2);
        this.TextViews0[2] = (TextView) findViewById(R.id.centerFreq2fix);
        this.SeekBart0[3] = (SeekBar) findViewById(R.id.mySeekBar3);
        this.TextViewr0[3] = (TextView) findViewById(R.id.centerFreq3);
        this.TextViews0[3] = (TextView) findViewById(R.id.centerFreq3fix);
        this.SeekBart0[4] = (SeekBar) findViewById(R.id.mySeekBar4);
        this.TextViewr0[4] = (TextView) findViewById(R.id.centerFreq4);
        this.TextViews0[4] = (TextView) findViewById(R.id.centerFreq4fix);
        TextView textView3 = (TextView) findViewById(R.id.gain);
        this.k0 = textView3;
        textView3.setText("+0dB");
        if (this.a0) {
            for (int i2 = 0; i2 < 5; i2++) {
                this.TextViews0[i2].setVisibility(View.VISIBLE);
            }
            ((TextView) findViewById(R.id.gain_space)).setVisibility(View.VISIBLE);
        }
        this.bassSwitch = (Switch) findViewById(R.id.bassSwitch);
        this.bassSeekBar = (SeekBar) findViewById(R.id.bassSeekBar);
        this.myGainBar = (SeekBar) findViewById(R.id.myGainBar);
        this.VirtualSwitch = (Switch) findViewById(R.id.VirtualSwitch);
        this.VirtualSeekBar = (SeekBar) findViewById(R.id.VirtualSeekBar);
        ImageButton imageButton4 = (ImageButton) findViewById(R.id.ImgBtnZeroRight);
        this.imageButton = imageButton4;
        imageButton4.setOnClickListener(view -> EqualizerActivity.this.Set_Zero_Eq());
        ImageButton imageButton5 = (ImageButton) findViewById(R.id.ImgBtnZeroLeft);
        this.z = imageButton5;
        imageButton5.setOnClickListener(view -> EqualizerActivity.this.Set_Zero_Eq());
        if (!this.switchEnable.isChecked()) {
            int i22 = 0;
            for (int i3 = 5; i22 < i3; i3 = 5) {
                this.SeekBart0[i22].setEnabled(false);
                i22++;
            }
        }
        if (getResources().getConfiguration().orientation == 1) {
            ViewGroup.LayoutParams layoutParams = this.SeekBart0[0].getLayoutParams();
            this.r = layoutParams.height;
            this.s = layoutParams.width;
            float zoomEqVal = Preferences.getInstance(getApplicationContext()).getZoomEqVal();
            if (zoomEqVal < 0.0f) {
                Preferences.getInstance(getApplicationContext()).setZoomEqVal(0.0f);
                zoomEqVal = 0.0f;
            }
            if (zoomEqVal != 0.0f) {
                ResizeEq((double) zoomEqVal);
            }
        }
        if (!this.bassSwitch.isChecked()) {
            this.bassSeekBar.setEnabled(false);
        }
        if (!this.VirtualSwitch.isChecked()) {
            this.VirtualSeekBar.setEnabled(false);
        }
        if (this.j0) {
            this.h0 = this.i0.getEqualizer();
            this.bassBoost = this.i0.getBassBoost();
            if (this.U) {
                this.virtualizer = this.i0.getVirtualizer();
            }
            this.loudnessEnhancer = this.i0.getLoudnessEnhancer();
            if (this.G) {
                Equalizer equalizer = this.h0;
                boolean bool6 = true;
                equalizer.setEnabled(bool6);
                this.bassBoost.setEnabled(bool6);
                PausePlay();
            }
            Control_AGC_preference();
            boolean z2 = this.w;
            boolean bool7 = true;
            if (z2 == bool7) {
                findViewById(R.id.ViewGainPlugin).setVisibility(View.VISIBLE);
            }
            boolean z3 = this.m;
            boolean bool8 = false;
            if (z3 == bool8) {
                this.ViewBBSCard.setVisibility(View.GONE);
                this.i0.setbBSwitch(bool8);
                this.bassSwitch.setChecked(bool8);
                this.bassBoost.setEnabled(bool8);
                this.c0 = bool8;
                Preferences.getInstance(getApplicationContext()).setBBSwitch(bool8);
            }
            if (this.U == bool7) {
                findViewById(R.id.VirtualCardView).setVisibility(View.VISIBLE);
            } else {
                this.i0.setVirSwitch(bool8);
                this.VirtualSwitch.setChecked(bool8);
                Preferences.getInstance(getApplicationContext()).setVirSwitch(bool8);
            }
            if (this.t == bool8) {
                if (!this.h0.hasControl()) {
                    prepareEqualizer();
                }
                CheckError_CloseApp();
            }
            if (this.o == bool8) {
                this.q0 = this.h0.getNumberOfBands();
                short[] bandLevelRange = this.h0.getBandLevelRange();
                this.o0 = bandLevelRange[0];
                this.n0 = bandLevelRange[1];
                SetLabelsFreqDelayed();
                this.p0 = this.h0.getNumberOfPresets();
                Create_Spinner();
                Listener_Spinner();
                this.bassSeekBar.setMax(1000);
                this.bassSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                        EqualizerActivity.this.i0.setBBSlider(i);
                        if (!EqualizerActivity.this.bassBoost.hasControl()) {
                            EqualizerActivity.this.prepareEqualizer();
                        }
                        try {
                            EqualizerActivity.this.bassBoost.setStrength((short) i);
                            bassSwitch.setText((i / 10) + "%");
                        } catch (Exception e) {
                        }
                        if (EqualizerActivity.this.i && EqualizerActivity.this.bassBoost.getEnabled()) {
                            EqualizerActivity.this.DO_AGC();
                        }
                    }

                    public void onStopTrackingTouch(SeekBar seekBar) {
                        EqualizerActivity.this.SetLabelsBBSVirtualDelayed();
                    }
                });
                if (this.w == bool7) {
                    this.loudnessEnhancer.setEnabled(bool7);
                    this.myGainBar.setMax(PathInterpolatorCompat.MAX_NUM_POINTS);
                    this.myGainBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        public void onStartTrackingTouch(SeekBar seekBar) {
                        }

                        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                            EqualizerActivity.this.loudnessEnhancer.setEnabled(true);
                            float f = (float) i;
                            EqualizerActivity.this.i0.setLoudSlider(f);
                            String format = String.format("%.1f", f / 100.0f);
                            TextView textView = EqualizerActivity.this.k0;
                            textView.setText("+" + format);
                            try {
                                if (!EqualizerActivity.this.loudnessEnhancer.hasControl()) {
                                    EqualizerActivity.this.prepareEqualizer();
                                }
                                EqualizerActivity.this.loudnessEnhancer.setTargetGain((short) i);
                            } catch (Exception e) {
                            }
                            if (EqualizerActivity.this.h || EqualizerActivity.this.i) {
                                EqualizerActivity mainActivity = EqualizerActivity.this;
                                mainActivity.v.setTextColor(mainActivity.colorStateList);
                            }
                            EqualizerActivity mainActivity2 = EqualizerActivity.this;
                            if (mainActivity2.w) {
                                mainActivity2.S.setBackgroundResource(0);
                            }
                        }

                        public void onStopTrackingTouch(SeekBar seekBar) {
                            if (EqualizerActivity.this.loudnessEnhancer.getTargetGain() == 0.0f) {
                                EqualizerActivity.this.S.setBackgroundResource(0);
                            } else {
                                EqualizerActivity.this.S.setBackgroundResource(0);
                            }
                            if (EqualizerActivity.this.w) {
                                Preferences.getInstance(EqualizerActivity.this.getApplicationContext()).setManualGainValue((int) EqualizerActivity.this.loudnessEnhancer.getTargetGain());
                                if (EqualizerActivity.this.h || EqualizerActivity.this.i) {
                                    EqualizerActivity mainActivity = EqualizerActivity.this;
                                    mainActivity.v.setTextColor(ContextCompat.getColor(mainActivity.getApplicationContext(), R.color.agcYellow));
                                    if (!Preferences.getInstance(EqualizerActivity.this.getApplicationContext()).getAgcYellowFirstMoveCheck()) {
                                        new AlertDialog.Builder(EqualizerActivity.this).setTitle("AGC").setMessage(EqualizerActivity.this.getString(R.string.AgcFirstYellowCheckExplain)).setPositiveButton("OK", (dialogInterface, i) -> {
                                            Preferences.getInstance(EqualizerActivity.this.getApplicationContext()).setAgcYellowFirstMoveCheck(true);
                                            dialogInterface.dismiss();
                                        }).create().show();
                                    }
                                }
                            }
                        }
                    });
                }
                if (this.U == bool7) {
                    try {
                        if (!this.virtualizer.hasControl()) {
                            this.virtualizer = this.i0.getVirtualizer();
                        }
                    } catch (Exception e3) {
                        this.virtualizer = this.i0.getVirtualizer();
                    }
                }
                this.VirtualSeekBar.setMax(1000);
                this.VirtualSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                        EqualizerActivity.this.i0.setVirSlider(i);
                        EqualizerActivity mainActivity = EqualizerActivity.this;
                        if (mainActivity.U) {
                            try {
                                if (!mainActivity.virtualizer.hasControl()) {
                                    EqualizerActivity mainActivity2 = EqualizerActivity.this;
                                    mainActivity2.virtualizer = mainActivity2.i0.getVirtualizer();
                                }
                                EqualizerActivity.this.virtualizer.setStrength((short) i);
                                VirtualSwitch.setText((i / 10) + "%");
                            } catch (Exception e) {
                                EqualizerActivity mainActivity3 = EqualizerActivity.this;
                                mainActivity3.virtualizer = mainActivity3.i0.getVirtualizer();
                            }
                        }
                    }

                    public void onStopTrackingTouch(SeekBar seekBar) {
                        EqualizerActivity.this.SetLabelsBBSVirtualDelayed();
                    }
                });
                this.bassSwitch.setOnCheckedChangeListener(this);
                this.switchEnable.setOnCheckedChangeListener(this);
                this.VirtualSwitch.setOnCheckedChangeListener(this);
                this.spinner.setSelection(this.i0.getSpinnerPos());
                this.switchEnable.setChecked(this.i0.getEqSwitch());
                this.bassSwitch.setChecked(this.i0.getbBSwitch());
                this.bassSeekBar.setProgress(this.i0.getBBSlider());
                this.VirtualSwitch.setChecked(this.i0.getVirSwitch());
                this.VirtualSeekBar.setProgress(this.i0.getVirSlider());
            }
        }
        new AdsHandler(this).BannerAd((RelativeLayout) findViewById(R.id.bannerAd), this);
    }

    public void ResizeEq(double d) {
        ViewGroup.LayoutParams layoutParams = this.SeekBart0[0].getLayoutParams();
        double d2 = (double) this.r;
        Double.isNaN(d2);
        layoutParams.height = (int) (d2 * d);
        double d3 = (double) this.s;
        Double.isNaN(d3);
        layoutParams.width = (int) (d3 * d);
        for (int i2 = 0; i2 < 5; i2++) {
            this.SeekBart0[i2].setLayoutParams(layoutParams);
        }
        View findViewById = findViewById(R.id.zerobarL);
        ViewGroup.LayoutParams layoutParams2 = findViewById.getLayoutParams();
        double d4 = (double) this.r;
        Double.isNaN(d4);
        layoutParams2.height = (int) (d4 * d);
        findViewById.setLayoutParams(layoutParams2);
        View findViewById2 = findViewById(R.id.zerobarR);
        ViewGroup.LayoutParams layoutParams3 = findViewById2.getLayoutParams();
        double d5 = (double) this.s;
        Double.isNaN(d5);
        layoutParams3.height = (int) (d5 * d);
        findViewById2.setLayoutParams(layoutParams3);
        if (this.w) {
            ViewGroup.LayoutParams layoutParams4 = this.myGainBar.getLayoutParams();
            int i3 = this.s;
            double d6 = (double) i3;
            Double.isNaN(d6);
            layoutParams4.height = (int) (d6 * d);
            double d7 = (double) i3;
            Double.isNaN(d7);
            layoutParams4.width = (int) (d7 * d);
            this.myGainBar.setLayoutParams(layoutParams4);
        }
    }

    private void StartForegroundService() {
        Intent intent = new Intent(this, NotificationService.class);
        intent.setAction(Constants.ACTION.STARTFOREGROUND_ACTION);
        startService(intent);
    }

    public void Open_Website(String str) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(str));
            startActivity(intent);
        } catch (Exception e) {
        }
    }

    public void Huawei_Check_Protected() {
        if ("huawei".equalsIgnoreCase(Build.MANUFACTURER)) {
            if (!this.I || !this.J) {
                new AlertDialog.Builder(this).setTitle(getString(R.string.Huawei_title)).setMessage(getString(R.string.Huawei_message)).setPositiveButton(getString(R.string.Enable), (dialogInterface, i) -> {
                    if (!EqualizerActivity.this.J) {
                        Preferences.getInstance(EqualizerActivity.this.getApplicationContext()).setProtectedKB(true);
                        EqualizerActivity.this.Open_Website("https://github.com/Mahendra249K");
                        return;
                    }
                    Preferences.getInstance(EqualizerActivity.this.getApplicationContext()).setProtected(true);
                    Intent intent = new Intent();
                    intent.setComponent(new ComponentName("com.huawei.systemmanager", Build.VERSION.SDK_INT >= 28 ? "com.huawei.systemmanager.startupmgr.ui.StartupNormalAppListActivity" : "com.huawei.systemmanager.appcontrol.activity.StartupAppControlActivity"));
                    try {
                        EqualizerActivity.this.startActivity(intent);
                    } catch (Exception e) {
                    }
                }).setNegativeButton(getString(R.string.exit), (dialogInterface, i) -> dialogInterface.dismiss()).create().show();
            }
        }
    }

    public void Control_AGC_preference() {
        if (this.loudnessEnhancer == null) {
            this.loudnessEnhancer = this.i0.getLoudnessEnhancer();
        }
        if (this.h0 == null) {
            this.h0 = this.i0.getEqualizer();
        }
        if (this.bassBoost == null) {
            this.bassBoost = this.i0.getBassBoost();
        }
        if (this.h || this.i) {
            this.loudnessEnhancer.setEnabled(true);
            DO_AGC();
        } else if (this.w) {
            this.myGainBar.setProgress(Preferences.getInstance(getApplicationContext()).getManualGainValue());
        } else {
            try {
                if (!this.loudnessEnhancer.hasControl()) {
                    prepareEqualizer();
                }
                this.loudnessEnhancer.setTargetGain(0);
            } catch (Exception e) {
            }
        }
    }

    public void Check_Request_Battery_Optimization() {
        if (!((PowerManager) getSystemService(Context.POWER_SERVICE)).isIgnoringBatteryOptimizations(getApplicationContext().getPackageName())) {
            Preferences.getInstance(this).setBatteryOptimizationButtonEnable(true);
            if (this.n < 3) {
                this.n = this.n + 1;
                Preferences.getInstance(this).setBatteryStatusRejectCounter(this.n);
                Alert_Dialog_Allow_Battery_Optimization();
                return;
            }
            Toast.makeText(this, "Please, allow SpotiQ to run in backgroud.", Toast.LENGTH_SHORT).show();
            return;
        }
        Preferences.getInstance(this).setBatteryOptimizationButtonEnable(false);
    }

    private void Alert_Dialog_Allow_Battery_Optimization() {
        final PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        new AlertDialog.Builder(this).setTitle(R.string.Allow_battery_opt_spotiq_title).setMessage(R.string.Allow_battery_opt_spotiq_description).
                setPositiveButton(R.string.Allow, (dialogInterface, i) -> {
                    if (Build.VERSION.SDK_INT >= 23) {
                        Intent intent = new Intent();
                        String packageName = EqualizerActivity.this.getPackageName();
                        if (!powerManager.isIgnoringBatteryOptimizations(packageName)) {
                            intent.setAction("android.settings.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS");
                            intent.setData(Uri.parse("package:" + packageName));
                            EqualizerActivity.this.startActivity(intent);
                        }
                    }
                    dialogInterface.dismiss();
                }).create().show();
    }

    private void Dialog_Pro_Version_Owned() {
        new AlertDialog.Builder(this).setTitle(R.string.get_pro_version).setMessage(R.string.ComplimentiPro).setCancelable(false).setPositiveButton(R.string.Apply_close, (dialogInterface, i) -> {
            dialogInterface.dismiss();
            EqualizerActivity.this.Kill();
        }).create().show();
    }

    private void Listener_Spinner() {
        if (!this.o) {
            this.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onNothingSelected(AdapterView<?> adapterView) {
                }

                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long stringArrayList) {
                    ((TextView) view).setTextColor(EqualizerActivity.this.getResources().getColor(R.color.theamcolor));
                    if (!EqualizerActivity.this.h0.hasControl()) {
                        EqualizerActivity.this.prepareEqualizer();
                    }
                    int i2 = 0;
                    if (i < EqualizerActivity.this.arrayList.size() - (EqualizerActivity.this.stringArrayList.size() + 1)) {
                        try {
                            EqualizerActivity.this.h0.usePreset((short) i);
                            EqualizerActivity.this.i0.setSpinnerPos(i);
                            EqualizerActivity.this.i0.setIsCustomSelected(false);
                            while (i2 < 5) {
                                short s = (short) i2;
                                short bandLevel = EqualizerActivity.this.h0.getBandLevel(s);
                                EqualizerActivity mainActivity = EqualizerActivity.this;
                                int i3 = mainActivity.o0;
                                int bandLevel2 = ((bandLevel - i3) * 100) / (mainActivity.n0 - i3);
                                EqualizerActivity.this.SeekBart0[i2].setProgress(bandLevel2);
                                i2++;
                            }
                        } catch (Exception e) {
                            EqualizerActivity.this.disablePreset();
                        }
                        EqualizerActivity.this.UpdateCardStroke(1);
                        EqualizerActivity.this.y.setAlpha(0.2f);
                        EqualizerActivity.this.y.setClickable(false);
                    } else if (i < EqualizerActivity.this.arrayList.size() - EqualizerActivity.this.stringArrayList.size()) {
                        EqualizerActivity.this.i0.setIsCustomSelected(true);
                        EqualizerActivity.this.i0.setSpinnerPos(i);
                        while (i2 < 5) {
                            EqualizerActivity mainActivity2 = EqualizerActivity.this;
                            SeekBar seekBar = mainActivity2.SeekBart0[i2];
                            int slider = mainActivity2.i0.getSlider(i2);
                            EqualizerActivity mainActivity3 = EqualizerActivity.this;
                            int i4 = mainActivity3.o0;
                            seekBar.setProgress(((slider - i4) * 100) / (mainActivity3.n0 - i4));
                            i2++;
                        }
                        EqualizerActivity.this.UpdateCardStroke(3);
                        EqualizerActivity.this.y.setAlpha(0.2f);
                        EqualizerActivity.this.y.setClickable(false);
                    } else {
                        EqualizerActivity.this.i0.setIsCustomSelected(true);
                        EqualizerActivity.this.i0.setSpinnerPos(i);
                        EqualizerActivity mainActivity4 = EqualizerActivity.this;
                        mainActivity4.K = mainActivity4.stringArrayList.get((EqualizerActivity.this.stringArrayList.size() + i) - EqualizerActivity.this.arrayList.size());
                        StringBuilder sb = new StringBuilder();
                        sb.append("Id from name: ");
                        EqualizerActivity mainActivity5 = EqualizerActivity.this;
                        sb.append(mainActivity5.myDBHelper.getIdFromPresetName(mainActivity5.K));
                        EqualizerActivity mainActivity6 = EqualizerActivity.this;
                        DatabaseHelper dBHelper = mainActivity6.myDBHelper;
                        ArrayList<String> eqPresetValue = dBHelper.getEqPresetValue(dBHelper.getIdFromPresetName(mainActivity6.K));
                        while (i2 < 5) {
                            int parseInt = Integer.parseInt(eqPresetValue.get(i2 + 5));
                            EqualizerActivity.this.i0.setSlider(parseInt, i2);
                            EqualizerActivity.this.SeekBart0[i2].setProgress(parseInt);
                            i2++;
                        }
                        EqualizerActivity mainActivity7 = EqualizerActivity.this;
                        if (mainActivity7.m) {
                            mainActivity7.bassSwitch.setChecked(Boolean.parseBoolean(eqPresetValue.get(2)));
                        }
                        EqualizerActivity.this.bassSeekBar.setProgress(Integer.parseInt(eqPresetValue.get(3)));
                        EqualizerActivity mainActivity22 = EqualizerActivity.this;
                        mainActivity22.h0 = mainActivity22.i0.getEqualizer();
                        EqualizerActivity.this.y.setAlpha(1.0f);
                        EqualizerActivity.this.y.setClickable(true);
                    }
                    EqualizerActivity.this.SetLabelsBBSVirtualDelayed();
                    EqualizerActivity.this.SetLabelsFreqDelayed();
                }
            });
        }
    }

    private void SetLabelsFreq() {
        int i2 = 0;
        while (i2 < this.q0 && i2 < 5) {
            try {
                int centerFreq = this.h0.getCenterFreq((short) i2);
                this.SeekBart0[i2].setOnSeekBarChangeListener(EqualizerActivity.this);
                this.TextViewr0[i2].setText(milliHzToString(centerFreq));
                i2++;
            } catch (Exception e) {
                return;
            }
        }
    }

    private void SetDbAlwaysPresentLables() {
        int i2 = 0;
        while (i2 < 5) {
            try {
                TextView textView = this.TextViews0[i2];
                int i3 = this.o0;
                textView.setText(String.format("%.1f", ((float) (i3 + (((this.n0 - i3) * this.SeekBart0[i2].getProgress()) / 100))) / 100.0f));
                i2++;
            } catch (Exception e) {
                return;
            }
        }
    }

    public void Set_Zero_Eq() {
        for (int i2 = 0; i2 < 5; i2++) {
            this.i0.setSlider(0, i2);
            this.SeekBart0[i2].setProgress(50);
            UpdateCardStroke(3);
            SetLabelsFreqDelayed();
        }
    }

    public void Create_Spinner() {
        this.arrayList = new ArrayList<>();
        for (short s2 = 0; s2 < this.p0; s2 = (short) (s2 + 1)) {
            this.arrayList.add(this.h0.getPresetName(s2));
        }
        this.arrayList.add("↓ Custom ↓");
        this.stringArrayList = new ArrayList<>();
        ArrayList<String> allEqPreset = this.myDBHelper.getAllEqPreset();
        this.stringArrayList = allEqPreset;
        this.arrayList.addAll(allEqPreset);
        this.spinner.setAdapter(new ArrayAdapter<String>(EqualizerActivity.this, R.layout.layout_spinner, this.arrayList) {
            public View getView(int i, View view, ViewGroup viewGroup) {
                View view2 = super.getView(i, view, viewGroup);
                ((TextView) view2).setTextSize(16.0f);
                return view2;
            }

            public View getDropDownView(int i, View view, @NonNull ViewGroup viewGroup) {
                View dropDownView = super.getDropDownView(i, view, viewGroup);
                ((TextView) dropDownView).setGravity(17);
                return dropDownView;
            }
        });
    }

    public void Dialog_Add_Preset(View view) {
        View inflate = LayoutInflater.from(this).inflate(R.layout.dialog_save_presetname, (ViewGroup) null);
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AppTheme));
        final EditText editText = (EditText) inflate.findViewById(R.id.editTextDialogUserInput);
        builder.setPositiveButton("Ok", (dialogInterface, i) -> {
            if (editText.getText().toString().matches("")) {
                EqualizerActivity mainActivity = EqualizerActivity.this;
                Toast.makeText(mainActivity, mainActivity.getString(R.string.MissingEqualizerName), Toast.LENGTH_SHORT).show();
            } else if (editText.getText().toString().matches("fr@ckstudio")) {
                dialogInterface.dismiss();
                EqualizerActivity.this.DeveloperOptions();
            } else {
                Toast.makeText(EqualizerActivity.this, editText.getText().toString(), Toast.LENGTH_SHORT).show();
                EqualizerActivity.this.H = editText.getText().toString();
                EqualizerActivity.this.SaveEqForPreset();
                ((InputMethodManager) EqualizerActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(editText.getWindowToken(), 0);
                dialogInterface.dismiss();
                EqualizerActivity.this.Create_Spinner();
                try {
                    EqualizerActivity mainActivity2 = EqualizerActivity.this;
                    mainActivity2.spinner.setSelection(mainActivity2.arrayList.size() - 1);
                } catch (Exception e) {
                }
            }
        }).setView(inflate).setNegativeButton("Exit", (dialogInterface, i) -> {
            ((InputMethodManager) EqualizerActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(editText.getWindowToken(), 0);
            dialogInterface.cancel();
        }).show();
        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(2, 0);
    }

    public void Dialog_Del_Preset(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AppTheme));
        builder.setTitle(getString(R.string.DeleteEqQ)).setCancelable(false).setPositiveButton("OK", (dialogInterface, i) -> {
            EqualizerActivity mainActivity = EqualizerActivity.this;
            DatabaseHelper dBHelper = mainActivity.myDBHelper;
            dBHelper.deleteEqPreset(dBHelper.getIdFromPresetName(mainActivity.K));
            EqualizerActivity.this.Create_Spinner();
        }).setNeutralButton("No", (dialogInterface, i) -> dialogInterface.cancel());
        builder.show();
    }

    private void GetSlidersLevels() {
        this.getIntegerArrayList = new ArrayList<>();
        for (int i2 = 0; i2 < 5; i2++) {
            this.getIntegerArrayList.add(this.SeekBart0[i2].getProgress());
        }
    }

    public void SaveEqForPreset() {
        GetSlidersLevels();
        this.bassSwitch.setChecked(this.i0.getbBSwitch());
        String replaceAll = this.H.replaceAll("'", " ");
        this.H = replaceAll;
        Integer idFromPresetName = this.myDBHelper.getIdFromPresetName(replaceAll);
        if (idFromPresetName == -1) {
            DatabaseHelper dBHelper = this.myDBHelper;
            dBHelper.insertEqPreset(this.H, Boolean.toString(this.e0), Boolean.toString(this.c0), String.valueOf(this.bassSeekBar.getProgress()), String.valueOf(this.q0), String.valueOf(this.getIntegerArrayList.get(0)), String.valueOf(this.getIntegerArrayList.get(1)), String.valueOf(this.getIntegerArrayList.get(2)), String.valueOf(this.getIntegerArrayList.get(3)), String.valueOf(this.getIntegerArrayList.get(4)), "0");
            Toast.makeText(this, getString(R.string.PrestEqSaved), Toast.LENGTH_SHORT).show();
        } else {
            this.myDBHelper.updateEqPreset(idFromPresetName, this.H, Boolean.toString(this.e0), Boolean.toString(this.c0), String.valueOf(this.bassSeekBar.getProgress()), String.valueOf(this.q0), String.valueOf(this.getIntegerArrayList.get(0)), String.valueOf(this.getIntegerArrayList.get(1)), String.valueOf(this.getIntegerArrayList.get(2)), String.valueOf(this.getIntegerArrayList.get(3)), String.valueOf(this.getIntegerArrayList.get(4)), "0");
            Toast.makeText(this, getString(R.string.PresetEqUpdated), Toast.LENGTH_SHORT).show();
        }
        UpdateSpotifyButtons();
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
        if (compoundButton == this.switchEnable) {
            this.z.setClickable(z2);
            this.imageButton.setClickable(z2);
            this.spinner.setEnabled(z2);
            if (this.G) {
                this.h0.setEnabled(true);
                if (!z2) {
                    this.integerArrayList = new ArrayList<>();
                    for (int i2 = 0; i2 < 5; i2++) {
                        short bandLevel = this.h0.getBandLevel((short) i2);
                        int i22 = this.o0;
                        this.integerArrayList.add(((bandLevel - i22) * 100) / (this.n0 - i22));
                    }
                    Set_Zero_Eq();
                } else if (this.integerArrayList != null) {
                    for (int i3 = 0; i3 < 5; i3++) {
                        this.SeekBart0[i3].setProgress(this.integerArrayList.get(i3));
                    }
                }
            } else {
                this.h0.setEnabled(z2);
            }
            for (int i4 = 0; i4 < 5; i4++) {
                this.SeekBart0[i4].setEnabled(z2);
            }
            this.i0.setEqSwitch(z2);
            this.e0 = z2;
            if (this.h0.getEnabled()) {
                if (Preferences.getInstance(this).getAGC()) {
                    this.h = true;
                }
                if (this.w) {
                    this.myGainBar.setEnabled(true);
                }
                new Handler().postDelayed(this::Control_AGC_preference, 100);
            } else {
                this.h = false;
                Control_AGC_preference();
            }
            SetLabelsFreqDelayed();
            if (z2 || this.bassBoost.getEnabled()) {
                SetSpotifyEqFromDb();
            }
            AdsHandler adAdmob = new AdsHandler(this);
            adAdmob.BannerAd((RelativeLayout) findViewById(R.id.bannerAd), this);
            adAdmob.FullscreenAd(this);
        } else if (compoundButton == this.bassSwitch) {
            if (this.G) {
                this.bassBoost.setEnabled(true);
                if (!z2) {
                    this.intD = this.bassBoost.getRoundedStrength();
                    this.i0.setBBSlider(0);
                    this.bassSeekBar.setProgress(0);
                } else {
                    int i5 = this.intD;
                    if (i5 != -1) {
                        this.bassSeekBar.setProgress(i5);
                    }
                }
            } else {
                this.bassBoost.setEnabled(z2);
            }
            this.bassSeekBar.setEnabled(z2);
            this.i0.setbBSwitch(z2);
            boolean valueOf = Preferences.getInstance(getApplicationContext()).getAGCbbs();
            this.i = valueOf;
            if (z2) {
                if (valueOf) {
                    this.i = true;
                    Control_AGC_preference();
                }
                if (!this.l) {
                    new AlertDialog.Builder(this).setTitle(getString(R.string.BBSworkingTitle)).setMessage(getString(R.string.BBSworkingMessage)).setPositiveButton(getString(R.string.Yes), (dialogInterface, i) -> {
                        EqualizerActivity.this.bassSeekBar.setProgress(500);
                        EqualizerActivity.this.SetLabelsBBSVirtualDelayed();
                        dialogInterface.dismiss();
                    }).setNegativeButton(getString(R.string.No), (dialogInterface, i) -> {
                        dialogInterface.dismiss();
                        EqualizerActivity.this.Confirm_BBS_Disable();
                    }).create().show();
                    boolean bool = true;
                    this.l = bool;
                    Preferences.getInstance(getApplicationContext()).setBbsFirstEnableCheck(bool);
                }
            } else if (valueOf) {
                this.i = false;
                Control_AGC_preference();
            }
            this.c0 = z2;
            if (z2 || this.h0.getEnabled()) {
                SetSpotifyEqFromDb();
            }
        } else if (compoundButton == this.VirtualSwitch && this.U) {
            try {
                if (!this.virtualizer.hasControl()) {
                    this.virtualizer = this.i0.getVirtualizer();
                }
            } catch (Exception e) {
                this.virtualizer = this.i0.getVirtualizer();
            }
            this.virtualizer.setEnabled(z2);
            this.VirtualSeekBar.setEnabled(z2);
            this.i0.setVirSwitch(z2);
        }
        if (!this.aBoolean) {
            serviceChecker();
        }
    }

    public void Confirm_BBS_Disable() {
        new AlertDialog.Builder(this).setTitle(getString(R.string.DisableBBSq)).setMessage(getString(R.string.DisableBBSqMessage)).setPositiveButton(getString(R.string.Yes), (dialogInterface, i) -> {
            Switch switchR = EqualizerActivity.this.bassSwitch;
            boolean bool = false;
            switchR.setChecked(bool);
            EqualizerActivity.this.m = bool;
            Preferences.getInstance(EqualizerActivity.this.getApplicationContext()).setBbsPlugin(bool);
            EqualizerActivity.this.k.setVisibility(View.GONE);
            dialogInterface.dismiss();
        }).setNegativeButton(getString(R.string.No), (dialogInterface, i) -> dialogInterface.dismiss()).create().show();
    }

    public void BadReviewKiller() {
        new AlertDialog.Builder(this).setTitle(getString(R.string.HelpUsToImprove)).setMessage(getString(R.string.HelpUsToImproveMessage)).setPositiveButton("OK", (dialogInterface, i) -> dialogInterface.dismiss()).create().show();
    }

    public String milliHzToString(int i2) {
        if (i2 < 1000) {
            return "";
        }
        if (i2 < 1000000) {
            return "" + (i2 / 1000) + "Hz";
        }
        return "" + (i2 / 1000000) + "kHz";
    }

    public void onProgressChanged(SeekBar seekBar, int i2, boolean z2) {
        Log.e("Fabiolife", "onProgressChanged");
        if (!this.h0.hasControl()) {
            Log.e("equalizer", "hasControl");
            prepareEqualizer();
        }
        if (this.SeekBart0[0] == seekBar) {
            Log.e("yes", "" + 0);
            int i3 = this.o0;
            int i4 = (((this.n0 - i3) * i2) / 100) + i3;
            this.h0.setBandLevel((short) 0, (short) i4);
            if (this.i0.getIsCustomSelected()) {
                this.i0.setSlider(i4, 0);
                Log.e("Fabioset", "setSlider" + 0 + "to newLevel:" + i4);
                String format = String.format("%.1f", ((float) i4) / 100.0f);
                TextView textView = this.TextViewr0[0];
                textView.setText(format + " dB");
                if (this.a0) {
                    this.TextViews0[0].setText(format);
                }
            }
        }
        if (this.SeekBart0[1] == seekBar) {
            Log.e("yes", "" + 1);
            int i32 = this.o0;
            int i42 = (((this.n0 - i32) * i2) / 100) + i32;
            this.h0.setBandLevel((short) 1, (short) i42);
            if (this.i0.getIsCustomSelected()) {
                this.i0.setSlider(i42, 1);
                Log.e("Fabioset", "setSlider" + 1 + "to newLevel:" + i42);
                String format2 = String.format("%.1f", ((float) i42) / 100.0f);
                TextView textView2 = this.TextViewr0[1];
                textView2.setText(format2 + " dB");
                if (this.a0) {
                    this.TextViews0[1].setText(format2);
                }
            }
        }
        if (this.SeekBart0[2] == seekBar) {
            Log.e("yes", "" + 2);
            int i33 = this.o0;
            int i43 = (((this.n0 - i33) * i2) / 100) + i33;
            this.h0.setBandLevel((short) 2, (short) i43);
            if (this.i0.getIsCustomSelected()) {
                this.i0.setSlider(i43, 2);
                Log.e("Fabioset", "setSlider" + 2 + "to newLevel:" + i43);
                String format3 = String.format("%.1f", ((float) i43) / 100.0f);
                TextView textView3 = this.TextViewr0[2];
                textView3.setText(format3 + " dB");
                if (this.a0) {
                    this.TextViews0[2].setText(format3);
                }
            }
        }
        if (this.SeekBart0[3] == seekBar) {
            Log.e("yes", "" + 3);
            int i34 = this.o0;
            int i44 = (((this.n0 - i34) * i2) / 100) + i34;
            this.h0.setBandLevel((short) 3, (short) i44);
            if (this.i0.getIsCustomSelected()) {
                this.i0.setSlider(i44, 3);
                Log.e("Fabioset", "setSlider" + 3 + "to newLevel:" + i44);
                String format4 = String.format("%.1f", ((float) i44) / 100.0f);
                TextView textView4 = this.TextViewr0[3];
                textView4.setText(format4 + " dB");
                if (this.a0) {
                    this.TextViews0[3].setText(format4);
                }
            }
        }
        if (this.SeekBart0[4] == seekBar) {
            Log.e("yes", "" + 4);
            int i35 = this.o0;
            int i45 = (((this.n0 - i35) * i2) / 100) + i35;
            this.h0.setBandLevel((short) 4, (short) i45);
            if (this.i0.getIsCustomSelected()) {
                this.i0.setSlider(i45, 4);
                Log.e("Fabioset", "setSlider" + 4 + "to newLevel:" + i45);
                String format5 = String.format("%.1f", Float.valueOf(((float) i45) / 100.0f));
                TextView textView5 = this.TextViewr0[4];
                textView5.setText(format5 + " dB");
                if (this.a0) {
                    this.TextViews0[4].setText(format5);
                }
            }
        }
        if (this.h) {
            if (this.w && Preferences.getInstance(this).getManualGainValue() != -1) {
                Preferences.getInstance(this).setManualGainValue(-1);
                Log.e("FabioManGain", "Disabilito Gain Manuale -1");
            }
            DO_AGC();
        }
        boolean z3 = this.t;
        boolean bool = true;
        if (z3 == bool && !this.u) {
            Preferences.getInstance(this).setForceRunWorksNotified(bool);
            this.stringC = GetTestMessage();
            Bundle bundle = new Bundle();
            bundle.putBoolean("ForceRunFlag", this.t);
            bundle.putString("report_message", this.stringC);
            bundle.putLong("TS_First_Run", this.M);
            bundle.putBoolean("KeepServiceAlwaysOn", this.aBoolean);
        }
    }

    public void DO_AGC() {
        float f;
        if (!this.h0.hasControl()) {
            prepareEqualizer();
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        int i2 = 0;
        for (int i22 = 0; i22 < 5; i22++) {
            short bandLevel = this.h0.getBandLevel((short) i22);
            arrayList.add((int) bandLevel);
            i2 += bandLevel;
        }
        int intValue = (Integer) Collections.min(arrayList);
        int intValue2 = (Integer) Collections.max(arrayList);
        int abs = Math.abs((i2 / 5) * 1);
        int abs2 = (Math.abs(intValue) + Math.abs(intValue2)) / 10;
        float parseFloat = Float.parseFloat(Preferences.getInstance(getApplicationContext()).getAGCmode());
        float f2 = ((float) abs) + (((float) abs2) * parseFloat);
        if (!this.i || !this.bassBoost.getEnabled()) {
        } else {
            double d = (double) f2;
            double roundedStrength = (double) this.bassBoost.getRoundedStrength();
            Double.isNaN(roundedStrength);
            Double.isNaN(d);
            f2 = (float) (d + (roundedStrength * 0.6d));
        }
        if (!this.i || this.h0.getEnabled()) {
            f = f2;
        } else {
            double roundedStrength2 = (double) this.bassBoost.getRoundedStrength();
            Double.isNaN(roundedStrength2);
            f = (float) (roundedStrength2 * 0.6d);
        }
        try {
            if (!this.loudnessEnhancer.hasControl()) {
                prepareEqualizer();
            }
            if (this.w) {
                SeekBar seekBar = this.myGainBar;
                if (seekBar != null) {
                    seekBar.setProgress((int) f);
                    return;
                }
            }
            this.loudnessEnhancer.setTargetGain((int) f);
        } catch (Exception e) {
            Toast.makeText(this, "AGC ERROR! Another incompatible app is present on this device! Delete it and reboot the device! The AGC will be disabled", Toast.LENGTH_LONG).show();
            boolean bool = false;
            this.h = bool;
            Preferences.getInstance(this).setAGC(bool);
        }
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if (str.contains("slider") && !str.equals("loudslider")) {
            UpdateCardStroke(3);
        }
        if (str.equals("agc")) {
            if (sharedPreferences.getBoolean("agc", true)) {
                this.h = true;
                this.v.setText("AGC");
                Preferences.getInstance(getApplicationContext()).setManualGainValue(-1);
            } else {
                try {
                    if (!this.loudnessEnhancer.hasControl()) {
                        prepareEqualizer();
                    }
                    this.loudnessEnhancer.setTargetGain(0);
                } catch (Exception e) {
                }
                this.i0.setLoudSlider(0.0f);
                this.h = false;
                this.v.setText("Gain");
                this.v.setTextColor(this.colorStateList);
            }
            Control_AGC_preference();
        }
        if (str.equals("agc_bbs")) {
            this.i = sharedPreferences.getBoolean("agc_bbs", true);
            Control_AGC_preference();
        }
        if (str.equals("dark_theme")) {
            if (sharedPreferences.getBoolean("dark_theme", false)) {
                setTheme(R.style.AppTheme_Dark);
                recreate();
            } else {
                setTheme(R.style.AppTheme);
                recreate();
            }
        }
        if (str.equals("AdFreeVersion")) {
            Dialog_Pro_Version_Owned();
        }
        if (str.equals("gain_plugin")) {
            recreate();
            sharedPreferences.getBoolean("gain_plugin", true);
        }
        if (str.equals("bbs_plugin")) {
            if (!sharedPreferences.getBoolean("bbs_plugin", true)) {
                EqualizerVM equalizerViewModel = this.i0;
                boolean bool = false;
                equalizerViewModel.setbBSwitch(bool);
                this.bassSwitch.setChecked(bool);
                this.bassBoost.setEnabled(bool);
                this.m = bool;
                this.c0 = bool;
                Preferences.getInstance(getApplicationContext()).setBBSwitch(bool);
            }
            recreate();
        }
        if (str.equals("control_bar")) {
            recreate();
        }
        if (str.equals("agc_mode")) {
            Control_AGC_preference();
        }
        if (str.equals("keep_service_always_on")) {
            if (!sharedPreferences.getBoolean("keep_service_always_on", false)) {
                serviceChecker();
            } else {
                this.aBoolean = true;
                StartForegroundService();
            }
            recreate();
        }
        if (str.equals("virtualizer_plugin")) {
            if (sharedPreferences.getBoolean("virtualizer_plugin", true)) {
                findViewById(R.id.VirtualCardView).setVisibility(View.VISIBLE);
            } else if (this.U) {
                EqualizerVM equalizerViewModel2 = this.i0;
                boolean bool2 = false;
                equalizerViewModel2.setVirSwitch(bool2);
                this.VirtualSwitch.setChecked(bool2);
                this.virtualizer.setEnabled(bool2);
                Preferences.getInstance(getApplicationContext()).setVirSwitch(bool2);
                this.U = bool2;
            }
            recreate();
        }
        if (str.equals("zoom_eq_val") && sharedPreferences.getFloat("zoom_eq_val", 0.0f) == -1.0f) {
            Preferences.getInstance(getApplicationContext()).setZoomEqVal(0.0f);
            ResizeEq(1.0d);
            Dialog_Zoom_Eq();
        }
        if (str.equals("db_labels_always_present")) {
            boolean bool3 = false;
            this.a0 = sharedPreferences.getBoolean("db_labels_always_present", bool3);
            TextView textView = (TextView) findViewById(R.id.gain_space);
            if (sharedPreferences.getBoolean("db_labels_always_present", bool3)) {
                for (int i2 = 0; i2 < 5; i2++) {
                    this.TextViews0[i2].setVisibility(View.VISIBLE);
                }
                SetDbAlwaysPresentLables();
                textView.setVisibility(View.VISIBLE);
            } else {
                for (int i22 = 0; i22 < 5; i22++) {
                    this.TextViews0[i22].setVisibility(View.GONE);
                }
                textView.setVisibility(View.GONE);
            }
        }
        if (str.equals("auto_start_boot") && sharedPreferences.getBoolean("auto_start_boot", true) && !Settings.canDrawOverlays(this)) {
            Intent intent = new Intent(this, EqualizerActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            new AlertDialog.Builder(this).setTitle(R.string.Allow_Overlays).setMessage(R.string.Allow_Overlays_Message).setPositiveButton(R.string.Allow, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    String packageName = EqualizerActivity.this.getPackageName();
                    EqualizerActivity mainActivity = EqualizerActivity.this;
                    mainActivity.startActivityForResult(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + packageName)), 232);
                    dialogInterface.dismiss();
                }
            }).create().show();
        }
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
        if (!this.i0.getIsCustomSelected()) {
            for (int i2 = 0; i2 < 5; i2++) {
                int i22 = this.o0;
                this.i0.setSlider((((this.n0 - i22) * this.SeekBart0[i2].getProgress()) / 100) + i22, i2);
            }
            this.spinner.setSelection((this.arrayList.size() - 1) - this.stringArrayList.size());
        }
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        SetLabelsFreq();
    }

    public void Kill() {
        boolean bool = false;
        this.switchEnable.setChecked(bool);
        this.bassSwitch.setChecked(bool);
        if (this.U) {
            this.VirtualSwitch.setChecked(bool);
        }
        try {
            Intent intent = new Intent(this, NotificationService.class);
            intent.setAction(Constants.ACTION.STOPFOREGROUND_ACTION);
            startService(intent);
        } catch (Exception e) {
        }
        finishAffinity();
    }

    public void disablePreset() {
        this.spinner.setVisibility(View.GONE);
    }

    public void serviceChecker() {
        if (this.switchEnable.isChecked() || this.bassSwitch.isChecked()) {
            Intent intent = new Intent(this, NotificationService.class);
            intent.setAction(Constants.ACTION.STARTFOREGROUND_ACTION);
            startService(intent);
            return;
        }
        Intent intent2 = new Intent(this, NotificationService.class);
        intent2.setAction(Constants.ACTION.STOPFOREGROUND_ACTION);
        startService(intent2);
    }

    public void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
        ObservableObject.getInstance().deleteObservers();
    }

    public void update(Observable observable, Object obj) {
        if (!obj.toString().contains("playbackstatechanged")) {
            this.N = this.N + 1;
            if (Preferences.getInstance(this).getSpotifyConnection()) {
                Intent intent = (Intent) obj;
                String stringExtra = intent.getStringExtra("id");
                String stringExtra2 = intent.getStringExtra("artist");
                String stringExtra3 = intent.getStringExtra("album");
                String stringExtra4 = intent.getStringExtra("track");
                if (stringExtra == null || stringExtra2 == null || stringExtra3 == null || stringExtra4 == null) {
                    Toast.makeText(this, getString(R.string.MissingInfoSpotify), Toast.LENGTH_LONG).show();
                } else if (!stringExtra.equals(this.v0) || !stringExtra2.equals(this.X) || !stringExtra3.equals(this.W) || !stringExtra4.equals(this.w0)) {
                    this.O = this.O + 1;
                    this.v0 = stringExtra;
                    this.X = stringExtra2;
                    this.W = stringExtra3;
                    this.w0 = stringExtra4;
                    String str = this.v0;
                    if (str == null || this.X == null || this.W == null || this.w0 == null) {
                        Toast.makeText(this, getString(R.string.MissingInfoSpotify), Toast.LENGTH_LONG).show();
                    } else {
                        this.v0 = str.replaceAll("'", "-2283-");
                        this.X = this.X.replaceAll("'", "-2283-");
                        this.W = this.W.replaceAll("'", "-2283-");
                        this.w0 = this.w0.replaceAll("'", "-2283-");
                    }
                    if (this.switchEnable.isChecked() || this.bassSwitch.isChecked()) {
                        SetSpotifyEqFromDb();
                    }
                }
            }
        }
    }

    private void SetSpotifyEqFromDb() {
        if (!(this.o != Boolean.FALSE || this.v0 == null || this.X == null || this.W == null || this.w0 == null || !Preferences.getInstance(this).getSpotifyConnection())) {
            if (!this.h0.hasControl()) {
                prepareEqualizer();
            }
            this.q = this.myDBHelper.getIdFromTrackid(this.v0);
            this.p = this.myDBHelper.getIdFromAlbumName(this.W);
            this.myDBHelper.getIdFromArtistName(this.X);
            if (this.q != -1) {
                DatabaseHelper dBHelper = this.myDBHelper;
                ArrayList<String> eqValues = dBHelper.getEqValues(dBHelper.getIdFromTrackid(this.v0));
                for (int i2 = 0; i2 < 5; i2++) {
                    int parseInt = Integer.parseInt(eqValues.get(i2 + 8));
                    this.SeekBart0[i2].setProgress(parseInt);
                    this.spinner.setSelection((this.arrayList.size() - this.stringArrayList.size()) + -1);
                }
                if (this.m) {
                    this.bassSwitch.setChecked(Boolean.parseBoolean(eqValues.get(5)));
                }
                this.bassSeekBar.setProgress(Integer.parseInt(eqValues.get(6)));
            } else if (this.p != -1) {
                DatabaseHelper dBHelper2 = this.myDBHelper;
                ArrayList<String> eqValues2 = dBHelper2.getEqValues(dBHelper2.getIdFromAlbumName(this.W));
                for (int i22 = 0; i22 < 5; i22++) {
                    int parseInt2 = Integer.parseInt(eqValues2.get(i22 + 8));
                    this.SeekBart0[i22].setProgress(parseInt2);
                    this.spinner.setSelection((this.arrayList.size() - this.stringArrayList.size()) + -1);
                }
                if (this.m) {
                    this.bassSwitch.setChecked(Boolean.parseBoolean(eqValues2.get(5)));
                }
                this.bassSeekBar.setProgress(Integer.parseInt(eqValues2.get(6)));
            }
            UpdateSpotifyButtons();
        }
        SetLabelsBBSVirtualDelayed();
        SetLabelsFreqDelayed();
    }

    public void UpdateSpotifyButtons() {
        this.q = this.myDBHelper.getIdFromTrackid(this.v0);
        this.p = this.myDBHelper.getIdFromAlbumName(this.W);
        this.myDBHelper.getIdFromArtistName(this.X);
        if (this.q != -1) {
            UpdateCardStroke(2);
        } else if (this.p != -1) {
            UpdateCardStroke(2);
        } else {
            UpdateCardStroke(3);
        }
    }

    public void UpdateCardStroke(Integer num) {
        if (this.a0) {
            SetDbAlwaysPresentLables();
        }
    }

    public void SetLabelsBBSVirtualDelayed() {
        new Handler().postDelayed(() -> {
            bassSwitch.setText("Bass");
            VirtualSwitch.setText("3D");
        }, 500);
    }

    public void SetLabelsFreqDelayed() {
        new Handler().postDelayed(() -> {
            int i = 0;
            while (true) {
                EqualizerActivity mainActivity = EqualizerActivity.this;
                if (i < mainActivity.q0 && i < 5) {
                    try {
                        int centerFreq = mainActivity.h0.getCenterFreq((short) i);
                        EqualizerActivity mainActivity2 = EqualizerActivity.this;
                        mainActivity2.SeekBart0[i].setOnSeekBarChangeListener(mainActivity2);
                        EqualizerActivity mainActivity3 = EqualizerActivity.this;
                        mainActivity3.TextViewr0[i].setText(mainActivity3.milliHzToString(centerFreq));
                        i++;
                    } catch (Exception e) {
                        return;
                    }
                } else {
                    return;
                }
            }
        }, 500);
    }

    public void onResume() {
        super.onResume();
        if (this.j0) {
            this.h0 = this.i0.getEqualizer();
            this.bassBoost = this.i0.getBassBoost();
            this.loudnessEnhancer = this.i0.getLoudnessEnhancer();
            this.h = this.h0.getEnabled() && Preferences.getInstance(getApplicationContext()).getAGC();
            Control_AGC_preference();
            if (Preferences.getInstance(getApplicationContext()).getManualGainValue() != -1 && this.w) {
                if (!this.loudnessEnhancer.hasControl()) {
                    prepareEqualizer();
                }
                if (this.w) {
                    SeekBar seekBar = this.myGainBar;
                    if (seekBar != null) {
                        seekBar.setProgress(Preferences.getInstance(getApplicationContext()).getManualGainValue());
                    }
                }
            }
            if (!this.h0.hasControl()) {
                prepareEqualizer();
            }
            ObservableObject.getInstance().addObserver(this);
            this.X = "";
            boolean z2 = this.o;
            boolean bool = false;
            if (z2 == bool && this.t == bool) {
                CheckError_CloseApp();
            } else if (this.t) {
                String GetTestMessage = GetTestMessage();
                this.stringC = GetTestMessage;
                if (GetTestMessage.length() != 0) {
                    Bundle bundle = new Bundle();
                    bundle.putString("report_message", this.stringC);
                    bundle.putLong("TS_First_Run", this.M);
                    bundle.putBoolean("OnResError", this.F);
                    bundle.putInt("WasOnResInErrorCount", this.V);
                    bundle.putBoolean("DisableApp", this.o);
                    bundle.putBoolean("ForceRunFlag", this.t);
                    bundle.putBoolean("ForceRunWorksNotified", this.u);
                    bundle.putBoolean("KeepServiceAlwaysOn", this.aBoolean);
                }
            }
            if (!Preferences.getInstance(getApplicationContext()).getDontShowAgainWarningApps()) {
                WarningAppListAlertDialog(WarningAppsListCreate());
            }
            if (!this.m) {
                this.bassSwitch.setChecked(bool);
            }
            if (!this.U) {
                this.VirtualSwitch.setChecked(bool);
            }
        }
    }

    private void AudioEffectNotWorking() {
        new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AppTheme)).setTitle(getString(R.string.Warning)).setMessage(R.string.AudioResourceError).setPositiveButton(getString(R.string.Exit), (dialogInterface, i) -> {
            EqualizerActivity.this.Kill();
            dialogInterface.dismiss();
        }).setCancelable(false).show();
    }

    @SuppressLint("ResourceType")
    private void CheckError_CloseApp() {
        this.stringC = GetTestMessage();
        this.t = Preferences.getInstance(getApplicationContext()).getForceRunFlag();
        this.V = Preferences.getInstance(this).getWasOnResInErrorCount();
        if (this.stringC.length() != 0) {
            boolean bool = true;
            this.F = bool;
            this.o = bool;
            Preferences.getInstance(getApplicationContext()).setOnResError(this.F);
            Bundle bundle = new Bundle();
            bundle.putString("report_message", this.stringC);
            bundle.putLong("TS_First_Run", this.M);
            bundle.putBoolean("OnResError", this.F);
            bundle.putInt("WasOnResInErrorCount", this.V);
            bundle.putBoolean("DisableApp", this.o);
            bundle.putBoolean("ForceRunFlag", this.t);
            bundle.putBoolean("ForceRunWorksNotified", this.u);
            bundle.putBoolean("KeepServiceAlwaysOn", this.aBoolean);
            View inflate = View.inflate(this, R.layout.checkbox, (ViewGroup) null);
            final CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.checkbox);
            checkBox.setChecked(this.t);
            checkBox.setOnCheckedChangeListener((compoundButton, z) -> {
            });

            checkBox.setText(getString(R.string.ForceRun));
            checkBox.setTextSize(11.0f);
            try {
                ((TextView) new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AppTheme)).setTitle(getString(R.string.Warning)).setCancelable(false).setView(inflate).setMessage(getString(R.string.ErrorOnResCloseApp)).setPositiveButton(getString(R.string.Exit), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EqualizerActivity.this.Kill();
                        if (checkBox.isChecked()) {
                            EqualizerActivity.this.t = true;
                            Preferences.getInstance(EqualizerActivity.this.getApplicationContext()).setForceRunFlag(EqualizerActivity.this.t);
                            new Handler().postDelayed(this::RestartApp, 500);
                            return;
                        }
                        EqualizerActivity.this.t = false;
                        Preferences.getInstance(EqualizerActivity.this.getApplicationContext()).setForceRunFlag(EqualizerActivity.this.t);
                        dialogInterface.dismiss();
                        EqualizerActivity.this.finish();
                    }

                    public void RestartApp() {
                        ((AlarmManager) EqualizerActivity.this.getApplicationContext().getSystemService(Context.ALARM_SERVICE)).set(AlarmManager.RTC, System.currentTimeMillis() + 100, PendingIntent.getActivity(EqualizerActivity.this.getApplicationContext(), 123456, new Intent(EqualizerActivity.this.getApplicationContext(), EqualizerActivity.class), 268435456));
                        System.exit(0);
                    }
                }).setNeutralButton("Exit", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                    EqualizerActivity.this.finish();
                }).show().findViewById(16908299)).setTextSize(11.0f);
            } catch (Exception e) {
            }
        } else if (this.F) {
            this.V++;
            new Bundle().putInt("WasOnResInErrorCount", this.V);
            this.F = false;
            Preferences.getInstance(getApplicationContext()).setWasOnResInError(this.V);
            Preferences.getInstance(getApplicationContext()).setOnResError(this.F);
        }
    }

    public void onPause() {
        super.onPause();
    }

    public void prepareEqualizer() {
        StartForegroundService();
        if (this.h0 != null) {
            releaseEqualizer();
        }
        if (this.aBoolean) {
            Equalizer equalizer2 = this.i0.getEqualizer();
            this.h0 = equalizer2;
            equalizer2.setEnabled(true);
            this.bassBoost = this.i0.getBassBoost();
            this.h0.setEnabled(true);
            this.loudnessEnhancer = this.i0.getLoudnessEnhancer();
            if (this.U) {
                this.virtualizer = this.i0.getVirtualizer();
            }
        } else {
            Equalizer equalizer3 = new Equalizer(Integer.MAX_VALUE, 0);
            this.h0 = equalizer3;
            equalizer3.setEnabled(true);
            this.bassBoost = new BassBoost(Integer.MAX_VALUE, 0);
            this.h0.setEnabled(true);
            this.loudnessEnhancer = new LoudnessEnhancer(0);
            if (this.U) {
                this.virtualizer = new Virtualizer(Integer.MAX_VALUE, 0);
            }
        }
        this.q0 = this.h0.getNumberOfBands();
        this.h0.getBandLevelRange();
    }

    private void releaseEqualizer() {
        unbindSystemEqualizer();
        release(this.h0);
        release(this.bassBoost);
        release(this.loudnessEnhancer);
    }

    private void unbindSystemEqualizer() {
        Intent intent = new Intent("android.media.action.CLOSE_AUDIO_EFFECT_CONTROL_SESSION");
        intent.putExtra("android.media.extra.AUDIO_SESSION", 0);
        intent.putExtra("android.media.extra.PACKAGE_NAME", getApplicationContext().getPackageName());
        getApplicationContext().sendBroadcast(intent);
    }

    private void release(AudioEffect audioEffect) {
        if (audioEffect != null) {
            audioEffect.setControlStatusListener((AudioEffect.OnControlStatusChangeListener) null);
            audioEffect.setEnableStatusListener((AudioEffect.OnEnableStatusChangeListener) null);
            if (audioEffect instanceof Equalizer) {
                ((Equalizer) audioEffect).setParameterListener((Equalizer.OnParameterChangeListener) null);
            } else if (audioEffect instanceof BassBoost) {
                ((BassBoost) audioEffect).setParameterListener((BassBoost.OnParameterChangeListener) null);
            }
        }
    }

    private String GetTestMessage() {
        this.stringC = "";
        if (this.h0 == null) {
            this.stringC += "Eq";
        }
        if (!this.h0.hasControl()) {
            this.stringC += "Co";
        }
        if (!this.h0.getEnabled() && this.switchEnable.isChecked()) {
            this.stringC += "En";
        }
        if (this.i0.getEqualizer() == null) {
            this.stringC += "Vm";
        }
        if (this.stringC.length() != 0 && !((PowerManager) getSystemService(Context.POWER_SERVICE)).isIgnoringBatteryOptimizations(getApplicationContext().getPackageName())) {
            this.stringC += "Bo";
        }
        return this.stringC;
    }

    public void onActivityResult(int i2, int i22, Intent intent) {
        super.onActivityResult(i2, i22, intent);
        if (i2 == 232 && !Settings.canDrawOverlays(this)) {
            Preferences.getInstance(getApplicationContext()).setAutoStartBoot(false);
            Toast.makeText(this, R.string.Autostart_not_activated_warning, Toast.LENGTH_LONG).show();
        }
    }

    public ArrayList<ResolveInfo> WarningAppsListCreate() {
        List<ResolveInfo> queryIntentActivities = getPackageManager().queryIntentActivities(new Intent("android.media.action.DISPLAY_AUDIO_EFFECT_CONTROL_PANEL"), 0);
        ArrayList<ResolveInfo> arrayList = new ArrayList<>();
        for (int i2 = 0; i2 < queryIntentActivities.size(); i2++) {
            ResolveInfo resolveInfo = queryIntentActivities.get(i2);
            if (!resolveInfo.activityInfo.packageName.equals(getPackageName()) && !resolveInfo.activityInfo.packageName.contains("com.android.musicfx") && !resolveInfo.activityInfo.packageName.contains("com.sec.android.app.soundalive") && !resolveInfo.activityInfo.packageName.contains("com.android.settings") && !resolveInfo.activityInfo.packageName.contains("com.miui.audioeffect") && !resolveInfo.activityInfo.packageName.contains("com.dolby.daxappui") && !resolveInfo.activityInfo.packageName.contains("com.motorola.audiofx") && !resolveInfo.activityInfo.packageName.contains("com.dolby.dax2appUI") && !resolveInfo.activityInfo.packageName.contains("com.sonyericsson.soundenhancement")) {
                String str = resolveInfo.activityInfo.packageName;
                new Bundle().putString("Warning_app", str);
                arrayList.add(resolveInfo);
            }
        }
        return arrayList;
    }

    public void WarningAppListAlertDialog(ArrayList arrayList) {
        Drawable drawable;
        final String str;
        Drawable drawable2;
        int size = arrayList.size();
        if (size > 0) {
            View inflate = LayoutInflater.from(this).inflate(R.layout.dialog_warning_apps, (ViewGroup) null);
            AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AppTheme));
            View findViewById = inflate.findViewById(R.id.ViewWarningApp1);
            View findViewById2 = inflate.findViewById(R.id.ViewWarningApp2);
            ImageView imageView = (ImageView) inflate.findViewById(R.id.IconWarningApp1);
            ImageView imageView2 = (ImageView) inflate.findViewById(R.id.IconWarningApp2);
            TextView textView = (TextView) inflate.findViewById(R.id.NameWarningApp1);
            TextView textView2 = (TextView) inflate.findViewById(R.id.NameWarningApp2);
            final CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.checkboxShoWA);
            TextView textView3 = (TextView) inflate.findViewById(R.id.TxtMoreWaringApps);
            if (size > 2) {
                textView3.setVisibility(View.VISIBLE);
                textView3.setText(getString(R.string.MoreDetectedApps) + ": " + size);
            } else {
                textView3.setVisibility(View.GONE);
            }
            final String str2 = ((ResolveInfo) arrayList.get(0)).activityInfo.packageName;
            textView.setText(str2);
            try {
                drawable = getApplicationContext().getPackageManager().getApplicationIcon(str2);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                drawable = null;
            }
            imageView.setImageDrawable(drawable);
            findViewById2.setVisibility(View.GONE);
            if (arrayList.size() > 1) {
                findViewById2.setVisibility(View.VISIBLE);
                str = ((ResolveInfo) arrayList.get(1)).activityInfo.packageName;
                textView2.setText(str);
                try {
                    drawable2 = getApplicationContext().getPackageManager().getApplicationIcon(str);
                } catch (PackageManager.NameNotFoundException e2) {
                    e2.printStackTrace();
                    drawable2 = null;
                }
                imageView2.setImageDrawable(drawable2);
            } else {
                str = "";
            }
            findViewById.setOnClickListener(view -> {
                try {
                    Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                    intent.setData(Uri.parse("package:" + str2));
                    EqualizerActivity.this.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    EqualizerActivity.this.startActivity(new Intent("android.settings.MANAGE_APPLICATIONS_SETTINGS"));
                }
            });
            findViewById2.setOnClickListener(view -> {
                try {
                    Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                    intent.setData(Uri.parse("package:" + str));
                    EqualizerActivity.this.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    EqualizerActivity.this.startActivity(new Intent("android.settings.MANAGE_APPLICATIONS_SETTINGS"));
                }
            });
            builder.setTitle(R.string.Warning).setView(inflate).setPositiveButton("Ok", (dialogInterface, i) -> {
                Preferences.getInstance(EqualizerActivity.this.getApplicationContext()).setDontShowAgainWarningApps(checkBox.isChecked());
                dialogInterface.cancel();
            }).show();
        }
    }

    public void DeveloperOptions() {
        AlertDialog.Builder title = new AlertDialog.Builder(this).setTitle("Developer Options");
        title.setMessage("TestMessage: " + GetTestMessage()).setPositiveButton("UnregisterReveiver", (dialogInterface, i) -> dialogInterface.dismiss()).setNegativeButton("PrepareEqualizer", (dialogInterface, i) -> {
            EqualizerActivity.this.prepareEqualizer();
            dialogInterface.dismiss();
        }).setNeutralButton("PrepareEqLocalCalls", (dialogInterface, i) -> {
            EqualizerActivity.this.h0 = new Equalizer(Integer.MAX_VALUE, 0);
            EqualizerActivity.this.h0.setEnabled(true);
            EqualizerActivity.this.bassBoost = new BassBoost(Integer.MAX_VALUE, 0);
            EqualizerActivity.this.h0.setEnabled(true);
            EqualizerActivity.this.loudnessEnhancer = new LoudnessEnhancer(0);
            EqualizerActivity mainActivity = EqualizerActivity.this;
            if (mainActivity.U) {
                mainActivity.virtualizer = new Virtualizer(Integer.MAX_VALUE, 0);
            }
            dialogInterface.dismiss();
        }).show();
    }

    private void PausePlay() {
        if (((AudioManager) getSystemService(Context.AUDIO_SERVICE)).isMusicActive()) {
            MediaButtonIntent(85);
            new Handler().postDelayed(() -> EqualizerActivity.this.MediaButtonIntent(85), 1000);
        }
    }

    public void MediaButtonIntent(int i2) {
        Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
        intent.setPackage("com.spotify.music");
        synchronized (this) {
            intent.putExtra("android.intent.extra.KEY_EVENT", new KeyEvent(0, i2));
            getApplicationContext().sendOrderedBroadcast(intent, (String) null);
            intent.putExtra("android.intent.extra.KEY_EVENT", new KeyEvent(1, i2));
            getApplicationContext().sendOrderedBroadcast(intent, (String) null);
        }
    }

    public void Dialog_Zoom_Eq() {
        final double[] dArr = new double[1];
        View inflate = LayoutInflater.from(this).inflate(R.layout.dialog_seekbar_zoom_eq, (ViewGroup) null);
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AppTheme_Dark));
        final TextView textView = (TextView) inflate.findViewById(R.id.textViewZoomEq);
        ((SeekBar) inflate.findViewById(R.id.Zoom_Eq_SeekBar)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                double d = (double) i;
                dArr[0] = ((d * 1.0d / 100.0d) * 3.0d) + 1.0d;
                dArr[0] = round(dArr[0], 2);
                EqualizerActivity.this.ResizeEq(dArr[0]);
                textView.setText(EqualizerActivity.this.getString(R.string.Vertical_Zoom_Equalizer) + " x" + dArr[0]);
            }
        });
        builder.setPositiveButton("Ok", (dialogInterface, i) -> {
            Preferences.getInstance(EqualizerActivity.this.getApplicationContext()).setZoomEqVal((float) dArr[0]);
            dialogInterface.cancel();
        }).setView(inflate).setNegativeButton("Exit", (dialogInterface, i) -> dialogInterface.cancel()).show();
    }
}