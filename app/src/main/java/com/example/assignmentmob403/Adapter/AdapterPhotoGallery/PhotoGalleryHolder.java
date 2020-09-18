package com.example.assignmentmob403.Adapter.AdapterPhotoGallery;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignmentmob403.R;


public class PhotoGalleryHolder extends RecyclerView.ViewHolder {
    TextView tvNamePhotoGallery;
    ImageView imgPhotoGallery;

    public PhotoGalleryHolder(@NonNull View itemView) {
        super(itemView);

        imgPhotoGallery=itemView.findViewById(R.id.imgPhotoGallery);
        tvNamePhotoGallery=itemView.findViewById(R.id.tvNamePhotoGallery);

    }
}
