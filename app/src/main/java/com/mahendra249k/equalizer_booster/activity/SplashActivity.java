package com.mahendra249k.equalizer_booster.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import androidx.appcompat.app.AppCompatActivity;

import com.mahendra249k.equalizer_booster.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            findViewById(R.id.progressThumb).setVisibility(View.VISIBLE);
            Animation anim = new AlphaAnimation(0.6f, 1.0f);
            anim.setDuration(700);
            anim.setStartOffset(20);
            anim.setRepeatMode(Animation.REVERSE);
            anim.setRepeatCount(Animation.INFINITE);
            findViewById(R.id.progressBar).startAnimation(anim);

            Animation anim2 = new AlphaAnimation(1.0f, 0.6f);
            anim2.setDuration(20);
            anim2.setStartOffset(700);
            anim2.setRepeatMode(Animation.REVERSE);
            anim2.setRepeatCount(Animation.INFINITE);
            findViewById(R.id.progressThumb).startAnimation(anim2);

            findViewById(R.id.progressBar).setOnClickListener(v -> {
                startActivity(new Intent(SplashActivity.this
                        , EqualizerActivity.class));
                finish();
            });

        }, 2500);

    }
}