package com.test.catsanddogs.model;

import com.test.catsanddogs.model.source.DataSource;


public class PetRepository {

    private PetDataSourceFactory factory;

    public PetRepository(PetDataSourceFactory factory) {
        this.factory = factory;
    }

    public DataHolder getPets(String name) {
        DataSource dataSource = factory.create(name);
        return dataSource.get(name);
    }
}
