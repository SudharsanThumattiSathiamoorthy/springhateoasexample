package com.sudhar.example.domain;

import java.util.UUID;

public class Invoice {

    private UUID id;

    private int price;

    private String title;

    private int customerId;

    public Invoice(UUID id, int price, String title, int customerId) {
        this.id = id;
        this.price = price;
        this.title = title;
        this.customerId = customerId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
