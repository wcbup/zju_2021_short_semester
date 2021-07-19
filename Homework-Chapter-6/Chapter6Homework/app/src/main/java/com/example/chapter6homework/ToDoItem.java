package com.example.chapter6homework;

import android.util.Log;

import java.util.Date;

public class ToDoItem {
    private static final String TAG = "ToDoItem";
    public Boolean isFinished;
    public String whatToDo;
    public String createTime;
    public int priority;
    public ToDoItem(Boolean isFinished, String whatToDo, String createTime, int priority){
        this.isFinished = isFinished;
        this.whatToDo = whatToDo;
        this.createTime = createTime;
        this.priority = priority;
    }
    public ToDoItem(String whatToDo, int priority){
        this.whatToDo = whatToDo;
        this.priority = priority;
        isFinished = false;
        createTime = (new Date()).toString();
        Log.d(TAG, "Time is "+createTime.toString());
    }
}
