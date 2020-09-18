package com.example.assignmentmob403.Adapter.AdapterSearch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignmentmob403.Adapter.AdapterPhotoGallery.PhotoGalleryHolder;
import com.example.assignmentmob403.Model.Search.Search;
import com.example.assignmentmob403.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterSearch extends RecyclerView.Adapter<SearchHolder> {

    private Context context;
    private List<Search> searchList;

    public AdapterSearch(Context context, List<Search> searchList) {
        this.context = context;
        this.searchList = searchList;
    }

    @NonNull
    @Override
    public SearchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recycle_search, parent, false);
        SearchHolder photoGalleryHolder = new SearchHolder(view);
        return photoGalleryHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchHolder holder, int position) {
        Search search = searchList.get(position);
        Picasso.get().load(search.getUrlM()).into(holder.imgSearch);
        holder.tvTitleSearch.setText(search.getTitle());
        holder.tvViewsSearch.setText(search.getViews() + " views");
    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }
}
