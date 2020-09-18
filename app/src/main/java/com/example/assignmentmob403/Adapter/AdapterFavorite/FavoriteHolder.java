package com.example.assignmentmob403.Adapter.AdapterFavorite;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignmentmob403.R;

public class FavoriteHolder extends RecyclerView.ViewHolder {
    TextView tvViewsFavorite,tvTitleFavorite;
    ImageView imgFavorite;
    public FavoriteHolder(@NonNull View itemView) {
        super(itemView);

        imgFavorite=itemView.findViewById(R.id.imgFavorite);
        tvViewsFavorite=itemView.findViewById(R.id.tvViewsFavorite);
//        tvTitleFavorite=itemView.findViewById(R.id.tvTitleFavorite);

    }
}
