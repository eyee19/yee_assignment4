package com.example.eyee3.yee_assignment4;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
    RelativeLayout newRestParent;

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

        findViewById(R.id.newRestParent).setOnTouchListener(new View.OnTouchListener() { //Makes keyboard disappear when user clicks outside of text boxes
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                return true;
            }
        });

        String[] items = new String[]{"American", "Mexican", "Chinese", "Indian", "Italian", "French", "Japanese"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);

        category.setAdapter(adapter);

        add = (Button) findViewById(R.id.add);
        cancel = (Button) findViewById(R.id.cancel);

        myToolbar.setTitle("New Restaurant");
        setSupportActionBar(myToolbar);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));
        ActionBar ab = getSupportActionBar();
        //ab.setDisplayHomeAsUpEnabled(true);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().trim().length() <= 0) {
                    Toast.makeText(newRest.this, "Restaurant Name Cannot Be Empty", Toast.LENGTH_LONG).show(); //If name is empty
                }
                else {
                    Intent backHome = new Intent();
                    String nameReturn = name.getText().toString();
                    String phoneReturn = phone.getText().toString();
                    String websiteReturn = website.getText().toString();
                    float ratingReturn = rating.getRating();
                    String categoryReturn = category.getSelectedItem().toString();

                    Restaurant newRest = new Restaurant(nameReturn, phoneReturn, websiteReturn, ratingReturn, categoryReturn);

                    backHome.putExtra("newRest", newRest);

                    /*backHome.putExtra("nameReturn", nameReturn);
                    backHome.putExtra("phoneReturn", phoneReturn);
                    backHome.putExtra("websiteReturn", websiteReturn);
                    backHome.putExtra("ratingReturn", ratingReturn);
                    backHome.putExtra("categoryReturn", categoryReturn);*/

                    if (ratingReturn == 5.0) {
                        PendingIntent pi = PendingIntent.getActivity(newRest.this, 0, new Intent(newRest.this, viewRest.class), 0);
                        Resources r = getResources();
                        Notification notification = new NotificationCompat.Builder(newRest.this)
                                .setSmallIcon(android.R.drawable.ic_menu_add)
                                .setContentTitle("5/5 Stars!")
                                .setContentText(nameReturn + " received a 5 star rating!")
                                .setContentIntent(pi)
                                .setAutoCancel(true)
                                .build();

                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify(0, notification);

                        Toast.makeText(newRest.this, nameReturn + " received a 5 star rating!", Toast.LENGTH_LONG).show();
                        /*Intent broadcast = new Intent("my.action.string");
                        broadcast.putExtra("extra", nameReturn);
                        sendBroadcast(broadcast);*/
                    }

                    setResult(Activity.RESULT_OK, backHome);
                    finish();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
