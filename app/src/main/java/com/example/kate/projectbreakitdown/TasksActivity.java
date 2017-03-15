package com.example.kate.projectbreakitdown;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by Kate on 06/03/2017.
 */

public class TasksActivity extends Activity {

    Context context;
    ImageButton menu_lilac;

    private ArrayList<Task> task_parent = new ArrayList<>();
    private HashMap<String, Action> task_child = new HashMap<>();

    private ExpandableListAdapter taskListAdapter;
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

        //Get reference for expendable list view
        task_listview = (ExpandableListView) findViewById(R.id.list_of_tasks);

        //create the adapter and pass parent data array
        taskListAdapter = new TaskListAdapter(TasksActivity.this, task_parent, task_child);
        //Attach the adapter
        task_listview.setAdapter(taskListAdapter);

        //set on child click listener for child row click
        task_listview.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                //Get the group header
                Task task_title = task_parent.get(groupPosition);
                //Get the child info
                Action action_title = task_title.getActions().get(childPosition);
                //Display it or more...
                Toast.makeText(getBaseContext(), "Task is " + task_title.getTask_title()
                                + ". Action is " + action_title.getName(), Toast.LENGTH_LONG).show();
                return false;
            }
        });

        //set on parent click listener for parent row click
        task_listview.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                //get the group header
                Task task_title = task_parent.get(groupPosition);
                //Display it or more...
                Toast.makeText(getBaseContext(), "Task is " + task_title.getTask_title(), Toast.LENGTH_LONG).show();

                return false;
            }
        });

        task_parent.add(new Task("Cryptography coursework", "Decrypt ciphers using different methods.", "2017-03-31", "9", "240"));
        task_child.put("Cryptography coursework", new Action("Cryptography coursework", "EIGamal cipher", "Decrypt cipher text using EIGamal cipher.", "8", "60"));

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
}
