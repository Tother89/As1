package com.example.cfs.toth_habittracker;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;

public class HabitMainActivity extends AppCompatActivity {
    public final static String HABIT_TITLE ="com.example.cfs.toth_habittracker.MESSAGE";
    private ListView oldHabitView;
    private ArrayList<Habit> habitList = new ArrayList<>();
    private ArrayAdapter<Habit> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_main);
    }

    @Override
    protected void onStart(){
        super.onStart();
        adapter = new ArrayAdapter<Habit>(this,
                R.layout.activity_habit_main, habitList);
        oldHabitView.setAdapter(adapter);
    }

    /** sendMessage from https://developer.android.com/training/basics/firstapp/starting-activity.html
     *
     * modified by toth
     */
    public void enterHabit(View view) {
        Intent intent = new Intent(this, AddHabitActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(HABIT_TITLE, message);
        startActivity(intent);
    }
    public void enterCompleted(){
        Intent intent = new Intent(this, CompletedHabitsActivity.class);
        startActivity(intent);
    }

}
