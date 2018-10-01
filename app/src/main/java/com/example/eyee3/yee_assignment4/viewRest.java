package com.example.eyee3.yee_assignment4;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class viewRest extends AppCompatActivity {

    Toolbar myToolbar;
    TextView name;
    TextView phone;
    TextView website;
    RatingBar rating;
    TextView category;
    Button back;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_rest);
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        name = (TextView) findViewById(R.id.nameView);
        phone = (TextView) findViewById(R.id.phoneView);
        website = (TextView) findViewById(R.id.websiteView);
        rating = (RatingBar) findViewById(R.id.ratingView);
        category = (TextView) findViewById(R.id.categoryView);
        back = (Button) findViewById(R.id.back);

        myToolbar.setTitle("View Restaurant");
        setSupportActionBar(myToolbar);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));
        ActionBar ab = getSupportActionBar();
        //ab.setDisplayHomeAsUpEnabled(true);

        final Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            name.setText(bundle.getString("name"));
            phone.setText(bundle.getString("phone"));
            website.setText(bundle.getString("website"));

            String getRating = bundle.getString("rating");
            String ratingRating = getRating.substring(8, 11);
            float floatRating = Float.valueOf(ratingRating);
            rating.setRating(floatRating);

            category.setText(bundle.getString("category"));
        }

        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = bundle.getString("phone");
                String sub = number.substring(7);
                Uri numberToCall = Uri.parse("tel:" + sub);
                Log.d("viewRest", String.valueOf(numberToCall));
                Intent callIntent = new Intent(Intent.ACTION_DIAL, numberToCall);
                startActivity(callIntent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

