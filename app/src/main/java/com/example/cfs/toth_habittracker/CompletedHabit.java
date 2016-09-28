package com.example.cfs.toth_habittracker;

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
}
