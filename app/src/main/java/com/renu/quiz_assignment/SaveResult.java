package com.renu.quiz_assignment;

import android.app.Activity;
import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class SaveResult
{
    static String filename = "result.txt";

    public static void saveTask(MainActivity context, int correctAnswer)
    {
        FileOutputStream fileOutputStream = null;

        try
        {
            fileOutputStream = context.openFileOutput(filename, Context.MODE_APPEND);// open file and continue writing
            String storageString = "";
            // I have to write byte array only
            // I have to convert task to string then to byte array
            fileOutputStream.write((correctAnswer + "$").getBytes());
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();// print all previous error
        }
        finally
        {
            // this will run if we have exception or not
            try
            {
                fileOutputStream.close();
            }


            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        //file outputstream for writing to the file
    }

    public static void resetAllTask(Activity context)
    {
        FileOutputStream fileOutputStream = null;
        try
        {
            fileOutputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);// earas data
            fileOutputStream.write("".getBytes());
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();// print all previous error
        }
        finally
        {
            // this will run if we have exception or not
            try
            {
                fileOutputStream.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    //shopping - Nov 25, 2021$Reading - Nov 26, 2021$Studing - Nov 30, 2021
    private static ArrayList<Integer> fromStringToListOfTODo(String stringFromTheFile){

        ArrayList<Integer> list = new ArrayList<>(0);
        int index = 0;
        for (int i = 0 ; i < stringFromTheFile.toCharArray().length ; i++){
            if (stringFromTheFile.toCharArray()[i] == '$'){
                String fullTask = stringFromTheFile.substring(index, i  );
               list.add(Integer.parseInt(fullTask));
                index = i + 1;
            }
        }

        return list;
    }
    //file outputstream for writing to the file
    public static ArrayList<Integer> getAllTasks(Activity activity){
        FileInputStream fileInputStream = null;
        ArrayList<Integer> list = new ArrayList<>(0);
        StringBuffer stringBuffer = new StringBuffer();
        int read = 0;
        try {

            // shopping - Nov 25, 2021$Reading - Nov 26, 2021$Studing - Nov 30, 2021
            fileInputStream = activity.openFileInput(filename);
            while ( (read = fileInputStream.read()) != -1){//
                stringBuffer.append((char)read);
            }
            // write a function to conver this string buffer into list of tasks
            list =  fromStringToListOfTODo(stringBuffer.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }


}
