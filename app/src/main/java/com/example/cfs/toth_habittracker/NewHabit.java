package com.example.cfs.toth_habittracker;

/**
 * Created by tothd on 9/26/2016.
 */
public class NewHabit extends Habit {

    public NewHabit(String givenTitle) {
        super(givenTitle);
    }

    @Override
    public boolean isComplete() {
        return false;
    }
}
