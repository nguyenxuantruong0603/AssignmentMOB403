package com.example.assignmentmob403.Service;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PhotoGalleryRetrofit {
    private static final String BASE_URL = "https://www.flickr.com/";

    public static FlickrService service;

    public static FlickrService serviceDownload;

    public static FlickrService getInstance() {

        if (service == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            service = retrofit.create(FlickrService.class);
        }
        return service;
    }
}
