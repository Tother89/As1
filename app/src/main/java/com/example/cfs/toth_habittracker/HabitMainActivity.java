package com.example.cfs.toth_habittracker;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class HabitMainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    public final static String HABIT_MESSAGE ="com.example.cfs.toth_habittracker.MESSAGE";
    private static final String FILENAME = "file.sav";
    private ListView oldHabitView;
    private TextView currentDayText;
    private EditText userInput;
    private ArrayList<Habit> habitList = new ArrayList<>();
    private ArrayList<Habit> dailyHabitList = new ArrayList<>();
    private ArrayAdapter<Habit> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_main);

        Calendar c = Calendar.getInstance();
        String today = findWeekDay(c.get(Calendar.DAY_OF_WEEK));
        currentDayText = (TextView) findViewById(R.id.currentDay);
        currentDayText.setText(today);

        loadFromFile();

//        Habit habit = new Habit("Default");
//        habitList.add(habit);
        oldHabitView = (ListView) findViewById(R.id.listView);

        for(Habit h: habitList){
            if(h.isonDay(today)){
                dailyHabitList.add(h);
            }
        }

        userInput = (EditText) findViewById(R.id.editText);
        saveInFile();
    }

    @Override
    protected  void onResume(){
        super.onResume();
        loadFromFile();
        userInput.setText("");
        adapter = new ArrayAdapter<Habit>(this,R.layout.active_list, dailyHabitList);
        oldHabitView.setAdapter(adapter);

        oldHabitView.getOnItemClickListener();
        oldHabitView.setOnItemClickListener(this);
        saveInFile();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode){
            case(AddHabitActivity.RESULT_OK):
                if(resultCode == RESULT_OK){
                    String d = data.getDataString();
                    Habit habit = new Habit(d);
                    habitList.add(habit);
                }
        }

        //handles results from other activities
        //create the new habit from addhabitactivity

        //when coming back from completed
        //compare with old habitlist until find one with same created time
        //then update that one that is found to new data aka adding a new completed or deleted
    }

    /** sendMessage from https://developer.android.com/training/basics/firstapp/starting-activity.html
     *
     * modified by toth
     */
    public void enterHabit(View view) {
        Intent intent = new Intent(this, AddHabitActivity.class);
        String message = userInput.getText().toString();
        intent.putExtra(HABIT_MESSAGE,message);
        startActivityForResult(intent,HabitMainActivity.RESULT_OK);
    }

    public void enterCompleted(View view){
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
            Type listType = new TypeToken<ArrayList>(){}.getType();
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


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //created time and list of completed
        //pass over as individual parts
        //or make class parcelable -- too hard do the first way each part
        //pass back the changes so it knows how to update, then update the whole object

    }
}
