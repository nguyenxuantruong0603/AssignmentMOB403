package com.example.assignmentmob403.Adapter.AdapterGallery;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.assignmentmob403.R;


public class GallerieHolder extends RecyclerView.ViewHolder {

    public TextView tvNameGallerie,tvSlImage;
    public GallerieHolder(View view) {
        super(view);

        tvNameGallerie=view.findViewById(R.id.tvNameGallerie);
        tvSlImage=view.findViewById(R.id.tvSlImage);

    }
}
