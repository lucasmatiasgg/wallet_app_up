package com.up.tx.manager.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class Response {

    private Map<String, Object> data;

    public Response(){
        data = new LinkedHashMap<>();
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public void putItem(String idField, Object value) {
        data.put(idField, value);
    }

}
