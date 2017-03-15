package com.example.kate.projectbreakitdown;

import android.app.Activity;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Kate on 09/03/2017.
 */

public class Task {

    private String task_title;
    private String task_description;
    private String task_duedate;
    private String task_priority;
    private String task_length;
    private ArrayList<Action> actions = new ArrayList<>();


    //Constructor

    public Task(String task_title, String task_description, String task_duedate, String task_priority, String task_length){

        this.task_title = task_title;
        this.task_description = task_description;
        this.task_duedate = task_duedate;
        this.task_priority = task_priority;
        this.task_length = task_length;
    }


    //Getters of task class
    public String getTask_title(){return task_title;}
    public String getTask_description(){return task_description;}
    public String getTask_duedate(){return task_duedate;}
    public String getTask_priority(){return task_priority;}
    public String getTask_length(){return task_length;}

    //Setters of task class
    public void setTask_title(){
        this.task_title = task_title;
    }

    public void setTask_description(){
        this.task_description = task_description;
    }

    public void setTask_duedate(){
        this.task_duedate = task_duedate;
    }

    public void setTask_priority(){
        this.task_priority = task_priority;
    }

    public void setTask_length(){
        this.task_length = task_length;
    }

    //Return list of children
    public ArrayList<Action> getActions(){
        return actions;
    }

}
