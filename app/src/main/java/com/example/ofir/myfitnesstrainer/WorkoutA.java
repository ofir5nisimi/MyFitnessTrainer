package com.example.ofir.myfitnesstrainer;

import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkoutA extends AppCompatActivity {

    private static final String TAG = "WorkoutA";
    private static final String MY_PREFS = "my_prefs";

    private static final String WEIGHT1 = "WEIGHT1";
    private static final String WEIGHT2 = "WEIGHT2";
    private static final String WEIGHT3 = "WEIGHT3";
    private static final String WEIGHT4 = "WEIGHT4";
    private static final String WEIGHT5 = "WEIGHT5";
    private static final String WEIGHT6 = "WEIGHT6";
    private static final String WEIGHT7 = "WEIGHT7";

    private static final String DEVICE1 = "DEVICE1";
    private static final String DEVICE2 = "DEVICE2";
    private static final String DEVICE3 = "DEVICE3";
    private static final String DEVICE4 = "DEVICE4";
    private static final String DEVICE5 = "DEVICE5";
    private static final String DEVICE6 = "DEVICE6";
    private static final String DEVICE7 = "DEVICE7";

    private static final String BEAT_SOUND = "metro_beat1.wav";
    private static final String BAR_SOUND = "metro_bar1.wav";

    private SoundPool sounds;
    List<Integer> soundIds = new ArrayList<>();

    EditText etWeight1, etWeight2, etWeight3, etWeight4, etWeight5, etWeight6, etWeight7;

    MaterialBetterSpinner betterSpinner1, betterSpinner2, betterSpinner3, betterSpinner4,
            betterSpinner5, betterSpinner6, betterSpinner7;

    Button bStartTempo;
    public boolean tempoOn = false;

    String[] devicesNameArr = {"Chest Press", "Pectoral Fly", "Shoulder Press", "Triceps Press",
            "Biceps Curl", "Biceps Curl (Opposite)", "Biceps Side curl", "Pulldown", "Row",
            "Lower Back Ups", "Triceps Pulls", "Triceps press", "Seated Leg Press", "Leg " +
            "Extension", "Seated Leg Curl", ""};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_a);

        etWeight1 = (EditText) findViewById(R.id.et_weight_1);
        etWeight2 = (EditText) findViewById(R.id.et_weight_2);
        etWeight3 = (EditText) findViewById(R.id.et_weight_3);
        etWeight4 = (EditText) findViewById(R.id.et_weight_4);
        etWeight5 = (EditText) findViewById(R.id.et_weight_5);
        etWeight6 = (EditText) findViewById(R.id.et_weight_6);
        etWeight7 = (EditText) findViewById(R.id.et_weight_7);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout
                .simple_dropdown_item_1line, devicesNameArr);

        betterSpinner1 = (MaterialBetterSpinner)
                findViewById(R.id.drop_list_1);

        betterSpinner2 = (MaterialBetterSpinner)
                findViewById(R.id.drop_list_2);

        betterSpinner3 = (MaterialBetterSpinner)
                findViewById(R.id.drop_list_3);

        betterSpinner4 = (MaterialBetterSpinner)
                findViewById(R.id.drop_list_4);

        betterSpinner5 = (MaterialBetterSpinner)
                findViewById(R.id.drop_list_5);

        betterSpinner6 = (MaterialBetterSpinner)
                findViewById(R.id.drop_list_6);

        betterSpinner7 = (MaterialBetterSpinner)
                findViewById(R.id.drop_list_7);

        betterSpinner1.setAdapter(arrayAdapter);
        betterSpinner2.setAdapter(arrayAdapter);
        betterSpinner3.setAdapter(arrayAdapter);
        betterSpinner4.setAdapter(arrayAdapter);
        betterSpinner5.setAdapter(arrayAdapter);
        betterSpinner6.setAdapter(arrayAdapter);
        betterSpinner7.setAdapter(arrayAdapter);

        bStartTempo = (Button) findViewById(R.id.b_start_tempo);

        SharedPreferences pref = getApplicationContext().getSharedPreferences(MY_PREFS,
                MODE_PRIVATE);

        etWeight1.setText(pref.getString(WEIGHT1, ""));
        etWeight2.setText(pref.getString(WEIGHT2, ""));
        etWeight3.setText(pref.getString(WEIGHT3, ""));
        etWeight4.setText(pref.getString(WEIGHT4, ""));
        etWeight5.setText(pref.getString(WEIGHT5, ""));
        etWeight6.setText(pref.getString(WEIGHT6, ""));
        etWeight7.setText(pref.getString(WEIGHT7, ""));

        betterSpinner1.setText(pref.getString(DEVICE1, ""));
        betterSpinner2.setText(pref.getString(DEVICE2, ""));
        betterSpinner3.setText(pref.getString(DEVICE3, ""));
        betterSpinner4.setText(pref.getString(DEVICE4, ""));
        betterSpinner5.setText(pref.getString(DEVICE5, ""));
        betterSpinner6.setText(pref.getString(DEVICE6, ""));
        betterSpinner7.setText(pref.getString(DEVICE7, ""));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            createNewSoundpool();
        } else {
            createOldSoundpool();
        }

        try {
            AssetFileDescriptor descriptor1 = getAssets().openFd(BAR_SOUND);
            AssetFileDescriptor descriptor2 = getAssets().openFd(BEAT_SOUND);

            soundIds.add(sounds.load(descriptor1, 1));
            soundIds.add(sounds.load(descriptor2, 1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createOldSoundpool() {
        sounds = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void createNewSoundpool() {
        AudioAttributes attributes = new AudioAttributes.Builder().setUsage(AudioAttributes
                .USAGE_GAME).setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build();

        sounds = new SoundPool.Builder().setAudioAttributes(attributes).build();
    }

    public void playSound(int fileNumber) {
        sounds.play(soundIds.get(fileNumber), 1f, 1f, 1, 0, 1);
    }

    public void clickHandlerSaveData(View view) {
        String wieght1Str = etWeight1.getText().toString();
        String wieght2Str = etWeight2.getText().toString();
        String wieght3Str = etWeight3.getText().toString();
        String wieght4Str = etWeight4.getText().toString();
        String wieght5Str = etWeight5.getText().toString();
        String wieght6Str = etWeight6.getText().toString();
        String wieght7Str = etWeight7.getText().toString();

        String device1Str = betterSpinner1.getText().toString();
        String device2Str = betterSpinner2.getText().toString();
        String device3Str = betterSpinner3.getText().toString();
        String device4Str = betterSpinner4.getText().toString();
        String device5Str = betterSpinner5.getText().toString();
        String device6Str = betterSpinner6.getText().toString();
        String device7Str = betterSpinner7.getText().toString();

        SharedPreferences pref = getApplicationContext().getSharedPreferences(MY_PREFS,
                MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putString(WEIGHT1, wieght1Str);
        editor.putString(WEIGHT2, wieght2Str);
        editor.putString(WEIGHT3, wieght3Str);
        editor.putString(WEIGHT4, wieght4Str);
        editor.putString(WEIGHT5, wieght5Str);
        editor.putString(WEIGHT6, wieght6Str);
        editor.putString(WEIGHT7, wieght7Str);

        editor.putString(DEVICE1, device1Str);
        editor.putString(DEVICE2, device2Str);
        editor.putString(DEVICE3, device3Str);
        editor.putString(DEVICE4, device4Str);
        editor.putString(DEVICE5, device5Str);
        editor.putString(DEVICE6, device6Str);
        editor.putString(DEVICE7, device7Str);

        editor.apply();

        Toast toast = Toast.makeText(getApplicationContext(), "Saved!", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void clickHandlerStartTempo(View view) {
        if (!tempoOn) {
            Toast toast = Toast.makeText(getApplicationContext(), "Start Tempo!", Toast
                    .LENGTH_SHORT);
            toast.show();
            tempoOn = true;
            final Handler handler = new Handler();
            final int delay = 1000; //milliseconds

            handler.postDelayed(new Runnable() {
                public void run() {
                    if (tempoOn) {
                        playSound(0);
                        SystemClock.sleep(1000);
                        playSound(1);
                        SystemClock.sleep(1000);
                        playSound(1);
                        SystemClock.sleep(1000);
                        playSound(1);
                        SystemClock.sleep(1000);
                        playSound(1);
                        handler.postDelayed(this, delay);
                    }
                }
            }, delay);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Stop Tempo!", Toast
                    .LENGTH_SHORT);
            toast.show();
            tempoOn = false;
        }

    }
}
