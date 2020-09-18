package com.example.assignmentmob403.Model.Favorite;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FFavorite {

    @SerializedName("photos")
    @Expose
    private Favorites favorites;

    @SerializedName("stat")
    @Expose
    private String stat;

    public Favorites getFavorites() {
        return favorites;
    }

    public void setFavorites(Favorites favorites) {
        this.favorites = favorites;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }
}
