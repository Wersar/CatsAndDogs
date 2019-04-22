package com.test.catsanddogs;

import android.app.Application;

import com.test.catsanddogs.data.Pet;

import java.util.List;

public class App extends Application {

    private PetCache cache;

    @Override
    public void onCreate() {
        super.onCreate();
        initStore();
    }

    private void initStore() {
        cache = new PetCache();
    }

    public PetCache getCache() {
        return cache;
    }

}
