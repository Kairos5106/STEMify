package com.example.stemify;

import java.util.HashMap;
import java.util.Map;

public class DataManager {
    private static DataManager instance;
    private Map<String, Object> dataMap;

    private DataManager() {
        dataMap = new HashMap<>();
    }

    public static synchronized DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    public void putData(String key, Object value) {
        dataMap.put(key, value);
    }

    public Object getData(String key) {
        return dataMap.get(key);
    }
}

