package com.example.assignmentmob403.Model.Search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FSearch {


    @SerializedName("photos")
    @Expose
    private Searchs searchs;
    @SerializedName("stat")
    @Expose
    private String stat;

    public Searchs getSearchs() {
        return searchs;
    }

    public void setSearchs(Searchs searchs) {
        this.searchs = searchs;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }
}
