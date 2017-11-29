package com.msushanth.tablesapp.PresentationLayer.FormClasses.Chat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.msushanth.tablesapp.Interfaces.Chat.SetWhenAndWhereToMeetInterface;
import com.msushanth.tablesapp.MainActivity;
import com.msushanth.tablesapp.PresentationLayer.ActionClasses.Chat.SetWhenAndWheretoMeetAction;
import com.msushanth.tablesapp.PresentationLayer.FormClasses.Account.LogInForm;
import com.msushanth.tablesapp.R;
import com.msushanth.tablesapp.Room;
import com.msushanth.tablesapp.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Sushanth on 11/9/17.
 */

public class SetWhenAndWhereToMeetForm extends AppCompatActivity implements SetWhenAndWhereToMeetInterface {
    private Toolbar mToolbar;

    TimePicker meetingTimePicker;
    DatePicker meetingDatePicker;
    EditText meetingLocationText;
    String roomID;
    String time;
    String date;
    String meetingLocation;
    List<String> users;


    String chatRoomID;
    String user1name;
    String user1id;
    String user2name;
    String user2id;

    String chatTime;
    String chatDate;
    String location;


    boolean user1SentInvite;
    boolean user2SentInvite;
    String user1Accepted;
    String user2Accepted;

    Room thisChatRoom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_time_location);

        mToolbar = (Toolbar) findViewById(R.id.nav_action);
        TextView pageTitle = (TextView) mToolbar.findViewById(R.id.pageTitle);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        pageTitle.setText("Enter Time and Location");
        pageTitle.setTextSize(25);

        // add the back button to the toolbar
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        // Get the chat room
        Intent intent = getIntent();
        chatRoomID = intent.getStringExtra("chatRoomID");
        user1name = intent.getStringExtra("user1name");
        user1id = intent.getStringExtra("user1ID");
        user2name = intent.getStringExtra("user2name");
        user2id = intent.getStringExtra("user2ID");
        chatTime = intent.getStringExtra("time");
        chatDate = intent.getStringExtra("date");
        location = intent.getStringExtra("location");
        user1SentInvite = intent.getBooleanExtra("user1SentInvite", true);
        user2SentInvite = intent.getBooleanExtra("user2SentInvite", false);
        user1Accepted = intent.getStringExtra("user1Accepted");
        user2Accepted = intent.getStringExtra("user2Accepted");

        thisChatRoom = new Room();
        thisChatRoom.setRoomID(chatRoomID);
        thisChatRoom.setUser1Name(user1name);
        thisChatRoom.setUser1ID(user1id);
        thisChatRoom.setUser2Name(user2name);
        thisChatRoom.setUser2ID(user2id);
        thisChatRoom.setTime(chatTime);
        thisChatRoom.setDate(chatDate);
        thisChatRoom.setLocation(location);
        thisChatRoom.setUser1SentInvite(user1SentInvite);
        thisChatRoom.setUser2SentInvite(user2SentInvite);
        thisChatRoom.setUser1Accepted(user1Accepted);
        thisChatRoom.setUser2Accepted(user2Accepted);


        meetingTimePicker = (TimePicker) findViewById(R.id.timePicker);
        meetingDatePicker = (DatePicker) findViewById(R.id.datePicker);
        meetingLocationText = (EditText) findViewById(R.id.LocationInputText);

        // assume you have a roomID and the users
        roomID = "";
        time = "";
        date = "";
        meetingLocation = "";
        users = new ArrayList<String>();
    }


    // implement the back button to the toolbar
    // implement the options in the menu on the toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    public void submitButtonClicked(View view) {

        // create the room
        //assume you have a roomID and the user assign them to null


        String time = "";
        int hour = meetingTimePicker.getCurrentHour();
        int min = meetingTimePicker.getCurrentMinute();

        String date = (meetingDatePicker.getMonth()+1) + "/" + meetingDatePicker.getDayOfMonth();

        if(hour <= 12) {
            time += hour;
            time += ":";
            if(min < 10){
                time += "0";
                time += min;
            } else {
                time += min;
            }
            time += " AM";
        }
        else {
            time += hour%12;
            time += ":";
            if(min < 10){
                time += "0";
                time += min;
            } else {
                time += min;
            }
            time += " PM";
        }

        String location = meetingLocationText.getText().toString();

        System.out.println("**** Time:" + time + "****");
        System.out.println("**** Date:" + date + "****");
        System.out.println("**** Location:" + location + "****");

        thisChatRoom.setTime(time);
        thisChatRoom.setDate(date);
        thisChatRoom.setLocation(location);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(chatRoomID+"CHAT");
        databaseReference.getParent().child(thisChatRoom.getRoomID()).setValue(thisChatRoom);


        //setTimeDateLocation(room);
        /*Intent logInIntent = new Intent();
        startActivity(logInIntent);
        finish();*/
    }


    public boolean checkTimeAndDate(){return true;}
    @Override
    public void setTimeDateLocation(Room room){
        SetWhenAndWheretoMeetAction act = new SetWhenAndWheretoMeetAction();
        act.setTimeDateLocation(room);
    }

}