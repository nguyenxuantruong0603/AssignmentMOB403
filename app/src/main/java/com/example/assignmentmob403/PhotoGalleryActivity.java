package com.example.assignmentmob403;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.example.assignmentmob403.Adapter.AdapterGallery.AdapterGallerie;
import com.example.assignmentmob403.Adapter.AdapterPhotoGallery.AdapterPhotoGallerie;
import com.example.assignmentmob403.Model.GalleryPhoto.FPhotoGallery;
import com.example.assignmentmob403.Service.PhotoGalleryRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoGalleryActivity extends AppCompatActivity implements Callback<FPhotoGallery> {


    private SwipeRefreshLayout RefreshPhotoGallery;
    private RecyclerView rcPhotoGallery;
    AdapterPhotoGallerie adapterPhotoGallery;

    private static final String FULL_EXTRAS = "views,media,path_alias,url_sq,url_t,url_s,url_q,url_m,url_n,url_z,url_c,url_l,url_o";
    //private static final String USER_ID = "187059586@N04";
    private static final String KEY_TOKEN = "62b2190700f992ce7355eb837e069a78";
    private static final String GET_PHOTOGALLERY = "flickr.galleries.getPhotos";
    private int page = 1;
    ActionBar actionBar;
    String GALLERYNAME="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_gallery);
        RefreshPhotoGallery = findViewById(R.id.RefreshPhotoGallery);
        rcPhotoGallery = findViewById(R.id.rcPhotoGallery);
        actionBar = getSupportActionBar();


        Intent intent = getIntent();
        String Galleryid = intent.getStringExtra("Gallery_id");
         GALLERYNAME = intent.getStringExtra("GALLERY_NAME");
        RefreshPhotoGallery.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                            int Page= page++;
                        PhotoGalleryRetrofit.getInstance().getListPhotoGallery(FULL_EXTRAS, "1", Galleryid, "json", KEY_TOKEN, GET_PHOTOGALLERY,
                                Page, 10).enqueue(PhotoGalleryActivity.this);

                        adapterPhotoGallery.notifyDataSetChanged();


                        RefreshPhotoGallery.setRefreshing(false);
                    }
                }, 2000);
            }
        });

        PhotoGalleryRetrofit.getInstance().getListPhotoGallery(FULL_EXTRAS, "1", Galleryid, "json", KEY_TOKEN, GET_PHOTOGALLERY,
                page, 10).enqueue(PhotoGalleryActivity.this);
    }


    @Override
    public void onResponse(Call<FPhotoGallery> call, Response<FPhotoGallery> response) {
        adapterPhotoGallery = new AdapterPhotoGallerie(PhotoGalleryActivity.this,
                response.body().getPhotos().getPhoto());

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rcPhotoGallery.setLayoutManager(layoutManager);
        rcPhotoGallery.setAdapter(adapterPhotoGallery);
        actionBar.setTitle(GALLERYNAME);
    }

    @Override
    public void onFailure(Call<FPhotoGallery> call, Throwable t) {
        Log.e("CÓ LỖI SẢY RA ", t + "");
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutoolbar,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
//            case android.R.id.home:
//                onBackPressed();
//                return true;
            case R.id.menu1:
                Intent intent=new Intent(PhotoGalleryActivity.this,GalleryActivity.class);
                startActivity(intent);
                break;
            case R.id.menu2:
                Intent intent2=new Intent(PhotoGalleryActivity.this,HomeActivity.class);
                startActivity(intent2);
                break;
            case R.id.menu3:
                //code xử lý khi bấm menu3
                Toast.makeText(this, "menu3", Toast.LENGTH_SHORT).show();
                break;
            default:break;
        }

        return super.onOptionsItemSelected(item);
    }
}
