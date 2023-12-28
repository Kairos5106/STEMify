package com.example.stemify;

import java.util.HashMap;
import java.util.Map;

public class DataManager {
    private static DataManager instance;
    private static DataManager postInstance;
    private Map<String, Object> dataMap;
    private Map<String, Object> postDataMap;

    private DataManager() {
        dataMap = new HashMap<>();
        postDataMap = new HashMap<>();
    }

    public static synchronized DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    public static synchronized DataManager getPostInstance() {
        if (postInstance == null) {
            postInstance = new DataManager();
        }
        return postInstance;
    }

    // Methods for SignUp data
    public void putData(String key, Object value) {
        dataMap.put(key, value);
    }

    public Object getData(String key) {
        return dataMap.get(key);
    }

    // Methods for Post data
    public void putPostData(String key, Object value) {
        postDataMap.put(key, value);
    }

    public Object getPostData(String key) {
        return postDataMap.get(key);
    }
}

