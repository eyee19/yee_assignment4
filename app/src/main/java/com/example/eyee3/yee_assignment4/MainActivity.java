package com.example.eyee3.yee_assignment4;

//Resources:
//Android documentation
//Text style: https://stackoverflow.com/questions/4792260/how-do-you-change-text-to-bold-in-android
//setEmptyView: https://stackoverflow.com/questions/12483508/setemptyview-on-listview-not-showing-its-view-in-a-android-app
//Get spinner value: https://stackoverflow.com/questions/1947933/how-to-get-spinner-value
//Rating bar value: https://stackoverflow.com/questions/7332537/how-to-get-ratingbar-value
//Dial a number: https://stackoverflow.com/questions/22372561/android-dial-a-phone-number-programmatically
//Website onClick: https://stackoverflow.com/questions/8352208/how-do-i-launch-a-url-in-browser-from-an-onclick-event-on-a-textview
//Fixing getSerializable error: https://stackoverflow.com/questions/12774499/getserializableextra-returning-null

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
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
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

        myToolbar.setTitle("Local Eatz");
        setSupportActionBar(myToolbar);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));

        restList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String currentString = restList.getItemAtPosition(position).toString();
                String [] separated = currentString.split(Pattern.quote("\n"));
                Intent i = new Intent(MainActivity.this, viewRest.class);

                i.putExtra("slogan", separated[0]);
                i.putExtra("name", separated[1]);
                i.putExtra("phone", separated[2]);
                i.putExtra("website", separated[3]);
                i.putExtra("rating", separated[4]);
                i.putExtra("category", separated[5]);

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
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>
                        (MainActivity.this, android.R.layout.simple_list_item_1, RestaurantsList);
                restList.setAdapter(adapter);

                try {
                    InputStream inputreader = getAssets().open("loadList.txt");
                    BufferedReader buffreader = new BufferedReader(new InputStreamReader(inputreader));

                    Scanner  sc = new Scanner(inputreader);
                    String count = sc.nextLine();
                    int convertCount = Integer.valueOf(count);
                    //int count = sc.nextInt();
                    for (int i = 0; i < convertCount; i++) {
                        String slogan = sc.nextLine();
                        String name = sc.nextLine();
                        String phone = sc.nextLine();
                        String website = sc.nextLine();
                        String rating = sc.nextLine();
                        String category = sc.nextLine();

                        RestaurantsList.add(name + "\n"
                                + slogan + "\n"
                                + phone + "\n"
                                + website + "\n"
                                + rating + " Stars" + "\n"
                                + category);
                    }
                    buffreader.close() ;
                    adapter.notifyDataSetChanged();
                } catch(java.io.FileNotFoundException e){
                    e.printStackTrace();
                } catch(java.io.IOException e){
                    e.printStackTrace();
                }
                return true;

            case R.id.action_sort:
                Collections.sort(RestaurantsList);
                final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, RestaurantsList);

                if (adapter2.isEmpty()) {
                    Toast.makeText(MainActivity.this, "No Restaurants to Sort", Toast.LENGTH_SHORT).show();
                    return true;
                }
                else {
                    restList.setAdapter(adapter2);
                    adapter2.notifyDataSetChanged();
                    return true;
                }

            case R.id.action_pref:
                ArrayList<Object> allArray = new ArrayList<Object>();
                for (int i = 1; i < RestaurantsList.size(); i++) {
                    allArray.add(restList.getItemAtPosition(i));
                }

                Intent pref = new Intent(MainActivity.this, pref.class);
                pref.putExtra("list", allArray);
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
                Restaurant received = (Restaurant) data.getSerializableExtra("newRest");

                final String sloganReturn = received.getSlogan();
                final String nameReturn = received.getName();
                final String phoneReturn = received.getPhone();
                final String websiteReturn = received.getWebsite();
                final float ratingReturn = received.getRating();
                final String categoryReturn = received.getCategory();

                RestaurantsList.add("Name: " + nameReturn + "\n"
                        + "Slogan: " + sloganReturn + "\n"
                        + "Phone: " + phoneReturn + "\n"
                        + "Website: " + websiteReturn + "\n"
                        + "Rating: " + ratingReturn + " Stars" + "\n"
                        + "Category: " + categoryReturn);

                adapter.notifyDataSetChanged();

                if (ratingReturn == 5.0) {
                    final String convertRating = String.valueOf(ratingReturn);
                    final String convertAgain = "Rating: " + convertRating;
                    final String slogan5 = "Slogan: " + sloganReturn;
                    final String name5 = "Name: " + nameReturn;
                    final String phone5 = "Phone: " + phoneReturn;
                    final String website5 = "Website: " + websiteReturn;
                    final String category5 = "Category: " + categoryReturn;

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                            MainActivity.this);

                    alertDialogBuilder
                            .setMessage("A 5 Star Restaurant Was Added! View It?") //Verifies first
                            .setCancelable(false)
                            .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    Intent i = new Intent(MainActivity.this, viewRest.class);

                                    i.putExtra("slogan", slogan5);
                                    i.putExtra("name", name5);
                                    i.putExtra("phone", phone5);
                                    i.putExtra("website", website5);
                                    i.putExtra("rating", convertAgain);
                                    i.putExtra("category", category5);

                                    startActivityForResult(i, 3);
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
                }
            }
        }
    }


}
