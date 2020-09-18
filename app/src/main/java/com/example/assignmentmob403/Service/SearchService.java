package com.example.assignmentmob403.Service;

import com.example.assignmentmob403.Model.Search.FSearch;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchService {
    @GET("services/rest/")
    Call<FSearch> getListSearch(@Query("extras") String extras,
                                  @Query("nojsoncallback") String nojsoncallback,
                                  @Query("text") String text,
                                  @Query("format") String format,
                                  @Query("api_key") String api_key,
                                  @Query("method") String method,
                                  @Query("page") int page,
                                  @Query("per_page") int per_page);
}
