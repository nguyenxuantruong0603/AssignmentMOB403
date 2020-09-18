package com.example.assignmentmob403;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.assignmentmob403.Adapter.AdapterFavorite.AdapterFavorite;
import com.example.assignmentmob403.Adapter.AdapterSearch.AdapterSearch;
import com.example.assignmentmob403.Model.Favorite.FFavorite;
import com.example.assignmentmob403.Model.Search.FSearch;
import com.example.assignmentmob403.Service.FavoriteRetrofit;
import com.example.assignmentmob403.Service.SearchRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity implements Callback<FSearch> {

    private EditText edtSearch;
    private ImageButton btnSearch;
    private RecyclerView rcSearch;
    private static final String FULL_EXTRAS = "views,media,path_alias,url_sq,url_t,url_s,url_q,url_m,url_n,url_z,url_c,url_l,url_o";
    private static String TEXTSEARCH = "";
    private static final String KEY_TOKEN = "62b2190700f992ce7355eb837e069a78";
    private static final String GET_FAVO = "flickr.photos.search";
    private int page = 1;

    private AdapterSearch adapterSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        edtSearch = findViewById(R.id.edtSearch);
        btnSearch = findViewById(R.id.btnSearch);
        rcSearch = findViewById(R.id.rcSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TEXTSEARCH = edtSearch.getText().toString();
                SearchRetrofit.getInstance().getListSearch(FULL_EXTRAS, "1", TEXTSEARCH, "json", KEY_TOKEN, GET_FAVO,
                        page, 20).enqueue(SearchActivity.this);

            }
        });
    }


    @Override
    public void onResponse(Call<FSearch> call, Response<FSearch> response) {
        adapterSearch = new AdapterSearch(SearchActivity.this,
                response.body().getSearchs().getSearchList());
        Log.e("SEARCH DATA ", response.body().getSearchs().getSearchList().size() + "");

        LinearLayoutManager layoutManager = new LinearLayoutManager(SearchActivity.this);
        rcSearch.setLayoutManager(layoutManager);
        rcSearch.setAdapter(adapterSearch);
    }

    @Override
    public void onFailure(Call<FSearch> call, Throwable t) {
        Log.e("FAIL SEARCH", t.getMessage());
    }
}