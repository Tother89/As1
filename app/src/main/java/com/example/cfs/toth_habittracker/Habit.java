package com.example.cfs.toth_habittracker;

import java.util.Date;

/**
 * Created by toth on 9/24/2016.
 */
public class Habit {
    private String title;
    private Boolean complete;
    public Date date;

    public Habit(Title givenTitle){
        this.complete= false;

    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
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
