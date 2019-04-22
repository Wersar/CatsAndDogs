package com.test.catsanddogs.model.service;

import com.test.catsanddogs.data.PackOfPets;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface KotApi {
    @GET("api.php")
    Call<PackOfPets> getPets(@Query("query") String pet_type);
}
