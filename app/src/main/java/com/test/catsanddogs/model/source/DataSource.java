package com.test.catsanddogs.model.source;

import com.test.catsanddogs.model.DataHolder;

public interface DataSource {
    DataHolder get(String name);
}
