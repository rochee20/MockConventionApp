/**
 * The main activity for MockConApp
 *
 * Copyright (C) 2019 Emily Roche, Simon D. Levy
 */

package edu.wlu.mockconapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    // Instead of multiple activities, we use different views in the main activity
    private LinearLayout _eventLayout;
    private ScrollView _aboutScrollView;
    private LinearLayout _pressLayout;
    private LinearLayout _ticketLayout;

//    private CloudClient _cloudClient;
//
//    private SessionsDO _session;

    private static MainActivity _instance;

    public static final String EXTRA_MESSAGE = "edu.wlu.mockconapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Always gotta do this
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connect to our cloud service
//        _cloudClient = new CloudClient(this);

        // Grab views from resources
        _eventLayout = (LinearLayout) findViewById(R.id.event_layout);
        _aboutScrollView = (ScrollView)findViewById(R.id.about_scrollview);
        _pressLayout = (LinearLayout)findViewById(R.id.press_layout);
        _ticketLayout = (LinearLayout)findViewById(R.id.ticket_layout);

        // Keep an instance of ourself around for use by EventActivity
        _instance = this;

        // Always start with Events
        showEvents();

        //Need to enable all the hyperlinks to be clicked on and to open in new browser

        //Hyperlink to Events: General Information
        TextView t1 = (TextView) findViewById(R.id.general_info_events);
        t1.setMovementMethod(LinkMovementMethod.getInstance());

        //Hyperlink to Events: Platform and Rules
        TextView t2 = (TextView) findViewById(R.id.platform_rules_events);
        t2.setMovementMethod(LinkMovementMethod.getInstance());

        //Hyperlink to About: Meet the MockCon Departments
        TextView t3 = (TextView) findViewById(R.id.meet_the_department);
        t3.setMovementMethod(LinkMovementMethod.getInstance());

        //Hyperlink to About: Memorabilia
        TextView t4 = (TextView) findViewById(R.id.see_memorabilia);
        t4.setMovementMethod(LinkMovementMethod.getInstance());

        //Hyperlinks to About: General Information - Parking/Information/Etc & Platform and Rules
        TextView t5 = (TextView) findViewById(R.id.see_convention_layout);
        t5.setMovementMethod(LinkMovementMethod.getInstance());
        TextView t6 = (TextView) findViewById(R.id.see_platform_rules);
        t6.setMovementMethod(LinkMovementMethod.getInstance());

        //Hyperlink to About: Contact - Convention Team & Developers
        TextView t7 = (TextView) findViewById(R.id.see_contact);
        t7.setMovementMethod(LinkMovementMethod.getInstance());
        TextView t8 = (TextView) findViewById(R.id.see_developer);
        t8.setMovementMethod(LinkMovementMethod.getInstance());

        //Hyperlink to Press: Convention Chronicles
        TextView t9 = (TextView) findViewById(R.id.convention_chronicles);
        t9.setMovementMethod(LinkMovementMethod.getInstance());

        //Hyperlink to Press: Newsroom
        TextView t10 = (TextView) findViewById(R.id.newsroom);
        t10.setMovementMethod(LinkMovementMethod.getInstance());

    } // onCreate

    @Override
    protected void onResume() {
        super.onResume();

        // Download AWS data when main activity starts or resumes
//        _cloudClient.load();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar itemAmazonDynamoDB clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.nav_events) {
            showEvents();
        }
        if (id == R.id.nav_tickets) {
            showTickets();
        }
        if (id == R.id.nav_press) {
            showPress();
        }
        if (id == R.id.nav_about) {
            showAbout();
        }

        return super.onOptionsItemSelected(item);
    }

    private void hideAll() {

        hideView(_eventLayout);
        hideView(_aboutScrollView);
        hideView(_pressLayout);
        hideView(_ticketLayout);
    }

    private void showOne(String label, View view) {

        hideAll();
        setContents(label);
        showView(view);
    }

    private void showTickets() {

        showOne("Tickets", _ticketLayout);

        setTextFromHtml(R.id.ticket_blurb, R.string.ticket_string);
    }


    private void showAbout() {

        showOne("About", _aboutScrollView);

        setText(R.id.about_main_text, "Convention Weekend 2020");
        setTextFromHtml(R.id.about_text, R.string.about_string);
        setTextFromHtml(R.id.team_caption, R.string.team_string);
        setText(R.id.team_label, "THE TEAM");
        setText(R.id.memorabilia_label, "MEMORABILIA");
        setTextFromHtml(R.id.memorabilia_caption, R.string.memorabilia_string);
        setText(R.id.contact_label, "CONTACT");
        setText(R.id.convention_team, "CONVENTION TEAM");
        setTextFromHtml(R.id.contact_caption, R.string.contact_string);
        setText(R.id.developer_team, "DEVELOPERS");
        setTextFromHtml(R.id.developer_contact_caption, R.string.developer_contact_string);
        setText(R.id.general_information, "GENERAL INFORMATION");
    }

    private void showEvents() {

        // Set up image adapter for event grid view
        GridView eventGridView = (GridView)findViewById(R.id.event_grid_view);
        eventGridView.setAdapter(new EventsImageAdapter(this));
        eventGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showEvent(i);
            }
        });

        // Add a click listener to the main image button
        final ImageButton button = (ImageButton) findViewById(R.id.event_main_image_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showEvent(5); //testing whether you can make image id 5 (p&&m at the very end)
            }
        });

        // No selected session yet
//        _session = null;

        showOne("Events", _eventLayout);

        setText(R.id.event_main_text, "Featured Event");
        setText(R.id.event_description_text, "Welcome to the Mock Convention App! We created this interactive tool to allow Convention viewers near and far to experience the weekend and explore the work of Mock Convention 2020.");
        setText(R.id.event_subsidary_text, "All Events");
    }


    private void showPress() {

        //Speakers Grid View
        GridView speakersGridView = (GridView)findViewById(R.id.speakers_grid_view);
        speakersGridView.setAdapter(new SpeakersImageAdapter(this));
        speakersGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showSpeaker(i);
            }
        });

        showOne("Press", _pressLayout);

        setText(R.id.press_subsidary_text, "Press Releases & Articles");
        setText(R.id.press_speakers_text, "Speakers");
        setText(R.id.press_release_articles,"Follow the link below to read the work of Washington and Lee's student body in anticipation of Mock Convention 2020:");
        setText(R.id.press_release_articles_2,"Follow the link below to gain access to latest Mock Convention news as it breaks:");
    }

    private static void showView(View view) {
        view.setVisibility(View.VISIBLE);
    }

    private static void hideView(View view) {
        view.setVisibility(View.INVISIBLE);
    }

    private void setContents(String title) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo((R.mipmap.header_logo));
        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }

    private void setText(int id, String text) {
        TextView textView = findViewById(id);
        textView.setText(text);
    }

    private void setTextFromHtml(int textViewId, int stringId) {
        TextView textView = findViewById(textViewId);
        textView.setText(Html.fromHtml(getString(stringId)));
    }

    // EventActivity will use this to call methods below
    public static MainActivity getInstance() {
        return _instance;
    }

    // Instead of passing event info to EventActivity through a Bundle, we allow EventActivity to
    // query the info about the currently selected event via public methods in MainActivity.

    private void showEvent(int id) {
//        _session = _cloudClient.getSession(id);
        Intent intent = new Intent(this, EventActivity.class);
        intent.putExtra("ID", new Integer(id));
        startActivity(intent);
    }

    private void showSpeaker(int id) {
        Intent intent = new Intent(this, SpeakerActivity.class);
        intent.putExtra("ID", new Integer(id));
        startActivity(intent);
    }

//    public SessionsDO getSession() {
//        return _session;    }
//
}