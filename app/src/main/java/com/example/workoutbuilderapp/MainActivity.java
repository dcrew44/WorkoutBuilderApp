package com.example.workoutbuilderapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private String[] exerciseList;
    private int arrayLength;
    private Exercises exercises = new Exercises();
    private LayoutInflater inflater;
    private LinearLayout myLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addButton = (Button) findViewById(R.id.addButton);
        Button clearButton = (Button) findViewById(R.id.clearButton);
        Button mailButton = (Button) findViewById(R.id.mailButton);

        myLayout = (LinearLayout) findViewById(R.id.exerciseLayout);
        exerciseList = getResources().getStringArray(R.array.exercise_array);
        arrayLength = exerciseList.length;

        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        addButton.setOnClickListener(v -> {
            addExercise();
            addViews();
        });

        clearButton.setOnClickListener(v -> clearExercises());

        mailButton.setOnClickListener(v -> sendMail());
    }

    private void addViews() {
        myLayout.removeAllViews();
        for (View exercise : exercises.exercises) {
            //Add the exercise to the linear layout with the id exerciseLayout in activity_main
            myLayout.addView(exercise, 0);
        }
    }

    private void addExercise() {
        String exercise = exerciseList[(int) (Math.random() * arrayLength)];
        //Create a new View for single_exercise_layout, but the content of the textview is the exercise
        TextView exerciseView = (TextView) inflater.inflate(R.layout.single_exercise_layout, null);
        exerciseView.setText(exercise);
        exercises.addExercise(exerciseView);

    }

    private void clearExercises() {
        exercises.clearList();
        myLayout.removeAllViews();
    }

    private void sendMail() {
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{"trish_cornez@redlands.edu" });
        email.putExtra(Intent.EXTRA_SUBJECT, "Random Workout");
        email.putExtra(Intent.EXTRA_TEXT, exercises.toString());

        //need this to prompts email client only
        email.setType("message/rfc822");

        startActivity(Intent.createChooser(email, "Choose an Email client :"));


    }


}