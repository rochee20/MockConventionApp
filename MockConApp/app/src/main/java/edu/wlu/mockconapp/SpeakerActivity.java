package edu.wlu.mockconapp;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.Toolbar;

public class SpeakerActivity extends AppCompatActivity {

    private class Speaker {

        String name;
        int imageId;
        int blurbId;
        int scrollerId;
        int captionId;

        Speaker(String name, int imageId, int blurbId, int scrollerId, int captionId) {
            this.name = name;
            this.imageId = imageId;
            this.blurbId = blurbId;
            this.scrollerId = scrollerId;
            this.captionId = captionId;
        }
    }

    protected void onCreate(Bundle savedInstanceState) {

        Speaker [] speakers = {

                new Speaker("Trevor Noah", R.id.trevor_noah_image, R.string.trevor_noah_blurb, R.id.trevor_noah_scrollview, R.id.trevor_noah_caption),
                new Speaker("Tara McGowan", R.id.tara_mcgowan_image, R.string.tara_mcgowan_blurb, R.id.tara_mcgowan_scrollview, R.id.tara_mcgowan_caption),
                new Speaker("Symone Sanders", R.id.symone_sanders_image, R.string.symone_sanders_blurb, R.id.symone_sanders_scrollview, R.id.symone_sanders_caption),
                new Speaker("Patti Solis Doyle", R.id.patti_solis_doyle_image, R.string.patti_solis_doyle_blurb, R.id.patti_solis_doyle_scrollview, R.id.patti_solis_doyle_caption),
                new Speaker("Nadine Strossen", R.id.nadine_strossen_image, R.string.nadine_strossen_blurb, R.id.nadine_strossen_scrollview, R.id.nadine_strossen_caption),
                new Speaker("Mike Allen", R.id.mike_allen_image, R.string.mike_allen_blurb, R.id.mike_allen_scrollview, R.id.mike_allen_caption),
                new Speaker("Andrew Gillum", R.id.andrew_gillum_image, R.string.andrew_gillum_blurb, R.id.andrew_gillum_scrollview, R.id.andrew_gillum_caption),
                new Speaker("Donna Brazile", R.id.donna_brazille_image, R.string.donna_brazille_blurb, R.id.donna_brazille_scrollview, R.id.donna_brazille_caption),
                new Speaker("Cameron Kasky", R.id.cameron_kasky_image, R.string.cameron_kasky_blurb, R.id.cameron_kasky_scrollview, R.id.cameron_kasky_caption),
                new Speaker("Errin Haines", R.id.errin_haines_image, R.string.errin_haines_blurb, R.id.errin_haines_scrollview, R.id.errin_haines_caption),
                new Speaker("Joe Donnelly", R.id.joe_donnelly_image, R.string.joe_donnelly_blurb, R.id.joe_donnelly_scrollview, R.id.joe_donnelly_caption),
                new Speaker("Jim Acosta", R.id.jim_acosta_image, R.string.jim_acosta_blurb, R.id.jim_acosta_scrollview, R.id.jim_acosta_caption),
                new Speaker("Bill Roberts", R.id.bill_roberts_image, R.string.bill_roberts_blurb, R.id.bill_roberts_scrollview, R.id.bill_roberts_caption)
        };



        // Always have to do this
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speaker);

        // Get the speaker ID passed from the main activity
        Intent intent = getIntent();
        Integer id = (Integer) intent.getSerializableExtra("ID");

        // Add MockCon logo and speaker name to toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(speakers[id].name);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo((R.mipmap.header_logo));
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        // Show the speaker's view, and hide all others
        for (int k = 0; k < speakers.length; ++k) {
            ScrollView speakerView = (ScrollView) findViewById(speakers[k].scrollerId);
            speakerView.setVisibility(k == id ? View.VISIBLE : View.INVISIBLE);
            setTextFromHtml(speakers[k].captionId, speakers[k].blurbId);
        }
    }

    private void setTextFromHtml(int textViewId, int stringId) {
        TextView textView = findViewById(textViewId);
        textView.setText(Html.fromHtml(getString(stringId)));
    }
}
