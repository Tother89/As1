package com.example.cfs.toth_habittracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ChangeDayActivity extends AppCompatActivity {
    public static final String CHANGE_MESSAGE = "com.example.cfs.toth_habittracker.CHANGE_DAY";
    private String day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_day);

//        Intent intent = getIntent();
//        day = intent.getStringExtra("today");
    }

    public void sumbitDayChange(){
        //http://stackoverflow.com/questions/9748070/radio-group-onclick-event-not-firing-how-do-i-tell-which-is-selected
        //Found out how to do this properly from stackoverflow
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.weekGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch(checkedId){
                    case R.id.first:
                        day = "Sunday";
                    case R.id.second:
                        day = "Monday";
                    case R.id.third:
                        day = "Tuesday";
                    case R.id.fourth:
                        day = "Wednesday";
                    case R.id.fifth:
                        day = "Thursday";
                    case R.id.sixth:
                        day = "Friday";
                    case R.id.seventh:
                        day = "Saturday";
                }
            }
        });

        Intent intent = getIntent();
        intent.putExtra(ChangeDayActivity.CHANGE_MESSAGE,day);
        setResult(RESULT_OK,intent);
        finish();
    }
}
