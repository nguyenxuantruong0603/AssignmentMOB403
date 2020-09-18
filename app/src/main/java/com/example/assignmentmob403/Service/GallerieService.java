package com.example.assignmentmob403.Service;

import com.example.assignmentmob403.Model.Gallerie.FGallerie;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GallerieService {

    @GET("services/rest/")
    Call<FGallerie> getThuvien(@Query("api_key") String api_key,
                               @Query("user_id") String user_id,
                               @Query("per_page") int per_page,
                               @Query("page") int page,
                               @Query("extras") String extras,
                               @Query("nojsoncallback") String nojsoncallback,
                               @Query("format") String format,
                               @Query("method") String method);


}
