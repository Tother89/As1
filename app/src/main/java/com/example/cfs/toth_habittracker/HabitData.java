package com.example.cfs.toth_habittracker;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by toth on 10/2/2016.
 */

public class HabitData {
    private ArrayList<Habit> habitList;

    public HabitData(){
        habitList = new ArrayList<>();
    }

    public ArrayList<Habit> getHabitList(){
        return habitList;
    }

    public void setHabitList(ArrayList<Habit> habitList) {
        this.habitList = habitList;
    }

    public void addHabit(Habit habit){
        habitList.add(habit);
    }
}
