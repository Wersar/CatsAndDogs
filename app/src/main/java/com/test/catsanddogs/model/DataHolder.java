package com.test.catsanddogs.model;

import com.test.catsanddogs.PseudoCache;
import com.test.catsanddogs.data.Pet;

import java.util.List;

public class DataHolder extends PseudoCache<String, List<Pet>> {
    private static final String DATA_KEY = "data";

    private Observer observer;

    public interface Observer {
        void onChange(List<Pet> data);
    }

    @Override
    public void put(String key, List<Pet> value) {
        this.put(value);
    }

    public void put(List<Pet> data) {
        super.put(DATA_KEY, data);
        if (observer != null) {
            observer.onChange(data);
        }
    }

    public void observer(Observer observer) {
        this.observer = observer;
        List<Pet> data = get(DATA_KEY);
        if (data != null) {
            this.observer.onChange(data);
        }
    }

}
