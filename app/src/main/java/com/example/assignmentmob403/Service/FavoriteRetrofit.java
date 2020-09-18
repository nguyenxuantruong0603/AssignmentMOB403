package com.example.assignmentmob403.Service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FavoriteRetrofit {

    private static final String BASE_URL = "https://www.flickr.com/";
    public static FavoriteService service;


    public static FavoriteService serviceDownload;

    public static FavoriteService getInstance() {

        if (service == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            service = retrofit.create(FavoriteService.class);
        }
        return service;
    }
}
