package com.example.assignmentmob403.Adapter.AdapterPhotoGallery;

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
import com.example.assignmentmob403.Model.GalleryPhoto.Photo;
import com.example.assignmentmob403.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterPhotoGallerie extends RecyclerView.Adapter<PhotoGalleryHolder> {

    private Context context;
    private List<Photo> galleryList;

    public AdapterPhotoGallerie(Context context, List<Photo> galleryList) {
        this.context = context;
        this.galleryList = galleryList;
    }

    @NonNull
    @Override
    public PhotoGalleryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycle_photogallery, parent, false);
        PhotoGalleryHolder photoGalleryHolder = new PhotoGalleryHolder(view);
        return photoGalleryHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoGalleryHolder holder, int position) {


        Photo photoGallery = galleryList.get(position);
        holder.tvNamePhotoGallery.setText(photoGallery.getViews() +" Views");
        Picasso.get().load(photoGallery.getUrlC()).into(holder.imgPhotoGallery);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ImageFavoriteActivity.class);
                intent.putExtra("URLC",photoGallery.getUrlC());
                intent.putExtra("NAME",photoGallery.getTitle());
                intent.putExtra("URLS",photoGallery.getUrlS());
                intent.putExtra("URLO",photoGallery.getUrlO());

                ActivityOptionsCompat optionsCompat= ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context,holder.imgPhotoGallery, ViewCompat.getTransitionName(holder.imgPhotoGallery));
                Log.e("CHECK PUTEXTRA",photoGallery.getUrlC()+"");
                context.startActivity(intent, optionsCompat.toBundle());
            }
        });

    }

    @Override
    public int getItemCount() {
        return galleryList.size();
    }
}
