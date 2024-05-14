package com.group.libraryapp.dto.fruit;

import java.time.LocalDate;

public class FruitInfo {
    private String name;
    private long price;
    private LocalDate warehousingDate;

    public FruitInfo(String name, long price, LocalDate warehousingDate) {
        this.name = name;
        this.price = price;
        this.warehousingDate = warehousingDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public LocalDate getWarehousingDate() {
        return warehousingDate;
    }

    public void setWarehousingDate(LocalDate warehousingDate) {
        this.warehousingDate = warehousingDate;
    }
}


