package com.example.chapter6homework;

import java.util.Comparator;
import java.util.Date;

public class MyComparator implements Comparator<ToDoItem> {
    @Override
    public int compare(ToDoItem o1, ToDoItem o2) {
        if(o1.isFinished){
            if(o2.isFinished){
                if(o1.priority < o2.priority){
                    return 1;
                }
                Date date1 = new Date(o1.createTime);
                Date date2 = new Date(o2.createTime);
                return date1.getTime() < date2.getTime() ? 1 : -1;
            }else {
                return 1;
            }
        }else {
            if(o2.isFinished){
                return -1;
            }else {
                if(o1.priority < o2.priority){
                    return 1;
                }
                Date date1 = new Date(o1.createTime);
                Date date2 = new Date(o2.createTime);
                return date1.getTime() < date2.getTime() ? 1 : -1;
            }
        }
    }
}
