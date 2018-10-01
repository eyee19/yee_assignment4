package com.example.eyee3.yee_assignment4;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.CheckBox;
import android.widget.TextView;

public class pref extends AppCompatActivity {

    Toolbar myToolbar;
    CheckBox cb1;
    CheckBox cb2;
    CheckBox cb3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pref);
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        cb1 = (CheckBox) findViewById(R.id.box1);
        cb2 = (CheckBox) findViewById(R.id.box2);
        cb3 = (CheckBox) findViewById(R.id.box3);

        myToolbar.setTitle("Preferences");
        setSupportActionBar(myToolbar);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));
        ActionBar ab = getSupportActionBar();
        //ab.setDisplayHomeAsUpEnabled(true);
    }
}
