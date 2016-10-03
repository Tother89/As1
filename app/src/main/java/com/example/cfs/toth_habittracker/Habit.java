package com.example.cfs.toth_habittracker;

import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by toth on 9/24/2016.
 */
public abstract class Habit{
    private String title;
    private ArrayList<String> daysOfWeek;
    public Date date;
    public boolean isActive;

    public Habit(String givenTitle){
        this.title = givenTitle;
        this.date = new Date();
    }

    public void editTitle(String title) throws HabitTooLongException{
        if(title.length() > 30){
            throw new HabitTooLongException();
        }
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public boolean isonDay(String day){
        return daysOfWeek.contains(day);
    }
//    public abstract void setWeekDays(ArrayList list);


}
