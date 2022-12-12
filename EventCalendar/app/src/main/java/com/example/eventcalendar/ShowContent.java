package com.example.eventcalendar;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


/**
 * second activity
 */
public class ShowContent extends AppCompatActivity{
    private String event_name;
    private String date;
    private String time;
    private String FACEBOOK_URL;
    private String INSTAGRAM_URL;
    private int event_img;
    private ImageView imageView;
    private TextView textView;
    private ImageButton retButton;
    private ImageButton fbButton;
    private ImageButton instButton;
    private String passName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_eventcontent);

        getContent();
        drawContent();

        // set URL for facebook and instagram. The URL is passed from first activity
        // call method handleButton
        handleButtonClick(fbButton, FACEBOOK_URL);
        handleButtonClick(instButton, INSTAGRAM_URL);

        //finish the current activity when pressed. takes you back to the first activity
        retButton.setOnClickListener(v -> finish());

        // class to show popup window with emoji buttons
       new MyPopUp(this, findViewById(R.id.like_image));


    }
    private void handleButtonClick(ImageButton button, String url) {
        button.setOnClickListener(v -> startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(url))));
    }


    /**
     * get the strings and integer content send from "main" activity (getIntent()).
     * set reference variables to respective views (XML attributes)
     */
    private void getContent() {
        event_name = getIntent().getStringExtra("EVENTNAME");
        date = getIntent().getStringExtra("DATE");
        time = getIntent().getStringExtra("TIME");
        event_img = getIntent().getIntExtra("EVENT_IMG",0);
        imageView = findViewById(R.id.show_eventimg);
        textView = findViewById(R.id.show_eventname);
        retButton = findViewById(R.id.returnbuttom);
        fbButton = findViewById(R.id.facebook_btn);
        instButton = findViewById(R.id.instagram_btn);
        FACEBOOK_URL = getIntent().getStringExtra("FB_URL");
        INSTAGRAM_URL = getIntent().getStringExtra("INST_URL");
        passName = getIntent().getStringExtra("PASS_NAME");
    }

    /**
     * set the drawable and the text (second activity)
     */
    private void drawContent() {
        imageView.setImageResource(event_img);
        textView.setText(event_name + "\n" + date + "\n" + "time" + "\n" +time);


    }

}
