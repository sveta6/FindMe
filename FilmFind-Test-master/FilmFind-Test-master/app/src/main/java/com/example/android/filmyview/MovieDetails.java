package com.example.android.filmyview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Movie;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MovieDetails extends AppCompatActivity {
    private String URL;
    private String URL_films = "https://api.themoviedb.org/3/search/movie?api_key=ba2ecc0718f461b1a96824d9a3623efd&query=";
    private String URL_genre = "https://api.themoviedb.org/3/discover/movie?api_key=ba2ecc0718f461b1a96824d9a3623efd&with_genres=";
    public MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        final Intent obj = getIntent();
        final String inputValue_films = obj.getStringExtra("Search");
        final String inputValue_genre = obj.getStringExtra("Genre");
        if (inputValue_films != null) {
            URL = URL_films + inputValue_films;
        } else if (inputValue_genre != null) {
            URL = URL_genre + inputValue_genre;
        }
        final List<MData> mData = new ArrayList<MData>();
        movieAdapter = new MovieAdapter(this, mData);
        ListView gridView = findViewById(R.id.movieGrid);
        gridView.setAdapter(movieAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MData obje = (MData) parent.getItemAtPosition(position);
                Intent intent = new Intent(view.getContext(), SelectedFilm.class);

                /*intent.putExtra("SelectedFilm", (Serializable) obje);
                Toast.makeText(MovieDetails.this, "ddddddddddd", Toast.LENGTH_SHORT).show();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                Bitmap bitmap = obje.getPoster();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                intent.putExtra("Bitmap", byteArrayOutputStream.toByteArray());*/

                intent.putExtra("mId", obje.getmId());
                intent.putExtra("Bitmap", obje.getPoster());
                intent.putExtra("Plot", obje.getPlot());
                intent.putExtra("Rating", obje.getRating().toString());
                intent.putExtra("Release", obje.getReleaseYear());
                intent.putExtra("FilmName", obje.getmName());
                startActivity(intent);
                Log.e("StartActivity", "Stat");
            }
        });
        MovieAsync objects = new MovieAsync();
        objects.execute(URL);
    }

    protected class MovieAsync extends AsyncTask<String, Void, List<MData>> {
        @Override
        protected List<MData> doInBackground(String... strings) {
            List<MData> mData = ExtractJsonResponse.extract(strings[0]);
            return mData;
        }

        @Override
        protected void onPostExecute(List<MData> mData) {
            movieAdapter.clear();
            if (mData != null) {
                movieAdapter.addAll(mData);
            }
        }
    }

    protected class PhotoSync extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            return null;
        }
    }
}
