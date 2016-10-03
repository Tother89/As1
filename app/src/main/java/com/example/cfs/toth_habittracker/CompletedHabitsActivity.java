package com.example.cfs.toth_habittracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
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

public class CompletedHabitsActivity extends AppCompatActivity {
    private static final String FILENAME = "file.sav";
    private HabitData habitData;
    private HabitData completedData = new HabitData();
    private ListView habitView;
    private TextView message;
    private ArrayAdapter<Habit> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_habits);


        Intent intent = getIntent();
        String newMessage = intent.getStringExtra(HabitMainActivity.HABIT_MESSAGE);
        loadFromFile();
        if(habitData==null){
            habitData = new HabitData();
        }

        habitView = (ListView) findViewById(R.id.completeListView);
        message = (TextView) findViewById(R.id.currentHabit) ;
        message.setText(newMessage+"'s completed habits");

        saveInFile();
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Iterate through and find all the completed ones
        for(Habit h: habitData.getHabitList()) {
            if (h.getCompletions()>0) {
                completedData.addHabit(h);
            }
        }
        adapter = new ArrayAdapter<Habit>(this,R.layout.active_list,completedData.getHabitList());
        habitView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        saveInFile();

    }

    public void removeHabits(View view){


        //
        for(Habit h: completedData.getHabitList()) {
            habitData.getHabitList().remove(h);
        }


        saveInFile();
        setResult(RESULT_OK);
        finish();
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
            habitData = gson.fromJson(in, dataType);
            ;
        } catch (FileNotFoundException e) {
			/* Create a brand new list if we can't find the file. */
            habitData = new HabitData();
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
            gson.toJson(habitData, out);
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
