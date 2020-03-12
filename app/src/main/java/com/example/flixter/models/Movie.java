package com.example.flixter.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Movie {

    String backdropPath;
    String posterPath;
    String title;
    String overview;
    Double voteAverage;

    public  Movie(JSONObject jsonObject) throws JSONException {
        backdropPath = jsonObject.getString("backdrop_path");
        posterPath = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
        voteAverage = jsonObject.getDouble("vote_average");

    }

   // public static List<Movie> fromJSONArray(JSONArray movieJsonArray) throws JSONException {
   public static List<Movie> fromJSONArray(JSONArray movieJsonArray)  {
        List<Movie> movies = new ArrayList<>();
          //  movies.add(new Movie(movieJsonArray.getJSONObject(i)));
            for(int i=0;i<movieJsonArray.length();i++)
            {
                try
                {
                    movies.add(new Movie(movieJsonArray.getJSONObject(i)));
                } catch (JSONException e)
                {
                    e.printStackTrace();
                }
        }
        return movies;
    }

    public String getPosterPath() {

        return String.format("https://image.tmdb.org/t/p/w342/%s",posterPath);
    }

    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s",backdropPath);
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }
}