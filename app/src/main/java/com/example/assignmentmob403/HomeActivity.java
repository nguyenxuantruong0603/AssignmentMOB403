package com.example.assignmentmob403;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
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

import com.example.assignmentmob403.Adapter.AdapterFavorite.AdapterFavorite;
import com.example.assignmentmob403.Model.Favorite.FFavorite;
import com.example.assignmentmob403.Service.FavoriteRetrofit;
import com.example.assignmentmob403.Service.FavoriteService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements Callback<FFavorite> {

    RecyclerView rcFavorite;
    SwipeRefreshLayout RefreshFavorite;

    AdapterFavorite adapterFavorite;
    ActionBar actionBar;
    private static final String FULL_EXTRAS = "views,media,path_alias,url_sq,url_t,url_s,url_q,url_m,url_n,url_z,url_c,url_l,url_o";
    private static final String USER_ID = "187059586@N04";
    private static final String KEY_TOKEN = "62b2190700f992ce7355eb837e069a78";
    private static final String GET_FAVO = "flickr.favorites.getList";
    private int page = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        rcFavorite = findViewById(R.id.rcFavorite);
        RefreshFavorite = findViewById(R.id.RefeshFavorite);

        actionBar = getSupportActionBar();


        RefreshFavorite.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        int Page=page++;
                        FavoriteRetrofit.getInstance().getListFavorive(FULL_EXTRAS,
                                "1", USER_ID, "json", KEY_TOKEN, GET_FAVO, Page,
                                10).enqueue(HomeActivity.this);

                        adapterFavorite.notifyDataSetChanged();

                        RefreshFavorite.setRefreshing(false);

                    }
                }, 3000);
            }
        });

        FavoriteRetrofit.getInstance().getListFavorive(FULL_EXTRAS,
                "1", USER_ID, "json", KEY_TOKEN, GET_FAVO, page,
                10).enqueue(HomeActivity.this);
    }

    @Override
    public void onResponse(Call<FFavorite> call, Response<FFavorite> response) {

        adapterFavorite = new AdapterFavorite(HomeActivity.this,
                response.body().getFavorites().getFavorite());
        Log.e("abcabc", response.body().getFavorites().getFavorite().size() + "");

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        rcFavorite.setLayoutManager(layoutManager);
        rcFavorite.setAdapter(adapterFavorite);

        actionBar.setTitle("Favorite");

    }

    @Override
    public void onFailure(Call<FFavorite> call, Throwable t) {
        Log.e("LỖI", t.getMessage());
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutoolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Search:
                Intent intent0=new Intent(HomeActivity.this,SearchActivity.class);
                startActivity(intent0);
                break;
            case R.id.menu1:
                Intent intent = new Intent(HomeActivity.this, GalleryActivity.class);
                startActivity(intent);
                break;
            case R.id.menu2:
                Intent intent2 = new Intent(HomeActivity.this, HomeActivity.class);
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