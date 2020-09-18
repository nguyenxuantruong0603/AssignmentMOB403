package com.example.assignmentmob403.Service;

import com.example.assignmentmob403.Model.Favorite.FFavorite;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FavoriteService {

    @GET("services/rest/")
    Call<FFavorite> getListFavorive(@Query("extras") String extras,
                                    @Query("nojsoncallback") String nojsoncallback,
                                    @Query("user_id") String user_id,
                                    @Query("format") String format,
                                    @Query("api_key") String api_key,
                                    @Query("method") String method,
                                    @Query("page") int page,
                                    @Query("per_page") int per_page);
}

