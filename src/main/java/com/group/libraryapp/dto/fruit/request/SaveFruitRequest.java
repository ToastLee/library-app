package com.group.libraryapp.dto.fruit.request;

import java.time.LocalDate;

public class SaveFruitRequest {
    private String name;
    private LocalDate warehousingDate;
    private long price;
    private String status;

    public SaveFruitRequest(String name, LocalDate warehousingDate, long price, String status) {
        this.name = name;
        this.warehousingDate = warehousingDate;
        this.price = price;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getWarehousingDate() {
        return warehousingDate;
    }

    public void setWarehousingDate(LocalDate warehousingDate) {
        this.warehousingDate = warehousingDate;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
