package com.example.cfs.toth_habittracker;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class AddHabitActivity extends AppCompatActivity {
    private NewHabit newHabit = new NewHabit("New task.");
    private static final String FILENAME = "file.sav";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habit);

        Intent intent = getIntent();
        String message = intent.getStringExtra(HabitMainActivity.HABIT_TITLE);

        try {
            newHabit.editTitle(message);
        } catch (HabitTooLongException e) {
            e.printStackTrace();
        }

        TextView textView = new TextView(this);
        textView.setTextSize(25);
        textView.setTextColor(Color.BLUE);
        textView.setText(message);

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_add_habit);

        layout.addView(textView);
    }

    protected void setWeekDaysActive(View view) {
        Date date = new Date();
        ArrayList<String> dayList = new ArrayList();
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        if(findViewById(R.id.monButton).isActivated()){
            dayList.add("Monday");
        }
        SaveHabitInfo saveHabit = new SaveHabitInfo();
        saveHabit.saveInFile(FILENAME,date,AddHabitActivity.this);
    }


}
