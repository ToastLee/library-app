package com.group.libraryapp.dto.fruit.response;

public class CountFruitResponse {
    private long count;

    public CountFruitResponse(long count) {
        this.count = count;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
