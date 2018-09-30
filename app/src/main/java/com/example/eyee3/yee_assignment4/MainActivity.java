package com.example.eyee3.yee_assignment4;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothClass;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Toolbar myToolbar;
    TextView restLabel;
    ListView restList;
    String[] ListElements = new String[] {};
    final ArrayList<String> RestaurantsList = new ArrayList<String>(Arrays.asList(ListElements));

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        restLabel = (TextView) findViewById(R.id.restList);
        restList = (ListView) findViewById(R.id.restaurantList);

        myToolbar.setTitle("Discount Yelp");
        setSupportActionBar(myToolbar);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                Intent newRest = new Intent(MainActivity.this, newRest.class);
                startActivityForResult(newRest, 1);
                return true;

            case R.id.action_clear:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        MainActivity.this);

                alertDialogBuilder
                        .setMessage("Clear all restaurants?") //Verifies first
                        .setCancelable(false)
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                //Clear whole listview
                                //finish();
                            }
                        })
                        .setNegativeButton("No",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                return true;

            case R.id.action_load:

                return true;

            case R.id.action_pref:
                Intent pref = new Intent(MainActivity.this, pref.class);
                startActivityForResult(pref, 2);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }


}
