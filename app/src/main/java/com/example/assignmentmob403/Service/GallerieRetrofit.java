package com.example.assignmentmob403.Service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GallerieRetrofit {

    private static final String BASE_URL = "https://www.flickr.com/";

    public static GallerieService service;


    public static GallerieService serviceDownload;

    public static GallerieService getInstance() {

        if (service == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            service = retrofit.create(GallerieService.class);
        }
        return service;
    }
}
