package com.example.cfs.toth_habittracker;

import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
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



public class HabitMainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    public final static String HABIT_MESSAGE = "com.example.cfs.toth_habittracker.MESSAGE";
    private static final String FILENAME = "file.sav";

    private final int ADD_ACTIVITY = 1;
    private final int COMPLETED_ACTIVITY = 2;
    private final int CHANGE_DAY_ACTIVITY = 3;

    private ListView oldHabitView;
    private EditText userInput;
    private RadioButton changeDay;
    private TextView currentDayText;
    private HabitData hData;
    private HabitData updateData = new HabitData();

    private ArrayAdapter<Habit> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_main);

        loadFromFile();

        changeDay = (RadioButton) findViewById(R.id.changeDay);

        if(hData == null){
            hData = new HabitData();
        }
        currentDayText = (TextView) findViewById(R.id.currentDay);
        oldHabitView = (ListView) findViewById(R.id.listView);
        userInput = (EditText) findViewById(R.id.editText);
        currentDayText.setText(hData.getToday());


//        for (Habit h : hData.getHabitList()) {
//            if (h.isonDay(hData.getToday())) {
//                updateData.addHabit(h);
//            }
//        }

        saveInFile();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        loadFromFile();
        updateData.getHabitList().clear();
        hData.getHabitList().clear();
        adapter.notifyDataSetChanged();
        saveInFile();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //adapter.clear();
        loadFromFile();
        userInput.setText("");
        currentDayText.setText(hData.getToday());
        for (Habit h : hData.getHabitList()) {
            if (h.isonDay(hData.getToday()) && !updateData.containsHabit(h) && h.getActivity() == true) {
                updateData.addHabit(h);

            } else if ((!h.isonDay(hData.getToday()) && hData.getHabitList().size()>0) || (h.getActivity() == false && hData.getHabitList().size()>0)) {
                updateData.removeHabit(h);
            }
        }
        adapter = new ArrayAdapter<Habit>(this, R.layout.active_list, updateData.getHabitList());

        oldHabitView.setAdapter(adapter);
        oldHabitView.getOnItemClickListener();
        oldHabitView.setOnItemClickListener(this);
        changeDay.setChecked(false);

        adapter.notifyDataSetChanged();
        saveInFile();
        }


    //handles results from other activities
    //create the new habit from addhabitactivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Nice implementation for a switch statement at this point
        // http://stackoverflow.com/questions/920306/sending-data-back-to-the-main-activity-in-android
        switch (requestCode) {
            case (ADD_ACTIVITY):
                if (resultCode == RESULT_OK) {
                    loadFromFile();
                    String d = data.getStringExtra(HabitMainActivity.HABIT_MESSAGE);
                    Habit habit = new Habit(d);
                    // found this snipet at http://stackoverflow.com/questions/5374546/passing-arraylist-through-intent
                    habit.setDaysOfWeek(data.getStringArrayListExtra("dayList"));
                    hData.addHabit(habit);
                    saveInFile();
                    break;
                }
                //when coming back from completed
                //compare with old hData until find one with same created time
                //then update that one that is found to new data aka adding a new completed or deleted
            case (COMPLETED_ACTIVITY):
                if (resultCode == RESULT_OK) {
                    break;
                }
            case CHANGE_DAY_ACTIVITY:
                if (resultCode == RESULT_OK) {
                    loadFromFile();
                    String updateDay = data.getStringExtra(ChangeDayActivity.CHANGE_MESSAGE);
                    hData.setToday(updateDay);
                    saveInFile();
                    break;

                }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //created time and list of completed
        //pass over as individual parts
        //or make class parcelable -- too hard do the first way each part
        //pass back the changes so it knows how to update, then update the whole object
        optionMenu(view,id);
    }


    /**
     * sendMessage from https://developer.android.com/training/basics/firstapp/starting-activity.html
     * <p>
     * modified by toth
     */
    public void enterHabit(View view) {

        Intent intent = new Intent(this, AddHabitActivity.class);
        String message = userInput.getText().toString();
        intent.putExtra(HABIT_MESSAGE, message);
        startActivityForResult(intent, ADD_ACTIVITY);
    }

    public void enterCompleted(View view) {
        Intent intent = new Intent(this, CompletedHabitsActivity.class);
        intent.putExtra(HabitMainActivity.HABIT_MESSAGE,hData.getToday());
        startActivityForResult(intent, COMPLETED_ACTIVITY);
    }

    public void getDay(View view) {
        Intent intent = new Intent(this, ChangeDayActivity.class);
        //intent.putExtra("today",today);
        startActivityForResult(intent, CHANGE_DAY_ACTIVITY);

    }

    public void optionMenu(View view, Long id) {
        Intent intent = new Intent(this, CompletedHabitsActivity2.class);
        intent.putExtra(HabitMainActivity.HABIT_MESSAGE,id);
        startActivityForResult(intent,COMPLETED_ACTIVITY);
    }

    /**
     * Code from 301 Lab https://github.com/joshua2ua/lonelyTwitter
     **/
    public void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            // Code from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            Type dataType = new TypeToken<HabitData>() {
            }.getType();
            hData = gson.fromJson(in, dataType);
            ;
        } catch (FileNotFoundException e) {
			/* Create a brand new list if we can't find the file. */
            hData = new HabitData();
        }
    }


    /**
     * Code from 301 Lab https://github.com/joshua2ua/lonelyTwitter
     **/
    public void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, 0);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(hData, out);
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



}


