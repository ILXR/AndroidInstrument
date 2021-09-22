package com.example.instrument.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.instrument.R;

import java.util.ArrayList;
import java.util.Random;

public class GuitarActivity extends Activity {

    private static final LinearLayout.LayoutParams linerParams   = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1f);
    private static final String                    TAG           = "GuitarActivity";
    private final        int                       image_id      = new Random().nextInt();
    private final        ArrayList<View>           guitar_string = new ArrayList<>();

    private void playGuitar(View layout) {
        // TODO 播放吉他音乐
        Log.i(TAG, "play guitar : " + layout.getId());
        ImageView img = layout.findViewById(image_id);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.string_scale);
        animation.setDuration(1000);
        animation.setInterpolator(new DecelerateInterpolator());
        img.startAnimation(animation);
    }

    private void playGuitar(int id) {
        View layout = guitar_string.get(id);
        if (layout != null) {
            playGuitar(layout);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrument);

        LinearLayout container = findViewById(R.id.stringContainer);
        for (int i = 0; i < 7; i++) {
            LinearLayout layout = new LinearLayout(this);
            layout.setLayoutParams(linerParams);
            layout.setGravity(Gravity.CENTER);
            layout.setId(i);

            ImageView img = new ImageView(this);
            img.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 5 + i * 2));
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            img.setImageResource(R.mipmap.string);
            img.setId(image_id);

            layout.setOnClickListener(this::playGuitar);
            layout.addView(img);
            container.addView(layout);
            guitar_string.add(layout);
        }

    }
}
