package com.example.assignmentmob403.Model.Gallerie;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FGallerie {

    @SerializedName("galleries")
    @Expose
    private Galleries galleries;
    @SerializedName("stat")
    @Expose
    private String stat;

    public Galleries getGalleriese() {
        return galleries;
    }

    public void setGalleriese(Galleries galleries) {
        this.galleries = galleries;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

}
