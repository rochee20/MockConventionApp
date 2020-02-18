/**
 * A class to help our GridView display images
 *
 * Based on https://www.youtube.com/watch?v=MRv0xtnaJAY
 *
 * Copyright (C) 2019 Simon D. Levy
 */

package edu.wlu.mockconapp;

import android.app.Activity;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.widget.GridView;
import android.widget.ImageView;

public class EventsImageAdapter extends BaseAdapter {

    private Context _context;

    public int [] _imageArray = {
            R.drawable.parade,
            R.drawable.session1,
            R.drawable.session2,
            R.drawable.session3,
            R.drawable.session4,
            R.drawable.politics_in_media
    };

    public EventsImageAdapter(Context context) {

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
        int h = 400;
        imageView.setLayoutParams(new GridView.LayoutParams(w,h));

        return imageView;
    }
}
