package com.example.assignmentmob403;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.assignmentmob403.Adapter.AdapterGallery.AdapterGallerie;
import com.example.assignmentmob403.Model.Gallerie.FGallerie;
import com.example.assignmentmob403.Model.Gallerie.Gallery;
import com.example.assignmentmob403.Service.GallerieRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GalleryActivity extends AppCompatActivity implements Callback<FGallerie> {
    private RecyclerView RcGallery;

    private static final String FULL_EXTRAS = "views,media,path_alias,url_sq,url_t,url_s,url_q,url_m,url_n,url_z,url_c,url_l,url_o";

    private static final String USER_ID = "187059586@N04";
    private static final String KEY_TOKEN = "62b2190700f992ce7355eb837e069a78";
    private static final String GET_GALLERIE = "flickr.galleries.getList";
    private int page = 1;
    AdapterGallerie AdapterGallerie;
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        RcGallery = findViewById(R.id.RcGallery);
        actionBar=getSupportActionBar();


        GallerieRetrofit.getInstance().getThuvien(KEY_TOKEN, USER_ID, 10,
                page, FULL_EXTRAS, "1",
                "json", GET_GALLERIE).enqueue(GalleryActivity.this);
    }

    @Override
    public void onResponse(Call<FGallerie> call, Response<FGallerie> response) {
        AdapterGallerie = new AdapterGallerie(GalleryActivity.this,
                response.body().getGalleriese().getGallery());

        Log.e("KHÔNG LẤY ĐC DATA", response.body().getGalleriese().getGallery().size() + "");


        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(GalleryActivity.this);
        RcGallery.setLayoutManager(linearLayoutManager);
        RcGallery.setAdapter(AdapterGallerie);
        actionBar.setTitle("Galleries");
    }

    @Override
    public void onFailure(Call<FGallerie> call, Throwable t) {
        Log.e("ABCA", t.getMessage());
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutoolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case android.R.id.home:
//                onBackPressed();
//                return true;
            case R.id.menu1:
                Intent intent = new Intent(GalleryActivity.this, GalleryActivity.class);
                startActivity(intent);
                break;
            case R.id.menu2:
                Intent intent2 = new Intent(GalleryActivity.this, HomeActivity.class);
                startActivity(intent2);
                break;
            case R.id.menu3:
                //code xử lý khi bấm menu3
                Toast.makeText(this, "menu3", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}