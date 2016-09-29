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
public abstract class Habit implements HabitInterface{
    private String title;
    private DateFormat dateFormat = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");
    public Date date;

    public Habit(String givenTitle){
        this.title = givenTitle;
        this.date = new Date();
    }

    public void editTitle(String title) throws HabitTooLongException{
        if(title.length() > 100){
            throw new HabitTooLongException();
        }
        this.title = title;
    }

    public String getHabitTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public abstract boolean isComplete();

    //Method that returns
    public abstract ArrayList<String> weekDaysActive();


}
