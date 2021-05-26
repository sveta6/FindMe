package com.example.android.filmyview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class SelectedFilm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_film);
        Intent intent = getIntent();
        /* This is setting image poster via intent */
        ImageView poster = (ImageView) findViewById(R.id.posterofSelected);
        // Bitmap bitmap = BitmapFactory.decodeByteArray(intent.getByteArrayExtra("Bitmap"),0,intent.getByteArrayExtra("Bitmap").length);
        poster.setImageBitmap((Bitmap) intent.getParcelableExtra("Bitmap"));
        /*This is setting plot of the selected film via intent object*/
        TextView plot = (TextView) findViewById(R.id.Plot);
        plot.setText(intent.getStringExtra("Plot"));
        /*This is setting IMDb rating of the selected film via intent */
        TextView rating = (TextView) findViewById(R.id.Ratingoffilm);
        rating.setText("IMDb: " + intent.getStringExtra("Rating"));
        /*This is Setting Released Date of the selected film via intent*/
        TextView releaseddate = (TextView) findViewById(R.id.ReleasdDate);
        releaseddate.setText("Release Date:  " + intent.getStringExtra("Release"));
        /*This is going to set the film title to textView*/
        TextView title = (TextView) findViewById(R.id.Title);
        title.setText("Title: " + intent.getStringExtra("FilmName"));
        TextView runtime = (TextView) findViewById(R.id.RunningTime);
        TextView genr = (TextView) findViewById(R.id.genre);
        runtime.setText("Calculating Time");
        genr.setText("Finding Genre");
        GettimeAsync gettimeAsync = new GettimeAsync();
        int tim = intent.getIntExtra("mId", 0);
        gettimeAsync.execute(tim);
    }

    public class GettimeAsync extends AsyncTask<Integer, Void, String[]> {


        @Override
        protected void onPostExecute(String[] s) {
            TextView runtime = (TextView) findViewById(R.id.RunningTime);
            runtime.setText("Duration: " + s[0] + " mins");
            TextView genr = (TextView) findViewById(R.id.genre);
            genr.setText("Genre: " + s[1]);
        }

        @Override
        protected String[] doInBackground(Integer... integers) {
            TimeExtract timeExtract = new TimeExtract();

            String urls = "https://api.themoviedb.org/3/movie/" + integers[0] + "?api_key=ba2ecc0718f461b1a96824d9a3623efd";
            String[] data = timeExtract.Extract(urls);
            return data;
        }
    }

}
