package com.maxk;

import java.util.List;

public class Product {
    private String name;
    private List<String> deliveryOptions;

    public Product(String name, List<String> deliveryOptions) {
        this.name = name;
        this.deliveryOptions = deliveryOptions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getDeliveryOptions() {
        return deliveryOptions;
    }

    public void setDeliveryOptions(List<String> deliveryOptions) {
        this.deliveryOptions = deliveryOptions;
    }
}
