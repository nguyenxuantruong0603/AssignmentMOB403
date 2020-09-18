package com.example.assignmentmob403.Service;


import com.example.assignmentmob403.Model.GalleryPhoto.FPhotoGallery;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FlickrService {
    @GET("services/rest/")
    Call<FPhotoGallery> getListPhotoGallery(@Query("extras") String extras,
                                            @Query("nojsoncallback") String nojsoncallback,
                                            @Query("gallery_id") String gallery_id,
                                            @Query("format") String format,
                                            @Query("api_key") String api_key,
                                            @Query("method") String method,
                                            @Query("page") int page,
                                            @Query("per_page") int per_page);

}
