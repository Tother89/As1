package com.example.cfs.toth_habittracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ChangeDayActivity extends AppCompatActivity {
    public static final String CHANGE_MESSAGE = "com.example.cfs.toth_habittracker.CHANGE_DAY";
    private String day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_day);

        //Intent intent = getIntent();
        //day = intent.getStringExtra("today");
    }

    public void submitDayChange(View view){
        int checkedId;
        switch(view.getId()){
                    case (R.id.first):
                        day = "Sunday";
                        break;
                    case R.id.second:
                        day = "Monday";
                        break;
                    case R.id.third:
                        day = "Tuesday";
                        break;
                    case R.id.fourth:
                        day = "Wednesday";
                        break;
                    case R.id.fifth:
                        day = "Thursday";
                        break;
                    case R.id.sixth:
                        day = "Friday";
                        break;
                    case R.id.seventh:
                        day = "Saturday";
                        break;
                }



        Intent intent = getIntent();
        intent.putExtra(ChangeDayActivity.CHANGE_MESSAGE,day);
        setResult(RESULT_OK,intent);
        finish();
    }
}
