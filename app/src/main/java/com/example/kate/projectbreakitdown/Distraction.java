package com.example.kate.projectbreakitdown;

/**
 * Created by Kate on 09/03/2017.
 */

public class Distraction {


    //Distraction properties
    private String distraction_name;
    private String distraction_priority;
    private String distraction_occurance;

    //Constructor of Distraction type
    public Distraction (String distraction_name, String distraction_priority, String distraction_occurance){

        this.distraction_name = distraction_name;
        this.distraction_priority = distraction_priority;
        this.distraction_occurance = distraction_occurance;
    }


    //Getter methods

    public String getDistraction_name(){return distraction_name;}
    public String getDistraction_priority(){return distraction_priority;}
    public String getDistraction_occurance(){return distraction_occurance;}

    public void setDistraction_name(String distraction_name){
        this.distraction_name = distraction_name;
    }

    public void setDistraction_priority(String distraction_priority){
        this.distraction_priority = distraction_priority;
    }

    public void setDistraction_occurance(String distraction_occurance) {
        this.distraction_occurance = distraction_occurance;
    }
}
