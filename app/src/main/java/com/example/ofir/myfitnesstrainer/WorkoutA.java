package com.example.ofir.myfitnesstrainer;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

public class WorkoutA extends AppCompatActivity {

    private static final String TAG = "WorkoutA";
    private static final String MY_PREFS = "my_prefs";

    private static final String WEIGHT1 = "WEIGHT1";
    private static final String WEIGHT2 = "WEIGHT2";
    private static final String WEIGHT3 = "WEIGHT3";
    private static final String WEIGHT4 = "WEIGHT4";

    private static final String DEVICE1 = "DEVICE1";
    private static final String DEVICE2 = "DEVICE2";
    private static final String DEVICE3 = "DEVICE3";
    private static final String DEVICE4 = "DEVICE4";

    EditText etWieght1, etWieght2, etWieght3, etWieght4;

    MaterialBetterSpinner betterSpinner1, betterSpinner2, betterSpinner3, betterSpinner4;

    String[] devicesNameArr = {"פולי עליון", "משיכה לחזה", "גב תחתון", "חזה (דחיקה)", "חזה (פרפר" +
            " ישיבה)", "חזה שיפוע חיובי", "חזה שיפוע שלילי", "יד אחורית (חוט)", "יד אחורית " +
            "(משקולת)", "כתף קדמית (מכשיר)"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_a);

        etWieght1 = (EditText) findViewById(R.id.et_weight_1);
        etWieght2 = (EditText) findViewById(R.id.et_weight_2);
        etWieght3 = (EditText) findViewById(R.id.et_weight_3);
        etWieght4 = (EditText) findViewById(R.id.et_weight_4);

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

        betterSpinner1.setAdapter(arrayAdapter);
        betterSpinner2.setAdapter(arrayAdapter);
        betterSpinner3.setAdapter(arrayAdapter);
        betterSpinner4.setAdapter(arrayAdapter);

        SharedPreferences pref = getApplicationContext().getSharedPreferences(MY_PREFS, MODE_PRIVATE);

        etWieght1.setText(pref.getString(WEIGHT1, ""));
        etWieght2.setText(pref.getString(WEIGHT2, ""));
        etWieght3.setText(pref.getString(WEIGHT3, ""));
        etWieght4.setText(pref.getString(WEIGHT4, ""));

        betterSpinner1.setText(pref.getString(DEVICE1, ""));
        betterSpinner2.setText(pref.getString(DEVICE2, ""));
        betterSpinner3.setText(pref.getString(DEVICE3, ""));
        betterSpinner4.setText(pref.getString(DEVICE4, ""));
    }

    public void clickHandlerSaveData(View view) {
        String wieght1Str = etWieght1.getText().toString();
        String wieght2Str = etWieght2.getText().toString();
        String wieght3Str = etWieght3.getText().toString();
        String wieght4Str = etWieght4.getText().toString();

        String device1Str = betterSpinner1.getText().toString();
        String device2Str = betterSpinner2.getText().toString();
        String device3Str = betterSpinner3.getText().toString();
        String device4Str = betterSpinner4.getText().toString();

        SharedPreferences pref = getApplicationContext().getSharedPreferences(MY_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putString(WEIGHT1, wieght1Str);
        editor.putString(WEIGHT2, wieght2Str);
        editor.putString(WEIGHT3, wieght3Str);
        editor.putString(WEIGHT4, wieght4Str);

        editor.putString(DEVICE1, device1Str);
        editor.putString(DEVICE2, device2Str);
        editor.putString(DEVICE3, device3Str);
        editor.putString(DEVICE4, device4Str);

        editor.apply();

        Toast toast = Toast.makeText(getApplicationContext(), "Saved!", Toast.LENGTH_SHORT);
        toast.show();
    }
}
