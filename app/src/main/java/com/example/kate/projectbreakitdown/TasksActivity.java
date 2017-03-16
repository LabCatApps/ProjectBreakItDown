package com.example.kate.projectbreakitdown;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;



/**
 * Created by Kate on 06/03/2017.
 */

public class TasksActivity extends Activity {

    Context context;
    ImageButton menu_lilac;
    Button add_task;
    Button add_action;

    private ArrayList<Task> tasks = new ArrayList<>();

    private BaseExpandableListAdapter taskListAdapter;
    private ExpandableListView task_listview;

    //On Create function initialises the layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasks_activity_layout);
        this.context = this;

        //Intent to Tasks Activity
        final Intent toHome = new Intent(TasksActivity.this, MainActivity.class);

        //Intent to Home Activity
        final Intent toDist = new Intent(TasksActivity.this, DistractionsActivity.class);

        //Intent to Settings Activity
        final Intent toSett = new Intent(TasksActivity.this, SettingsActivity.class);

        //Initialise instance of menu button
        menu_lilac = (ImageButton) findViewById(R.id.menu_lilac);

        //Addition of demo data
        Task task1 = new Task("Cryptography", "Solve coursework exercises", "31 March 2017", "10", "120");
        task1.getActions().add(new Action("ElGamal", "Decrypt this cipher", "6", "60"));
        task1.getActions().add(new Action("XOR", "Decrypt this cipher", "8","60"));

        Task task2 = new Task("RASS", "Write a report", "31 March 2017", "10", "240");
        task2.getActions().add(new Action("Read lectures", "important to understand material", "7", "60"));
        task2.getActions().add(new Action("Plan report", "a good plan is key", "10", "60"));

        tasks.add(task1);
        tasks.add(task2);

        //Get reference for expendable list view
        task_listview = (ExpandableListView) findViewById(R.id.list_of_tasks);

        //create the adapter and pass parent data array
        taskListAdapter = new TaskListAdapter(TasksActivity.this, tasks);
        //Attach the adapter
        task_listview.setAdapter(taskListAdapter);

        //Get reference for Add task button
        add_task = (Button) findViewById(R.id.add_task);

        //Add Task onClickListener ( Here the user will be able to enter a NAME, DESCRIPTION, PRIORITY, LENGTH and
        //DUE DATE through a Date Picker
        add_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater dialog = LayoutInflater.from(TasksActivity.this);
                final View add_task_dialog_view = dialog.inflate(R.layout.add_task_dialog, null);

                final EditText input_name = (EditText) add_task_dialog_view.findViewById(R.id.task_name_textbox);
                final EditText input_priority = (EditText) add_task_dialog_view.findViewById(R.id.task_priority_textbox);
                final EditText input_description = (EditText) add_task_dialog_view.findViewById(R.id.task_description_textbox);
                final DatePicker input_duedate = (DatePicker) add_task_dialog_view.findViewById(R.id.datePicker);
                final EditText input_length = (EditText) add_task_dialog_view.findViewById(R.id.task_length_textbox);

                //INPUT TYPES
                input_name.setInputType(InputType.TYPE_CLASS_TEXT);
                input_description.setInputType(InputType.TYPE_CLASS_TEXT);
                input_priority.setInputType(InputType.TYPE_CLASS_NUMBER);
                input_length.setInputType(InputType.TYPE_CLASS_NUMBER);


                //Create and initialise an AlertBuilder to open a pop-up where
                //the user will be able to give some info about the new distraction
                final AlertDialog.Builder builder = new AlertDialog.Builder(TasksActivity.this);
                builder.setTitle("Add Task").setView(add_task_dialog_view);

                //Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        final String new_name = input_name.getText().toString();
                        final String new_description = input_description.getText().toString();
                        final String new_priority = input_priority.getText().toString();
                        final String new_length = input_length.getText().toString();
                        //Take input from Date picker to form the final String date
                        String day = "" +input_duedate.getDayOfMonth();
                        String year = "" + input_duedate.getYear();
                        final String date = day + " " + whichMonth(input_duedate.getMonth() +1) + " " + year;

                        final Task new_task = new Task(new_name,new_description, date, new_priority, new_length );
                        tasks.add(new_task);
                        taskListAdapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        }
                });

                builder.show();
            }
                });

        //Get reference for add action button
        add_action = (Button) findViewById(R.id.add_action);

        //Add Action onClickListener where the user will be able to give NAME, PRIORITY and LENGTH for some action and
        //add it to a selected Task.
        add_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater dialog = LayoutInflater.from(TasksActivity.this);
                final View add_action_dialog_view = dialog.inflate(R.layout.add_action_dialog, null);

                final EditText input_name = (EditText) add_action_dialog_view.findViewById(R.id.action_title_textbox);
                final EditText input_description = (EditText) add_action_dialog_view.findViewById(R.id.action_description_textbox);
                final EditText input_priority = (EditText) add_action_dialog_view.findViewById(R.id.action_priority_textbox);
                final EditText input_length = (EditText) add_action_dialog_view.findViewById(R.id.action_length_textbox);
                final Spinner input_parent = (Spinner) add_action_dialog_view.findViewById(R.id.spinner);


                //INPUT TYPES
                input_name.setInputType(InputType.TYPE_CLASS_TEXT);
                input_description.setInputType(InputType.TYPE_CLASS_TEXT);
                input_priority.setInputType(InputType.TYPE_CLASS_NUMBER);
                input_length.setInputType(InputType.TYPE_CLASS_NUMBER);


                //Create and initialise an AlertBuilder to open a pop-up where
                //the user will be able to give some info about the new distraction
                final AlertDialog.Builder builder = new AlertDialog.Builder(TasksActivity.this);
                builder.setTitle("Add Task").setView(add_action_dialog_view);

                //Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        final String new_name = input_name.getText().toString();
                        final String new_description = input_description.getText().toString();
                        final String new_priority = input_priority.getText().toString();
                        final String new_length = input_length.getText().toString();
                        final String new_parent = String.valueOf(input_parent.getSelectedItem());

                        final Action new_action = new Action(new_name,new_description,new_priority, new_length );

                        taskListAdapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });

        //Menu button onClickListener
        menu_lilac.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(TasksActivity.this, view);
                popup.inflate(R.menu.mainmenu);
                popup.show();

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId()== R.id.Tasks){
                            Toast.makeText(context, "At tasks!", Toast.LENGTH_LONG).show();
                        }
                        if (item.getItemId() == R.id.Home){
                            startActivity(toHome);
                        }
                        if (item.getItemId() == R.id.Distractions){
                            startActivity(toDist);
                        }
                        if (item.getItemId() == R.id.Settings){
                            startActivity(toSett);
                        }
                        return false;
                    }
                });
            }
        });
    }

    private String whichMonth(int month){

        String january = "January";
        String february = "February";
        String march = "March";
        String april = "April";
        String may = "May";
        String june = "June";
        String july = "July";
        String august = "August";
        String september = "September";
        String october = "October";
        String november = "November";
        String december = "December";

        if (month == 1)
            return january;
        if (month == 2)
            return february;
        if (month == 3)
            return march;
        if (month == 4)
            return april;
        if (month == 5)
            return may;
        if (month == 6)
            return june;
        if (month == 7)
            return july;
        if (month ==8)
            return august;
        if (month == 9)
            return september;
        if (month == 10)
            return october;
        if (month == 11)
            return november;
        else return december;
        }
    }
    //private int addObject(String task_title, String action_title){

       // int groupPosition = 0;

        //check the parent arraylist if it exists
    //    Task task_info = task_parent.get(task_title);
        //Add the group if it doesn't exist;
    ///    if (task_info == null){
       //     task_info = new Task();
         //   task_info.setTask_title();
        //}

    //}
