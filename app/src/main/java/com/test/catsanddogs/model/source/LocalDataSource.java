package com.test.catsanddogs.model.source;


import com.test.catsanddogs.model.DataHolder;
import com.test.catsanddogs.PetCache;

public class LocalDataSource implements DataSource {

    private PetCache cache;

    public LocalDataSource(PetCache cache) {
        this.cache = cache;
    }

    @Override
    public DataHolder get(String name) {
        DataHolder dataHolder = new DataHolder();
        dataHolder.put(cache.get(name));
        return dataHolder;
    }
}
