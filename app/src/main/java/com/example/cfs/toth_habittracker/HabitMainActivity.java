package com.example.cfs.toth_habittracker;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;

public class HabitMainActivity extends AppCompatActivity {
    private static final String FILENAME = "file.sav";
    public final static String EXTRA_MESSAGE ="com.example.cfs.toth_habittracker.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Date date = new Date();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_main);
        SaveHabitInfo saveHabit = new SaveHabitInfo();
        saveHabit.saveInFile(FILENAME,date,HabitMainActivity.this);
        Button addButton = (Button) findViewById(R.id.addButton);

    }

    /** sendMessage from https://developer.android.com/training/basics/firstapp/starting-activity.html
     *
     * modified by toth
     */
    public void enterHabit(View view) {
        Intent intent = new Intent(this, AddHabitActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

}
