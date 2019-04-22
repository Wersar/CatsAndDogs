package com.test.catsanddogs.model;

import com.test.catsanddogs.PetCache;
import com.test.catsanddogs.model.service.PetService;
import com.test.catsanddogs.model.source.DataSource;
import com.test.catsanddogs.model.source.LocalDataSource;
import com.test.catsanddogs.model.source.NetworkDataSource;

public class PetDataSourceFactory {

    private PetCache cache;

    public PetDataSourceFactory(PetCache cache) {
        this.cache = cache;
    }

    public DataSource create(String name) {
        if (cache.isValid() && cache.isContains(name)) {
            return new LocalDataSource(cache);
        } else {
            PetService service = new PetService();
            return new NetworkDataSource(service, cache);
        }
    }

}
