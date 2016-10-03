package com.example.cfs.toth_habittracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

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

import static android.provider.Telephony.Mms.Part.FILENAME;

public class CompletedHabitsActivity extends AppCompatActivity {
    private HabitData hData;
    private ListView oldHabitView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_habits);

        oldHabitView = (ListView)  findViewById(R.id.listView2);

        Intent intent = getIntent();

    }

    /**Code from 301 Lab https://github.com/joshua2ua/lonelyTwitter**/
    public void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            // Code from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            Type listType = new TypeToken<ArrayList>(){}.getType();
            hData = gson.fromJson(in,listType);

        } catch (FileNotFoundException e) {
			/* Create a brand new list if we can't find the file. */
            hData = new HabitData();
        }
    }


    /**Code from 301 Lab https://github.com/joshua2ua/lonelyTwitter**/
    public void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,0);
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
