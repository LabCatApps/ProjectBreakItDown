package com.example.kate.projectbreakitdown;

/**
 * Created by Kate on 13/03/2017.
 */

public class Action {

    private String name = "";
    private String description = "";
    private String priority;
    private String length;

    public Action(String name, String description, String priority, String length){
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.length = length;
    }


    //Getters for Action
    public String getName() {
        return name;
    }

    public String getDescription(){
        return description;
    }

    public String getPriority(){
        return priority;
    }

    public String getLength(){
        return length;
    }

    //Setters for Action
    public void setName() {
        this.name = name;
    }

    public void setDescription(){
        this.description = description;
    }

    public void setPriority(){
        this.priority = priority;
    }

    public void setLength(){
        this.length = length;
    }

}
