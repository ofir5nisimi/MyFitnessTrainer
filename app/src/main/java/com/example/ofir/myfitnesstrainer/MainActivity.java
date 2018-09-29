package com.example.ofir.myfitnesstrainer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Button bWorkoutA = (Button) findViewById(R.id.b_workout_a);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickHandlerWorkoutA(View view) {
        startActivity(new Intent(MainActivity.this, WorkoutA.class));
    }

}
