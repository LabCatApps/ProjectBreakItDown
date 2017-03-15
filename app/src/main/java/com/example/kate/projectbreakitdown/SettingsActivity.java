package com.example.kate.projectbreakitdown;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by Kate on 06/03/2017.
 */

public class SettingsActivity extends Activity {

    Context context;

    //On Create function initialises the layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity_layout);
        this.context = this;
    }
}
