package com.example.assignmentmob403.Model.Favorite;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Favorites {

    @SerializedName("page")
    @Expose
    private Integer page;

    @SerializedName("pages")
    @Expose
    private Integer pages;

    @SerializedName("perpage")
    @Expose
    private Integer perpage;

    @SerializedName("total")
    @Expose
    private String total;

    @SerializedName("photo")
    @Expose
    private List<Favorite> favorite = null;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getPerpage() {
        return perpage;
    }

    public void setPerpage(Integer perpage) {
        this.perpage = perpage;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Favorite> getFavorite() {
        return favorite;
    }

    public void setFavorite(List<Favorite> favorite) {
        this.favorite = favorite;
    }
}
