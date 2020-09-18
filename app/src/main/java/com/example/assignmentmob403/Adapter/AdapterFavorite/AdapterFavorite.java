package com.example.assignmentmob403.Adapter.AdapterFavorite;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignmentmob403.ImageFavoriteActivity;
import com.example.assignmentmob403.Model.Favorite.Favorite;
import com.example.assignmentmob403.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterFavorite extends RecyclerView.Adapter<FavoriteHolder> {

    private Context context;
    private List<Favorite> favoriteList;

    public AdapterFavorite(Context context, List<Favorite> favoriteList) {
        this.context = context;
        this.favoriteList = favoriteList;
    }

    @NonNull
    @Override
    public FavoriteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recycle_favorite,parent,false);
        FavoriteHolder favoriteHolder=new FavoriteHolder(view);
        return favoriteHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteHolder holder, int position) {

        Favorite favorite=favoriteList.get(position);
        Picasso.get().load(favorite.getUrlC()).into(holder.imgFavorite);
        holder.tvViewsFavorite.setText(favorite.getViews()+" views");
//        holder.tvTitleFavorite.setText(favorite.getTitle());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ImageFavoriteActivity.class);
                intent.putExtra("URLC",favorite.getUrlC());
                intent.putExtra("NAME",favorite.getTitle());
                intent.putExtra("URLS",favorite.getUrlS());
                intent.putExtra("URLO",favorite.getUrlO());

                ActivityOptionsCompat optionsCompat= ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context,holder.imgFavorite, ViewCompat.getTransitionName(holder.imgFavorite));
                Log.e("CHECK PUTEXTRA",favorite.getUrlC()+"");
                context.startActivity(intent, optionsCompat.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return favoriteList.size();
    }
}
