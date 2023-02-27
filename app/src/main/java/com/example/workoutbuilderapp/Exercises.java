package com.example.workoutbuilderapp;

import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class Exercises {
    public ArrayList<View> exercises;
    public Exercises(){
        exercises = new ArrayList<View>();
    }
    public void addExercise(View exercise){
        exercises.add(exercise);
    }
    public void removeExercise(View exercise){
        exercises.remove(exercise);
    }
    public void clearList(){
        exercises.clear();
    }
    public String toString(){
        String exerciseString = "";
        for (int i = 0; i < exercises.size(); i++){
            exerciseString += ((TextView)exercises.get(i)).getText().toString() +"\n";
        }
        return exerciseString;
    }
}
