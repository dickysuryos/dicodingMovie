package com.example.dickysuryo.moviecatalogue;

import com.example.dickysuryo.moviecatalogue.Model.MovieNewest_Model;

import java.util.List;

public class Constant {

    public  static  String BASE_URL = "https://api.themoviedb.org/3/";
    public static String BASE_URL_IMAGE = "https://image.tmdb.org/t/p/w500";
    public static int CONTENT_TYPE_MOVIE = 1;
    public  static int CONTENT_TYPE_MISC_INFORMATION = 2;
    public static int CONTENT_TYPE_INFORMATION = 3;
    public  static int CONTENT_TYPE_MISC_OTHER = 4;
    public static String KEY_LIST_POPULAR = "list_data_popular";
    public static String API_KEY = "44c09582cf533adac2fed1dccbc47c8b";
    public static List<MovieNewest_Model.Result> items;
}
