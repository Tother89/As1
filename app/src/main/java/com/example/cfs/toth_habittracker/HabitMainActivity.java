package com.example.cfs.toth_habittracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HabitMainActivity extends AppCompatActivity {
    private static final String FILENAME = "file.sav";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_main);

        Button addButton = (Button) findViewById(R.id.addbutton);

        addButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(this,Habit.class);
                startActivity(intent);


            }
        });
    }
}
