package com.example.android.filmyview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

public class GenreDetails extends AppCompatActivity {
    Button action;
    Button adventure;
    Button comedy;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genres);
        action = (Button) findViewById(R.id.action_but);
        adventure = (Button) findViewById(R.id.adventure_but);
        comedy = (Button) findViewById(R.id.comedy_but);

        OnClickListener oMyButton = new OnClickListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GenreDetails.this, MovieDetails.class);
                switch (v.getId()) {
                    case R.id.action_but:
                        intent.putExtra("Genre", "28");
                        startActivity(intent);
                        break;
                    case R.id.adventure_but:
                        intent.putExtra("Genre", "12");
                        startActivity(intent);
                        break;
                    case R.id.comedy_but:
                        intent.putExtra("Genre", "35");
                        startActivity(intent);
                        break;
                }
            }
        };
        action.setOnClickListener(oMyButton);
        adventure.setOnClickListener(oMyButton);
        comedy.setOnClickListener(oMyButton);
    }
}
