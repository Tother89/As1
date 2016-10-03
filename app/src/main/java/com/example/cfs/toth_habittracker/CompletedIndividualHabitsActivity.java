package com.example.cfs.toth_habittracker;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
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
import java.util.Date;

public class CompletedIndividualHabitsActivity extends AppCompatActivity {
    private static final String FILENAME = "file.sav";
    private HabitData habitData;
    private HabitData completedData = new HabitData();
    private ListView habitView;
    private TextView message;
    private ArrayAdapter<Habit> adapter;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_habit);


        Intent intent = getIntent();

        loadFromFile();
        if (habitData == null) {
            habitData = new HabitData();
        }


        Date date = (Date) intent.getSerializableExtra("dateIn");
        int myInt =  habitData.getHabit(date).getCompletions();
        habitView = (ListView) findViewById(R.id.completeListView);
        message = (TextView) findViewById(R.id.currentHabit);
        message.setText("Current completions: " + myInt);


        saveInFile();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadFromFile();

        completedData.getHabitList().clear();

        //Iterate through and find all the completed ones
        for (Habit h : habitData.getHabitList()) {
            if (!h.getActivity()) {
                completedData.addHabit(h);
            }
        }
        adapter = new ArrayAdapter<Habit>(this, R.layout.active_list, completedData.getHabitList());
        adapter.notifyDataSetChanged();
        saveInFile();
    }

    public void removeHabit(View view) {
        super.onResume();
        loadFromFile();
        Intent intent = getIntent();
        Date date = (Date) intent.getSerializableExtra("dateIn");
        habitData.getHabit(date).setActive(false);
        saveInFile();
        setResult(RESULT_OK);
        finish();
    }

    public void completeHabit(View view) {
        loadFromFile();
        Intent intent = getIntent();
        Date date = (Date) intent.getSerializableExtra("dateIn");
        habitData.getHabit(date).increment();
        intent.putExtra("habitDate", date);
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

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("CompletedIndividualHabits Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}

