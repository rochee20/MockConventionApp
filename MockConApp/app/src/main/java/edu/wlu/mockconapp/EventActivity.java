/**
 * An activity for event details
 *
 * Copyright (C) 2019 Simon D. Levy
 */

package edu.wlu.mockconapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class EventActivity extends AppCompatActivity {

    private class Event {

        String name;
        int imageId;
        int blurbId;
        int scrollerId;
        int captionId;

        Event(String name, int imageId, int blurbId, int scrollerId, int captionId) {
            this.name = name;
            this.imageId = imageId;
            this.blurbId = blurbId;
            this.scrollerId = scrollerId;
            this.captionId = captionId;
        }
    }

    protected void onCreate(Bundle savedInstanceState) {

        Event [] events = {

                new Event("Parade", R.id.parade_image, R.string.parade_blurb, R.id.parade_scrollview, R.id.parade_caption),
                new Event("Session One", R.id.session1_image, R.string.session1_blurb, R.id.session1_scrollview, R.id.session1_caption),
                new Event("Session Two", R.id.session2_image, R.string.session2_blurb, R.id.session2_scrollview, R.id.session2_caption),
                new Event("Session Three", R.id.session3_image, R.string.session3_blurb, R.id.session3_scrollview, R.id.session3_caption), new Event("Session Four", R.id.session4_image, R.string.session4_blurb, R.id.session4_scrollview, R.id.session4_caption),
                new Event("Politics & Media", R.id.politics_in_media_image, R.string.politics_in_media_blurb, R.id.politics_in_media_scrollview, R.id.politics_in_media_caption)
        };



        // Always have to do this
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        // Get the speaker ID passed from the main activity
        Intent intent = getIntent();
        Integer id = (Integer) intent.getSerializableExtra("ID");

        // Add MockCon logo and speaker name to toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(events[id].name);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo((R.mipmap.header_logo));
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        // Show the speaker's view, and hide all others
        for (int k = 0; k < events.length; ++k) {
            ScrollView speakerView = (ScrollView) findViewById(events[k].scrollerId);
            speakerView.setVisibility(k == id ? View.VISIBLE : View.INVISIBLE);
            setTextFromHtml(events[k].captionId, events[k].blurbId);
        }
    }

    private void setTextFromHtml(int textViewId, int stringId) {
        TextView textView = findViewById(textViewId);
        textView.setText(Html.fromHtml(getString(stringId)));
    }
}

    // Below is the original code that links to the Session Retrieval

//    protected void onCreate(Bundle savedInstanceState) {
//
//        // Always have to do this
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_event);
//
//        // Get the instance of the MainActivity so we can call its methods
//        MainActivity mainActivity = MainActivity.getInstance();
//
//        // Use the MainActivity to get the current session
//        SessionsDO session = mainActivity.getSession();
//
//        TextView headerTextView = findViewById(R.id.header_text);
//
//        if (session == null) {
//            headerTextView.setText("no info");
//        }
//
//        else {
//            headerTextView.setText(session.getSessionTitle());
//            TextView locationTextView = findViewById(R.id.location_text);
//
//            // get subevents will allow you to access all the event titles - use a
//            // for loop to go through; get your body text in a text box that is constrained side by side and
//            //you should be good for it to format itself; you may be able to pull session by id too
//            locationTextView.setText(session.getLocation());
//       }
//    }
//}
