package com.example.assignmentmob403.Adapter.AdapterGallery;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignmentmob403.Model.Gallerie.Gallery;
import com.example.assignmentmob403.PhotoGalleryActivity;
import com.example.assignmentmob403.R;

import java.util.List;

public class AdapterGallerie extends RecyclerView.Adapter<GallerieHolder> {

     Context context;
     List<Gallery> galleryList;

    public AdapterGallerie(Context context, List<Gallery> galleryList) {
        this.context = context;
        this.galleryList = galleryList;
    }

    @NonNull
    @Override
    public GallerieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_gallerie, parent, false);
        GallerieHolder gallerieHolder = new GallerieHolder(view);
        return gallerieHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GallerieHolder holder, int position) {

        final Gallery gallery = galleryList.get(position);

        holder.tvNameGallerie.setText( "Gallery "+gallery.getTitle().getContent());
        holder.tvSlImage.setText(gallery.getCountPhotos()+"");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, PhotoGalleryActivity.class);
                intent.putExtra("Gallery_id",gallery.getGalleryId());
                intent.putExtra("GALLERY_NAME",gallery.getTitle().getContent());
                context.startActivity(intent);


            }
        });


    }

    @Override
    public int getItemCount() {
        return galleryList.size();
    }
}
