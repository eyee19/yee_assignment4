package com.example.eyee3.yee_assignment4;

//Resources:
//Android documentation
//Text style: https://stackoverflow.com/questions/4792260/how-do-you-change-text-to-bold-in-android
//setEmptyView: https://stackoverflow.com/questions/12483508/setemptyview-on-listview-not-showing-its-view-in-a-android-app
//Get spinner value: https://stackoverflow.com/questions/1947933/how-to-get-spinner-value
//Rating bar value: https://stackoverflow.com/questions/7332537/how-to-get-ratingbar-value
//Dial a number: https://stackoverflow.com/questions/22372561/android-dial-a-phone-number-programmatically
//Website onClick: https://stackoverflow.com/questions/8352208/how-do-i-launch-a-url-in-browser-from-an-onclick-event-on-a-textview

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothClass;
import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements Serializable {

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

        myToolbar.setTitle("Chapman Yelp");
        setSupportActionBar(myToolbar);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));

        restList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String currentString = restList.getItemAtPosition(position).toString();
                String [] separated = currentString.split(Pattern.quote("\n"));
                Intent i = new Intent(MainActivity.this, viewRest.class);

                i.putExtra("name", separated[0]);
                i.putExtra("phone", separated[1]);
                i.putExtra("website", separated[2]);
                i.putExtra("rating", separated[3]);
                i.putExtra("category", separated[4]);

                startActivityForResult(i, 2);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
                                final ArrayAdapter<String> testAdapter = new ArrayAdapter<String>
                                        (MainActivity.this, android.R.layout.simple_list_item_1, RestaurantsList);
                                restList.setAdapter(testAdapter);
                                testAdapter.clear();
                                testAdapter.notifyDataSetChanged();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (MainActivity.this, android.R.layout.simple_list_item_1, RestaurantsList);
        restList.setAdapter(adapter);

        if(requestCode == 1) {
            if(resultCode == Activity.RESULT_OK) {
                /*String nameReturn = data.getStringExtra("nameReturn");
                String phoneReturn = data.getStringExtra("phoneReturn");
                String websiteReturn = data.getStringExtra("websiteReturn");
                float ratingReturn = data.getFloatExtra("ratingReturn", 1);
                String categoryReturn = data.getStringExtra("categoryReturn");*/

                Restaurant received = (Restaurant) data.getSerializableExtra("newRest");
                String nameReturn = received.getName();
                String phoneReturn = received.getPhone();
                String websiteReturn = received.getWebsite();
                float ratingReturn = received.getRating();
                String categoryReturn = received.getCategory();

                //RestaurantsList.toArray(getIntent().getSerializableExtra("newRest"));
                //RestaurantsList.add(getIntent().getSerializableExtra("newRest"));

                /*Log.d("MainActivity", "RETURNED VALUES: " + nameReturn);
                Log.d("MainActivity", "RETURNED VALUES: " + phoneReturn);
                Log.d("MainActivity", "RETURNED VALUES: " + websiteReturn);
                Log.d("MainActivity", "RETURNED VALUES: " + ratingReturn);
                Log.d("MainActivity", "RETURNED VALUES: " + categoryReturn);*/

                RestaurantsList.add("Name: " + nameReturn + "\n"
                        + "Phone: " + phoneReturn + "\n"
                        + "Website: " + websiteReturn + "\n"
                        + "Rating: " + ratingReturn + " Stars" + "\n"
                        + "Category: " + categoryReturn);

                adapter.notifyDataSetChanged();
            }
        }
    }


}
