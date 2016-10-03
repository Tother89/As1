package com.example.cfs.toth_habittracker;

import android.annotation.TargetApi;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by toth on 9/24/2016.
 */

public class Habit{
    private String title;
    private ArrayList<String> daysOfWeek;
    private int completions;
    private Date date;

    private boolean isActive;


    public Habit(String givenTitle){
        this.title = givenTitle;
        this.date = new Date();
        this.isActive = true;
        this.completions = 0;

    }
    public void increment(){
        this.completions++;
    }
    public int getCompletions(){
        return completions;
    }
    public void editTitle(String title) throws InvalidHabitInputException {
        if(title.length() > 30 && title.length() <= 0){
            throw new InvalidHabitInputException("Input was too long or null.");
        }
        this.title = title;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public boolean getActivity() {
        return this.isActive;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public void setTitle(String t){ this.title=t;}

    public void setDate(){ this.date = new Date();}

    public void setDaysOfWeek(ArrayList<String> arr){
        this.daysOfWeek = arr;
    }

    public ArrayList<String> getDaysOfWeek() {
        return daysOfWeek;
    }

    //Returns which habits are active on given day
    public boolean isonDay(String day){
        return daysOfWeek.contains(day);
    }


    @Override
    public String toString() {
//          Attempted to implement this but was unsuccessful due to a runtime error
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        String str = format.format(date);
        return title + " created on " + date.toString();
    }


}
