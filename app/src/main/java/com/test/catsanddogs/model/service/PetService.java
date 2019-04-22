package com.test.catsanddogs.model.service;

import android.os.Handler;

import com.test.catsanddogs.model.DataHolder;
import com.test.catsanddogs.PetCache;
import com.test.catsanddogs.data.PackOfPets;
import com.test.catsanddogs.data.Pet;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PetService {
    private static final String BASE_URL = "http://kot3.com/xim/";

    private PetCache cache;
    private DataHolder dataHolder;
    private final Handler handler;
    private String query;
    private boolean process;

    public interface OnServiceListener {
        void onDataSuccess();
        void onDataFailure();
    }

    public PetService() {
        dataHolder = new DataHolder();
        handler = new Handler();
    }

    public DataHolder fetchData(String query) {
        this.process = true;
        this.query = query;
        startThread();
        return dataHolder;
    }

    public PetService setCache(PetCache cache) {
        this.cache = cache;
        return this;
    }

    private void startThread() {
        if (process) {
            Thread thread = new RequestThread();
            thread.setDaemon(true);
            thread.start();
        }
    }

//    private void onSuccess() {
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//                if (listener != null) {
//                    listener.onDataSuccess();
//                }
//            }
//        });
//    }
//
//    private void onFailure() {
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//                if (listener != null) {
//                    listener.onDataFailure();
//                }
//            }
//        });
//    }

//    public interface DataHolder<K, V> {
//        void put(K key, V value);
//    }

    private class RequestThread extends Thread {

        @Override
        public void run() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            KotApi api = retrofit.create(KotApi.class);

            try {
                Response<PackOfPets> response = api.getPets(query).execute();
                if (response.isSuccessful()) {
                    List<Pet> data = response.body().getData();

                    if (dataHolder != null) {
                        dataHolder.put(data);
                    }
                    if (cache != null) {
                        cache.put(query, data);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
