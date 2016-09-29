package com.example.cfs.toth_habittracker;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by tothd on 9/27/2016.
 */
public class SaveHabitInfo {
    private static final String FILENAME = "file.sav";
    /**Code from 301 Lab lonelyTwitter**/
    private String[] loadFromFile() {
        ArrayList<String> habits = new ArrayList<String>();
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            String line = in.readLine();
            while (line != null) {
                habits.add(line);
                line = in.readLine();
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return habits.toArray(new String[habits.size()]);
    }
    /**Code from 301 Lab lonelyTwitter**/
    private void saveInFile(String text, Date date) {
        try {
            //FileOutputStream fos = openFileOutput(FILENAME);
            fos.write(new String(date.toString() + " | " + text)
                    .getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
