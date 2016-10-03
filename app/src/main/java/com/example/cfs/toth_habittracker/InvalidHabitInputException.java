package com.example.cfs.toth_habittracker;

/**
 * Created by tothd on 9/27/2016.
 */
public class InvalidHabitInputException extends Exception {
    public InvalidHabitInputException(){}

    public InvalidHabitInputException(String message){
        super(message);
    }
}
