package com.example.cfs.toth_habittracker;

import java.util.ArrayList;

/**
 * Created by tothd on 9/26/2016.
 */
public class CompletedHabit extends Habit {

    public CompletedHabit(String givenTitle) {
        super(givenTitle);
    }

    @Override
    public boolean isComplete() {
        return true;
    }

    @Override
    public void setWeekDays(ArrayList list) {

    }
}
