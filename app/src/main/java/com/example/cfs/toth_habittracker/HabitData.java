package com.example.cfs.toth_habittracker;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by toth on 10/2/2016.
 */

public class HabitData {
    private ArrayList<Habit> habitList;
    private String today;
    public HabitData(){
        Calendar c = Calendar.getInstance();
        this.today = findWeekDay(c.get(Calendar.DAY_OF_WEEK));
        habitList = new ArrayList<>();
    }

    public void setToday(String today) {
        this.today = today;
    }

    public String getToday() { return this.today;
    }

    public ArrayList<Habit> getHabitList(){
        return this.habitList;
    }

    public void setHabitList(ArrayList<Habit> habitList) {
        this.habitList = habitList;
    }

    public void addHabit(Habit habit){
        habitList.add(habit);
    }

    //Useful method that determines today's week day and returns it as a string
    //Idea cited from http://stackoverflow.com/questions/18882420/want-to-get-day-of-a-week-as-a-string-but-giving-wrong-day
    public String findWeekDay(int i) {
        String day = "";
        switch (i) {
            case 1:
                day = "Sunday";
                break;
            case 2:
                day = "Monday";
                break;
            case 3:
                day = "Tuesday";
                break;
            case 4:
                day = "Wednesday";
                break;
            case 5:
                day = "Thursday";
                break;
            case 6:
                day = "Friday";
                break;
            case 7:
                day = "Saturday";
                break;
        }
        return day;
    }

    public void removeHabit(Habit h) {
        this.habitList.remove(h);
    }

    public boolean containsHabit(Habit h) {
        for (Habit hab: this.getHabitList())
        {
            if(hab.equals(h)){
            return true;
            }

        }
        return false;

    }
}
