package com.example.assignmentmob403.Adapter.AdapterSearch;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignmentmob403.R;

public class SearchHolder extends RecyclerView.ViewHolder {

     ImageView imgSearch;
     TextView tvTitleSearch;
     TextView tvViewsSearch;
    public SearchHolder(@NonNull View itemView) {
        super(itemView);



        imgSearch = itemView.findViewById(R.id.imgSearch);
        tvTitleSearch = itemView.findViewById(R.id.tvTitleSearch);
        tvViewsSearch = itemView.findViewById(R.id.tvViewsSearch);

    }
}
