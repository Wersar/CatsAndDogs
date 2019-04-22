package com.test.catsanddogs.model.source;

import com.test.catsanddogs.model.DataHolder;
import com.test.catsanddogs.PetCache;
import com.test.catsanddogs.model.service.PetService;


public class NetworkDataSource implements DataSource {

    private PetCache cache;
    private PetService service;

    public NetworkDataSource(PetService service, PetCache cache) {
        this.service = service;
        this.cache = cache;
    }

    @Override
    public DataHolder get(String name) {
        return service.setCache(cache).fetchData(name);
    }
}
