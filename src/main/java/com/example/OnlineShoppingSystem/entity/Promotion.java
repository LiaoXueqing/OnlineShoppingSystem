package com.example.OnlineShoppingSystem.entity;

import java.util.List;

public class Promotion {
    private String type;
    private List<String> barcodes;

    public Promotion() { }

    public Promotion(String type, List<String> barcodes) {
        this.type = type;
        this.barcodes = barcodes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getBarcodes() {
        return barcodes;
    }

    public void setBarcodes(List<String> barcodes) {
        this.barcodes = barcodes;
    }
}
