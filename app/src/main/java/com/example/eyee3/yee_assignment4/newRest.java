package com.example.eyee3.yee_assignment4;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class newRest extends AppCompatActivity {

    Toolbar myToolbar;
    EditText name;
    EditText phone;
    EditText website;
    TextView tv;
    TextView tv2;
    RatingBar rating;
    Spinner category;
    Button add;
    Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_rest);
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        name = (EditText) findViewById(R.id.name);
        phone = (EditText) findViewById(R.id.phone);
        website = (EditText) findViewById(R.id.website);
        tv = (TextView) findViewById(R.id.label);
        rating = (RatingBar) findViewById(R.id.rating);
        tv2 = (TextView) findViewById(R.id.label2);
        category = (Spinner) findViewById(R.id.category);

        String[] items = new String[]{"American", "Mexican", "Chinese", "Indian", "Italian", "French", "Japanese"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);

        category.setAdapter(adapter);

        add = (Button) findViewById(R.id.add);
        cancel = (Button) findViewById(R.id.cancel);

        myToolbar.setTitle("New Restaurant");
        setSupportActionBar(myToolbar);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
