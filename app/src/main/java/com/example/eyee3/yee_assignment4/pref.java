package com.example.eyee3.yee_assignment4;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class pref extends AppCompatActivity {

    Toolbar myToolbar;
    CheckBox cb1;
    CheckBox cb2;
    CheckBox cb3;
    Button savePref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pref);
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        cb1 = (CheckBox) findViewById(R.id.box1); //Phone number
        cb2 = (CheckBox) findViewById(R.id.box2); //Website
        cb3 = (CheckBox) findViewById(R.id.box3); //Category
        savePref = (Button) findViewById(R.id.savePref);

        myToolbar.setTitle("Preferences");
        setSupportActionBar(myToolbar);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));
        ActionBar ab = getSupportActionBar();
        //ab.setDisplayHomeAsUpEnabled(true);

        savePref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
