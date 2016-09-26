package com.example.cfs.toth_habittracker;

import android.support.v7.app.AppCompatActivity;

import java.util.Date;

/**
 * Created by toth on 9/24/2016.
 */
public class Habit{
    private String title;
    private Boolean complete;
    public Date date;

    public Habit(String givenTitle){
        this.complete= false;

    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHabitTitle() {
        return title;
    }

    public boolean isActive(){
        //TODO: implement this funciton
        return true;
    }
    public boolean isComplete(){
        return complete;
    }
}
