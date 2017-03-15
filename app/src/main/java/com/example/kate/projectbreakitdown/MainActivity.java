package com.example.kate.projectbreakitdown;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    //Creation of used objects
    //Button, Button, TextView, Handler, AlarmManager, NumberPicker, Pending Intent
    //Context, TextView, Button

    Button startButton;
    Button stopButton;
    TextView timerValue;
    Handler customHandler = new Handler();
    NumberPicker interval_length;
    Context context;
    TextView checker;
    ImageButton menu_lilac;

    //Creation of variables
    long timeInMilliseconds = 0L;
    long updatedTime = 0L;
    long startTime = 0L;
    int picked_value;
    private static int minValueInterval = 1;
    private static int maxValueInterval = 60;
    int final_interval;

    //On create function initialises the layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Creation of main GUI layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.context = this;

        //Intent to Tasks Activity
        final Intent toTasks = new Intent(this, TasksActivity.class);

        //Intent to Distractions Activity
        final Intent toDist = new Intent(this, DistractionsActivity.class);

        //Intent to Settings Activity
        final Intent toSett = new Intent(this, SettingsActivity.class);

        //Initialise number picker
        interval_length = (NumberPicker) findViewById(R.id.interval_length);

        //Set minimum value (minutes)
        interval_length.setMinValue(minValueInterval);
        //Set maximum value (minutes)
        interval_length.setMaxValue(maxValueInterval);
        //Set currently displayed value (minutes)
        interval_length.setValue(20);

        //Initialise listener to listen for a value change on the number picker
        interval_length.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //Replace variable picked_value with the new value of the number picker
                picked_value = interval_length.getValue() - 1;
            }
        });

        //Initialise timerValue text box
        timerValue = (TextView) findViewById(R.id.timerValue);

        //Initialise start button
        startButton = (Button) findViewById(R.id.startButton);

        //Initialise instance of stop button
        stopButton = (Button) findViewById(R.id.stopButton);

        //Initialise instance of menu button
        menu_lilac = (ImageButton) findViewById(R.id.menu_lilac);

        //Initialise checker text box
        checker = (TextView) findViewById(R.id.checker);

        //Menu button onClickListener
        menu_lilac.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(getApplicationContext(), view);
                popup.inflate(R.menu.mainmenu);
                popup.show();

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId()== R.id.Home){
                            Toast.makeText(context, "At home!", Toast.LENGTH_LONG).show();
                        }
                        if (item.getItemId() == R.id.Tasks){
                            startActivity(toTasks);
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

        //Start button OnClickListener
        startButton.setOnClickListener(new View.OnClickListener() {
            //On start begin counting up in milliseconds.
            //Reset timer every time Start button is pressed.
            public void onClick(View view) {
                timerValue.setText("00:00");
                startTime = SystemClock.uptimeMillis();
                customHandler.postDelayed(updateTimerThread, 0);

                //Set text box to print a message displaying when the alarm is for
                checker.setText("The alarm has been set for " + picked_value + " minutes from now!");

                //Make pop-up toast notification
                Toast.makeText(context, "Alarm on!", Toast.LENGTH_LONG).show();
            }
        });

        //Calculation of picked interval length (from minutes) to milliseconds
        final_interval = picked_value * 60 * 1000;
        //if (timerValue.equals(picked_value+":00")) {
        //    alarm_manager.set(AlarmManager.RTC_WAKEUP, timeInMilliseconds, pending_intent);
        //}

        //Initialise Stop button OnClickListener
        stopButton.setOnClickListener(new View.OnClickListener() {
            //On click stop updating timer thread and reset value to 00:00
            public void onClick(View view) {
                customHandler.removeCallbacks(updateTimerThread);
                timerValue.setText("00:00");

                //print message to notify user of alarm being cancelled
                checker.setText("The alarm has been cancelled!");
                //Make toast notification to say alarm cancelled
                Toast.makeText(context, "Alarm off!", Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();


    }

    //Creates a runnable to update the timer
    private Runnable updateTimerThread = new Runnable() {
        public void run() {
            //Takes time in milliseconds from the system clock
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            updatedTime = timeInMilliseconds;
            //Converts milliseconds to seconds and minutes
            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            int milliseconds = (int) (updatedTime % 1000);
            //Updates timerValue to the formatted 00:00 (minutes:seconds)
            timerValue.setText(String.format("%02d", mins) + ":"
                    + String.format("%02d", secs));
            customHandler.postDelayed(this, 0);
        }
    };
}


