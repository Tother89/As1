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
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class HabitMainActivity extends AppCompatActivity {
    public final static String HABIT_TITLE ="com.example.cfs.toth_habittracker.MESSAGE";
    private static final String FILENAME = "file.sav";
    private ListView oldHabitView;
    private TextView currentDayText;
    private EditText userInput;
    private ArrayList<Habit> habitList;
    private ArrayAdapter<Habit> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_main);

        Calendar c = Calendar.getInstance();
        String today = findWeekDay(c.get(Calendar.DAY_OF_WEEK));
        currentDayText = (TextView) findViewById(R.id.currentDay);
        currentDayText.setText(today);
        String habitTitle = userInput.getText().toString();
        Habit habit = new NewHabit(habitTitle);
        habitList.add(habit);
        oldHabitView = (ListView) findViewById(R.id.listView);
        userInput = (EditText) findViewById(R.id.editText);
        saveInFile();
    }

    @Override
    protected void onStart(){
        super.onStart();
        loadFromFile();


    }

    @Override
    protected  void onResume(){
        super.onResume();
        habitList = new ArrayList<>();
        loadFromFile();
        userInput.setText("");
        adapter = new ArrayAdapter<Habit>(this,R.layout.active_list, habitList);
        oldHabitView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    /** sendMessage from https://developer.android.com/training/basics/firstapp/starting-activity.html
     *
     * modified by toth
     */
    public void enterHabit(View view) {
        Intent intent = new Intent(this, AddHabitActivity.class);
        String message = userInput.getText().toString();
        intent.putExtra(HABIT_TITLE,message);
        startActivity(intent);
    }
    public void enterCompleted(){
        Intent intent = new Intent(this, CompletedHabitsActivity.class);
        startActivity(intent);
    }

    /**Code from 301 Lab https://github.com/joshua2ua/lonelyTwitter**/
    public void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            // Code from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            Type listType = new TypeToken<Habit>(){}.getType();
            habitList = gson.fromJson(in,listType);

        } catch (FileNotFoundException e) {
			/* Create a brand new list if we can't find the file. */
            habitList = new ArrayList<>();
        }
    }


    /**Code from 301 Lab https://github.com/joshua2ua/lonelyTwitter**/
    public void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,0);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(habitList, out);
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
			/* Rethrow. */
            throw new RuntimeException(e);
        } catch (IOException e) {
			/* Rethrow. */
            throw new RuntimeException(e);
        }
    }
    //Useful method that determines today's week day and returns it as a string
    //Idea cited from http://stackoverflow.com/questions/18882420/want-to-get-day-of-a-week-as-a-string-but-giving-wrong-day
    public String findWeekDay(int i) {
        String day = "";
        switch (i) {
            case 1:
                day = "Sunday";
                break;
            case 2:
                day = "Monday";
                break;
            case 3:
                day = "Tuesday";
                break;
            case 4:
                day = "Wednesday";
                break;
            case 5:
                day = "Thursday";
                break;
            case 6:
                day = "Friday";
                break;
            case 7:
                day = "Saturday";
                break;
        }
        return day;
    }

}
