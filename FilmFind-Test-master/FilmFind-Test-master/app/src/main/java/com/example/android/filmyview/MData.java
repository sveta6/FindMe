package com.example.android.filmyview;

import android.graphics.Bitmap;

import java.io.Serializable;

public class MData implements Serializable {
    private int mId;
    private String mName;
    private String releaseYear;
    private String plot;
    private Double rating;
    private String[] cast;
    private String country;
    private String language;
    private String trailerLink;
    private Bitmap poster;
    private String genre;
    //private boolean adult;

    public String getmName() {
        return mName;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public String getPlot() {
        return plot;
    }

    public Double getRating() {
        return rating;
    }

    // public String[] getCast() {
    //   return cast;
    // }
    public String getCountry() {
        return country;
    }

    public String getLanguage() {
        return language;
    }

    public String getGenre() {
        return genre;
    }

    public String getTrailerLink() {
        return trailerLink;
    }

    public Bitmap getPoster() {
        return poster;
    }

    //public boolean isAdult() {return adult;}

    public MData(String mName, String releaseYear, String plot, Double rating, String country, String language, String trailerLink, Bitmap poster, int mIdd) {
        this.mName = mName;
        this.releaseYear = releaseYear;
        this.plot = plot;
        this.rating = rating;
        // this.cast = cast;
        this.country = country;
        this.language = language;
        this.trailerLink = trailerLink;
        this.poster = poster;
        // this.adult = adult;
        mId = mIdd;
    }

    public int getmId() {
        return mId;
    }
}
