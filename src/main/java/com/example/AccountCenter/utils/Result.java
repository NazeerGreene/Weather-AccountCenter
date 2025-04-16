package com.example.AccountCenter.utils;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Result<K> {
    private final ArrayList<String> messages = new ArrayList<>();
    private ResultType type = ResultType.SUCCESS;
    private K payload = null;

    public boolean hasPayload() {
        return null != payload;
    }

    public K payload() {
        return payload;
    }

    public void payload(K payload) {
        this.payload = payload;
    }

    public ResultType type() {
        return this.type;
    }

    public void type(ResultType type) {
        this.type = type;
    }

    public Result<K> add(@NonNull String message) {
        this.messages.add(message);
        return this;
    }

    public List<String> messages() {
        return List.copyOf(this.messages);
    }
}
