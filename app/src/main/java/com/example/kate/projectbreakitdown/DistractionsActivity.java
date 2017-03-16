package com.example.kate.projectbreakitdown;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kate on 06/03/2017.
 */

public class DistractionsActivity extends Activity {

    //Creation of used objects

    ImageButton menu_lilac;
    Button add_distraction;
    EditText distraction_name;
    EditText distraction_priority;
    CheckBox distractions_repeat;
    Context context;
    ListView distractions_list;

    //On Create function initialises the layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.distractions_activity_layout);
        this.context = this;

        //Intent to Tasks Activity
        final Intent toTasks = new Intent(DistractionsActivity.this, TasksActivity.class);

        //Intent to Home Activity
        final Intent toHome = new Intent(DistractionsActivity.this, MainActivity.class);

        //Intent to Settings Activity
        final Intent toSett = new Intent(DistractionsActivity.this, SettingsActivity.class);

        //Initialise instance of menu button
        menu_lilac = (ImageButton) findViewById(R.id.menu_lilac);

        //Initialise list view from layout
        distractions_list = (ListView) findViewById(R.id.distractions_list);

        //Initialise edit box for name of distraction
        distraction_name = (EditText) findViewById(R.id.editText);

        //Initialise edit box for priority of distraction
        distraction_priority = (EditText) findViewById(R.id.editText2);

        //Initialise check box for repeat distraction
        distractions_repeat = (CheckBox) findViewById(R.id.checkBox);

        //Declare an Array List of Distractions
        final ArrayList<Distraction> distractionsArray = new ArrayList<>();

        //Creation of first sample data
        distractionsArray.add(new Distraction("Example: Text from Alice", "3", "1"));

        //Initialise the custom array adapter (nested ArrayAdapter class at the end of this activity)
        final ArrayAdapter<Distraction> adapter = new distractionArrayAdapter(DistractionsActivity.this, 0, distractionsArray );

        //Bind list view with the custom adapter
        distractions_list.setAdapter(adapter);

        //Adapter onclickable function
        distractions_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                //Popup menu for distraction (EDIT, REMOVE)
                PopupMenu popup2 = new PopupMenu(DistractionsActivity.this, view);
                popup2.inflate(R.menu.distraction_menu);
                popup2.show();

                popup2.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        //WHEN USER PRESSES EDIT
                        if (item.getItemId() == R.id.Edit) {

                            LayoutInflater dialog = LayoutInflater.from(DistractionsActivity.this);
                            final View edit_distraction_dialog_view = dialog.inflate(R.layout.edit_distractions, null);

                            //Create and initialise an AlertBuilder to open a pop-up where
                            //the user will be able to give some info about the distraction
                            final AlertDialog.Builder builder = new AlertDialog.Builder(DistractionsActivity.this);
                            builder.setTitle("Edit Distraction").setView(edit_distraction_dialog_view);

                            builder.show();
                            // builder.setView(R.layout.edit_distractions);
                            final EditText input_name = (EditText) edit_distraction_dialog_view.findViewById(R.id.edit_distraction_name_edittext);
                            final EditText input_priority = (EditText) edit_distraction_dialog_view.findViewById(R.id.edit_distraction_priority_edittext);
                            
                            //Set positive OK button for alert dialog
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            final String new_name = input_name.getText().toString();
                                            final String new_priority = input_priority.getText().toString();

                                            final String old_name = distractionsArray.get(position).getDistraction_name();
                                            final String old_priority = distractionsArray.get(position).getDistraction_priority();

                                            if (!(new_name.equals(old_name))){
                                                distractionsArray.get(position).setDistraction_name(new_name);
                                                if (!(new_priority.equals(old_priority))){
                                                    distractionsArray.get(position).setDistraction_priority(new_priority);
                                                }
                                            }
                                            if (!(new_priority.equals(old_name))){
                                                distractionsArray.get(position).setDistraction_name(new_name);
                                                if (!(new_priority.equals(old_priority))){
                                                    distractionsArray.get(position).setDistraction_priority(new_priority);
                                                }
                                            }
                                            if (new_name.isEmpty() || new_priority.isEmpty()){
                                                Toast.makeText(DistractionsActivity.this, "No input!", Toast.LENGTH_SHORT);
                                            }
                                            else
                                            {
                                                Toast.makeText(DistractionsActivity.this, "Already exists!", Toast.LENGTH_SHORT);
                                            }
                                            adapter.notifyDataSetChanged();
                                        }
                                    });

                            //Set negative CANCEL button for the alert dialog
                            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //Close alert dialog
                                        dialog.cancel();
                                    }
                                });
                        }
                        //WHEN USER PRESSES REMOVE
                        if (item.getItemId() == R.id.Remove) {
                            adapter.remove(distractionsArray.get(position));
                            adapter.notifyDataSetChanged();
                        }
                        return false;
                    }
                });

            }
        });

        //Initialise add_distractions button
        add_distraction = (Button) findViewById(R.id.add_distraction);

        add_distraction.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                final String new_name = distraction_name.getText().toString();
                final String new_priority = distraction_priority.getText().toString();
                final String new_occurance = "0";

                distractionsArray.add(new Distraction(new_name, new_priority, new_occurance));
                adapter.notifyDataSetChanged();

            }
        });


        //Menu button onClickListener
        menu_lilac.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(DistractionsActivity.this, view);
                popup.inflate(R.menu.mainmenu);
                popup.show();

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId()== R.id.Distractions){
                            Toast.makeText(context, "At distractions!", Toast.LENGTH_LONG).show();
                        }
                        if (item.getItemId() == R.id.Tasks){
                            startActivity(toTasks);
                        }
                        if (item.getItemId() == R.id.Home){
                            startActivity(toHome);
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

    protected void onStart() {
        super.onStart();



    }


    //Custom ArrayAdapter
    class distractionArrayAdapter extends ArrayAdapter<Distraction>{

        private Context context;
        private List<Distraction> distractionsArray;

        //Constructor call on creation
        public distractionArrayAdapter(Context context, int resource,
                                       ArrayList<Distraction> objects){
            super(context, resource, objects);

            this.context = context;
            this.distractionsArray = objects;
        }

        //Called when rendering the list
        public View getView(int position, View convertView, ViewGroup parent){

            //Get the distraction we are displaying
            Distraction distraction = distractionsArray.get(position);

            //Get the inflater and inflate the XML layout
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.distraction_view, null);

            TextView name = (TextView) view.findViewById(R.id.distraction_name);
            TextView priority = (TextView) view.findViewById(R.id.distraction_priority);
            TextView occurance = (TextView) view.findViewById(R.id.distraction_repeat);

            name.setText("Name:" + String.valueOf(distraction.getDistraction_name()));
            priority.setText("Priority:" + String.valueOf(distraction.getDistraction_priority()));
            occurance.setText("Times:" + String.valueOf(distraction.getDistraction_occurance()));

            return view;
        }
    }
}