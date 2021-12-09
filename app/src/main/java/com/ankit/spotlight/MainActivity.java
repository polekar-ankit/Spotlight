package com.ankit.spotlight;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.ankit.spotlight.prefs.PreferencesManager;
import com.ankit.spotlight.utils.SpotlightSequence;
import com.ankit.spotlight.utils.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Random;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {



//    static {
//        AppCompatDelegate.setCompatVectorFromSourcesEnabled(true);
//    }

    private FloatingActionButton fab;
    private static final String INTRO_CARD = "fab_intro";
    private static final String INTRO_SWITCH = "switch_intro";
    private static final String INTRO_RESET = "reset_intro";
    private static final String INTRO_REPEAT = "repeat_intro";
    private static final String INTRO_CHANGE_POSITION = "change_position_intro";
    private static final String INTRO_SEQUENCE = "sequence_intro";
    private boolean isRevealEnabled = true;
    private SpotlightView spotLight;

    TextView switchAnimation;
    TextView reset;
    TextView resetAndPlay;
    TextView changePosAndPlay;
    TextView startSequence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reset = findViewById(R.id.reset);
        resetAndPlay = findViewById(R.id.resetAndPlay);
        changePosAndPlay = findViewById(R.id.changePosAndPlay);
        startSequence = findViewById(R.id.startSequence);


        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Fab Clicked!", Toast.LENGTH_LONG).show();
            }
        });

        switchAnimation.setOnClickListener(this);
        reset.setOnClickListener(this);
        resetAndPlay.setOnClickListener(this);
        changePosAndPlay.setOnClickListener(this);
        startSequence.setOnClickListener(this);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                showIntro(fab, INTRO_CARD);
            }
        }, 2400);
    }

    @Override
    public void onClick(View view) {
        PreferencesManager mPreferencesManager = new PreferencesManager(MainActivity.this);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int screenWidth = displaymetrics.widthPixels;
        int screenHeight = displaymetrics.heightPixels;

        switch (view.getId()) {

            case R.id.switchAnimation:
                if (isRevealEnabled) {
                    switchAnimation.setText("Switch to Reveal");
                    isRevealEnabled = false;
                } else {
                    switchAnimation.setText("Switch to Fadein");
                    isRevealEnabled = true;
                }
                mPreferencesManager.resetAll();
                break;

            case R.id.reset:
                mPreferencesManager.resetAll();
                break;
            case R.id.resetAndPlay:
                mPreferencesManager.resetAll();
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showIntro(fab, INTRO_CARD);
                    }
                }, 400);
                break;
            case R.id.changePosAndPlay:
                mPreferencesManager.resetAll();
                Random r = new Random();
                int right = r.nextInt((screenWidth - Utils.dpToPx(16)) - 16) + 16;
                int bottom = r.nextInt((screenHeight - Utils.dpToPx(16)) - 16) + 16;
                CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
                params.setMargins(Utils.dpToPx(16), Utils.dpToPx(16), right, bottom);
                fab.setLayoutParams(params);
                break;
            case R.id.startSequence:
                mPreferencesManager.resetAll();
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        SpotlightSequence.getInstance(MainActivity.this,null)
                                .addSpotlight(switchAnimation, "Switch Animation", "Click to swtich the animation", INTRO_SWITCH)
                                .addSpotlight(reset, "Reset ", "Click here to reset preferences", INTRO_RESET)
                                .addSpotlight(resetAndPlay, "Play Again", "Click here to play again", INTRO_REPEAT)
                                .addSpotlight(changePosAndPlay, "Change Position", "Click here to change position and replay", INTRO_CHANGE_POSITION)
                                .addSpotlight(startSequence, "Start sequence", "Well.. you just clicked here", INTRO_SEQUENCE)
                                .addSpotlight(fab,"Love", "Like the picture?\n" + "Let others know.", INTRO_CARD)
                                .startSequence();
                    }
                },400);
                break;
        }
    }

    private void showIntro(View view, String usageId) {
        spotLight = new SpotlightView.Builder(this)
                .introAnimationDuration(400)
                .enableRevealAnimation(isRevealEnabled)
                .performClick(true)
                .fadeinTextDuration(400)
                //.setTypeface(FontUtil.get(this, "RemachineScript_Personal_Use"))
                .headingTvColor(Color.parseColor("#eb273f"))
                .headingTvSize(32)
                .headingTvText("Love")
                .subHeadingTvColor(Color.parseColor("#ffffff"))
                .subHeadingTvSize(16)
                .setSkipButtonTextSize(12)
                .subHeadingTvText("Like the picture?\nLet others know.")
                .maskColor(Color.parseColor("#dc000000"))
                .target(view)
                .lineAnimDuration(400)
                .lineAndArcColor(Color.parseColor("#eb273f"))
                .dismissOnTouch(true)
                .dismissOnBackPress(true)
                .enableDismissAfterShown(true)
                .usageId(usageId) //UNIQUE ID
                .setSkipListener(() -> Toast.makeText(MainActivity.this,"SKipp Button Click",Toast.LENGTH_SHORT).show())
                .show();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if(spotLight.isShown()){
            spotLight.removeSpotlightView(false);//Remove current spotlight view from parent
            resetAndPlay.performClick();//Show it again in new orientation if required.
        }
    }
}

