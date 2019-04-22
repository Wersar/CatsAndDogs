package com.test.catsanddogs.data;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PackOfPets {

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private List<Pet> data = null;

    public String getMessage() {
        return message;
    }

    public List<Pet> getData() {
        return data;
    }

}
