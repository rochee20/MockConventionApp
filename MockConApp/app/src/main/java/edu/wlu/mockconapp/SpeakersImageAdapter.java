/**
 * A class to help our GridView display images
 *
 * Based on https://www.youtube.com/watch?v=MRv0xtnaJAY
 *
 * Copyright (C) 2019 Simon D. Levy
 */

package edu.wlu.mockconapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class SpeakersImageAdapter extends BaseAdapter {

    private Context _context;

    public int [] _imageArray = {
            R.drawable.trevor_noah,
            R.drawable.tara_mcgowan,
            R.drawable.symone_sanders,
            R.drawable.patti_solis_doyle,
            R.drawable.nadine_strossen,
            R.drawable.mike_allen,
            R.drawable.andrew_gillum,
            R.drawable.donna_brazille,
            R.drawable.cameron_kasky,
            R.drawable.errin_haines,
            R.drawable.joe_donnelly,
            R.drawable.jim_acosta,
            R.drawable.bill_roberts
    };

    public SpeakersImageAdapter(Context context) {

        _context = context;
    }

    @Override
    public int getCount() {
        return _imageArray.length;
    }

    @Override
    public Object getItem(int position) {
        return _imageArray[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imageView = new ImageView(_context);
        imageView.setImageResource(_imageArray[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        // XXX NEED TO SIZE LAYOUT PARAMS BASED ON DEVICE SIZE
        int w = 300;
        int h = 300;
        imageView.setLayoutParams(new GridView.LayoutParams(w,h));

        return imageView;
    }
}
