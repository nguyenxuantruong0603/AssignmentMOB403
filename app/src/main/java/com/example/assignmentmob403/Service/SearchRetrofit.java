package com.example.assignmentmob403.Service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchRetrofit {
    private static final String BASE_URL = "https://www.flickr.com/";
    public static SearchService service;


    public static SearchService serviceDownload;

    public static SearchService getInstance() {

        if (service == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            service = retrofit.create(SearchService.class);
        }
        return service;
    }
}
