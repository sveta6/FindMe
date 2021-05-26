package com.example.android.filmyview;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MovieAdapter extends ArrayAdapter<MData> {

    public MovieAdapter(Context context, List<MData> md) {
        super(context, 0, md);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MData ob = getItem(position);
        View v = convertView;
        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.format, parent, false);
        }
        String title = ob.getmName();
        TextView FilmName = (TextView) v.findViewById(R.id.FilmName);
        TextView Release = (TextView) v.findViewById(R.id.DateOfRelease);
        //TextView Genre = (TextView) v.findViewById(R.id.isAdult);
        ImageView poster = (ImageView) v.findViewById(R.id.poster);
        FilmName.setText(title);
        Release.setText(ob.getReleaseYear());
        poster.setImageBitmap(ob.getPoster());
        /*if(ob.isAdult()==true){
            isAdult.setText("Adult(18+)");
        } else{
            isAdult.setText("U/A");
        }*/
        return v;
    }
}
