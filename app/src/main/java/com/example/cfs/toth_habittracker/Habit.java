package com.example.cfs.toth_habittracker;

import android.support.v7.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
        setDate(date);
    }
    //Set the exact date and time
    public void setDate(Date date) {
        this.date = new Date();
    }
    //Get the current date and time when the Habit was created
    public String getDate(){
        this.date = new Date();
        return dateFormat.format(this.date);}

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHabitTitle() {
        return title;
    }

    public abstract boolean isComplete();

}
